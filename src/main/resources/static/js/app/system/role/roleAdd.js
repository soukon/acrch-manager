var validator;
var $roleAddForm = $("#role-add-form");

$(function () {
    validateRule();
    createMenuTree();

    $("#role-add .btn-save").click(function () {
        var name = $(this).attr("name");
        getMenu();
        var validator = $roleAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "role/add", $roleAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("roleTable");
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "role/update", $roleAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("roleTable");
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#role-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#role-add-button").attr("name", "save");
    $("#role-add-modal-title").html('雇用区分追加');
    validator.resetForm();
    $MB.resetJsTree("menuTree");
    $MB.closeAndRestModal("role-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $roleAddForm.validate({
        rules: {
            roleName: {
                required: true,
                minlength: 3,
                maxlength: 10,
                remote: {
                    url: "role/checkRoleName",
                    type: "get",
                    dataType: "json",
                    data: {
                        roleName: function () {
                            return $("input[name='roleName']").val().trim();
                        },
                        oldRoleName: function () {
                            return $("input[name='oldRoleName']").val().trim();
                        }
                    }
                }
            },
            remark: {
                maxlength: 50
            },
            menuId: {
                required: true
            }
        },
        messages: {
            roleName: {
                required: icon + "雇用区分を入力してください",
                minlength: icon + "雇用区分を3〜10桁で入力してください",
                remote: icon + "該当雇用区分が存在しています"
            },
            remark: icon + "区分説明を50桁以内で入力してください",
            menuId: icon + "メニュー権限を選択してください"
        }
    });
}

function createMenuTree() {
    $.post(ctx + "menu/menuButtonTree", {}, function (r) {
        if (r.code === 0) {
            var data = r.msg;
            $('#menuTree').jstree({
                "core": {
                    'data': data.children
                },
                "state": {
                    "disabled": true
                },
                "checkbox": {
                    "three_state": false
                },
                "plugins": ["wholerow", "checkbox"]
            });
        } else {
            $MB.n_danger(r.msg);
        }
    })

}

function getMenu() {
    var $menuTree = $('#menuTree');
    var ref = $menuTree.jstree(true);
    var menuIds = ref.get_checked();
    $menuTree.find(".jstree-undetermined").each(function (i, element) {
        menuIds.push($(element).closest('.jstree-node').attr("id"));
    });
    $("[name='menuId']").val(menuIds);
}