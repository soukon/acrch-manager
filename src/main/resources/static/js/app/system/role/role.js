$(function () {
    var settings = {
        url: ctx + "role/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                roleName: $(".role-table-form").find("input[name='roleName']").val().trim()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'roleName',
            title: '雇用区分'
        }, {
            field: 'remark',
            title: '区分説明'
        }, {
            field: 'createTime',
            title: '作成時間'
        }]
    };

    $MB.initTable('roleTable', settings);
});

function search() {
    $MB.refreshTable('roleTable');
}

function refresh() {
    $(".role-table-form")[0].reset();
    search();
}

function deleteRoles() {
    var selected = $("#roleTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('削除したい雇用区分を選択してください！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].roleId;
        if (i !== (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "該当雇用区分を削除すると、紐つけるユーザもその権限を消します。よろしいでしょうか？",
        confirmButtonText: "はい"
    }, function () {
        $.post(ctx + 'role/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportRoleExcel() {
    $.post(ctx + "role/excel", $(".role-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportRoleCsv() {
    $.post(ctx + "role/csv", $(".role-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}