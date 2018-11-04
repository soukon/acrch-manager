function updateMenu() {
    var selected = $("#menuTable").bootstrapTreeTable("getSelections");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('更新したいメニューまたはボタンを選択してください！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一つメニューまたはボタンを選択して、更新してください！');
        return;
    }
    var menuId = selected[0].id;
    $.post(ctx + "menu/getMenu", {"menuId": menuId}, function (r) {
        if (r.code === 0) {
            var $form = $('#menu-add');
            var $menuTree = $('#menuTree');
            $form.modal();
            var menu = r.msg;
            $("#menu-add-modal-title").html('メニュー/ボタン更新');
            $("input:radio[value='" + menu.type + "']").trigger("click");
            $form.find("input[name='menuName']").val(menu.menuName);
            $form.find("input[name='oldMenuName']").val(menu.menuName);
            $form.find("input[name='menuId']").val(menu.menuId);
            $form.find("input[name='icon']").val(menu.icon);
            $('#menu-perms-list').find(".autocomplete-input").val(menu.perms == null ? "" : menu.perms);
            $('#menu-url-list').find(".autocomplete-input").val(menu.url == null ? "" : menu.url);
            $menuTree.jstree('select_node', menu.parentId, true);
            $menuTree.jstree('disable_node', menu.menuId);
            $("#menu-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}