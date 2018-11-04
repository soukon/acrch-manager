$(function() {
    initTreeTable();
});

function initTreeTable() {
    var setting = {
        id: 'deptId',
        code: 'deptId',
        url: ctx + 'dept/list',
        expandAll: true,
        expandColumn: "2",
        ajaxParams: {
            deptName: $(".dept-table-form").find("input[name='deptName']").val().trim()
        },
        columns: [{
                field: 'selectItem',
                checkbox: true
            },
            {
                title: 'No.',
                field: 'deptId',
                width: '50px'
            },
            {
                title: '部門名',
                field: 'deptName'
            },
            {
                title: '作成時間',
                field: 'createTime'
            }
        ]
    };

    $MB.initTreeTable('deptTable', setting);
}

function search() {
    initTreeTable();
}

function refresh() {
    $(".dept-table-form")[0].reset();
    search();
    $MB.refreshJsTree("deptTree", createDeptTree());
}

function deleteDepts() {
    var ids = $("#deptTable").bootstrapTreeTable("getSelections");
    var ids_arr = "";
    if (!ids.length) {
        $MB.n_warning("削除したい部門を選択してください！");
        return;
    }
    for (var i = 0; i < ids.length; i++) {
        ids_arr += ids[i].id;
        if (i !== (ids.length - 1)) ids_arr += ",";
    }
    $MB.confirm({
        text: "選択した部門を削除します。よろしいでしょうか？",
        confirmButtonText: "はい"
    }, function() {
        $.post(ctx + 'dept/delete', { "ids": ids_arr }, function(r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportDeptExcel(){
	$.post(ctx+"dept/excel",$(".dept-table-form").serialize(),function(r){
		if (r.code === 0) {
			window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
		} else {
			$MB.n_warning(r.msg);
		}
	});
}

function exportDeptCsv(){
	$.post(ctx+"dept/csv",$(".dept-table-form").serialize(),function(r){
		if (r.code === 0) {
			window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
		} else {
			$MB.n_warning(r.msg);
		}
	});
}