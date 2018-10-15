$(function () {
    var $userTableForm = $(".user-table-form");
    var settings = {
        url: ctx + "user/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                username: $userTableForm.find("input[name='username']").val().trim(),
                ssex: $userTableForm.find("select[name='ssex']").val(),
                status: $userTableForm.find("select[name='status']").val()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'userId',
            visible: false
        }, {
            field: 'username',
            title: 'ユーザ名'
        }, {
            field: 'deptName',
            title: '部門'
        }, {
            field: 'email',
            title: 'メール'
        }, {
            field: 'mobile',
            title: 'Tel'
        }, {
            field: 'ssex',
            title: 'セックス',
            formatter: function (value, row, index) {
                if (value === '0') return 'メイル';
                else if (value === '1') return 'フィーメイル';
                else return 'シークレット';
            }
        }, {
            field: 'crateTime',
            title: '作成時間'
        }, {
            field: 'status',
            title: 'ステータス',
            formatter: function (value, row, index) {
                if (value === '1') return '<span class="badge badge-success">有効</span>';
                if (value === '0') return '<span class="badge badge-warning">無効</span>';
            }
        }

        ]
    };

    $MB.initTable('userTable', settings);
});

function search() {
    $MB.refreshTable('userTable');
}

function refresh() {
    $(".user-table-form")[0].reset();
    $MB.refreshTable('userTable');
}

function deleteUsers() {
    var selected = $("#userTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('削除したいユーザを選択してください！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].userId;
        if (i !== (selected_length - 1)) ids += ",";
        if (userName === selected[i].username) contain = true;
    }
    if (contain) {
        $MB.n_warning('ログインしているユーザが削除できません！');
        return;
    }

    $MB.confirm({
        text: "選択されたユーザを削除します。よろしいでしょうか？",
        confirmButtonText: "はい"
    }, function () {
        $.post(ctx + 'user/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportUserExcel() {
    $.post(ctx + "user/excel", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportUserCsv() {
    $.post(ctx + "user/csv", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}