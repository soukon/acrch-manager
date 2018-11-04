$(function () {
    var settings = {
        url: ctx + "session/list",
        pageSize: 100,
        columns: [{
            field: 'username',
            title: 'ユーザ名'
        }, {
            field: 'startTimestamp',
            title: 'ログイン時間'
        }, {
            field: 'lastAccessTime',
            title: 'ラストアクセス時間'
        }, {
            field: 'host',
            title: 'ホスト'
        }, {
            field: 'location',
            title: 'ログイン場所'
        }, {
            field: 'status',
            title: 'ステータス',
            formatter: function (value, row, index) {
                if (value === '1') return '<span class="badge badge-success">オンライン</span>';
                if (value === '0') return '<span class="badge badge-danger">オフライン</span>';
            }
        }, {
            title: '操作',
            formatter: function (value, row, index) {
                return "<a href='#' onclick='offline(\"" + row.id + "\",\"" + row.status + "\",\"" + row.username + "\")'>キックオフ</a>";
            }
        }]
    };

    $MB.initTable('onlineTable', settings);
});

function offline(id, status, username) {
    if (status === "0") {
        $MB.n_warning("該当ユーザが既にオフラインしました！");
        return;
    }
    if (username === userName) {
        location.href = ctx + 'logout';
    }
    $.get(ctx + "session/forceLogout", {"id": id}, function (r) {
        if (r.code === 0) {
            $MB.n_success('該当ユーザがキックオフされました！');
            $MB.refreshTable('onlineTable');
        } else {
            $MB.n_danger(r.msg);
        }
    }, "json");
}