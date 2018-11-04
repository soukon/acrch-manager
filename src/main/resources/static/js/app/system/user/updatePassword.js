var validateUpdatePassword;
var $updatePasswordForm = $("#update-password-form");

$(function () {
    validateUpdatePasswordRule();

    $("#update-password .btn-save").click(function () {
        validateUpdatePassword = $updatePasswordForm.validate();
        var flag = validateUpdatePassword.form();
        if (flag) {
            $.post(ctx + "user/updatePassword", $updatePasswordForm.serialize(), function (r) {
                if (r.code === 0) {
                    validateUpdatePassword.resetForm();
                    $MB.closeAndRestModal("update-password");
                    $MB.n_success(r.msg);
                } else $MB.n_danger(r.msg);
            });
        }
    });

    $("#update-password .btn-close").click(function () {
        validateUpdatePassword.resetForm();
        $MB.closeAndRestModal("update-password");
    });

});

function validateUpdatePasswordRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validateUpdatePassword = $updatePasswordForm.validate({
        rules: {
            oldPassword: {
                required: true,
                remote: {
                    url: "user/checkPassword",
                    type: "get",
                    dataType: "json",
                    data: {
                        password: function () {
                            return $("input[name='oldPassword']").val().trim();
                        }
                    }
                }
            },
            newPassword: {
                required: true,
                minlength: 6,
                maxlength: 16
            },
            confirm: {
                required: true,
                equalTo: "#newPassword"
            }
        },
        messages: {
            oldPassword: {
                required: icon + "パスワードを入力してください",
                remote: icon + "パスワードが間違っています"
            },
            newPassword: {
                required: icon + "新しいパスワードを入力してください",
                minlength: icon + "新しいパスワードを6〜16桁で入力してください"
            },
            confirm: {
                required: icon + "（確認用）新しいパスワードを入力してください",
                equalTo: icon + "（確認用）新しいパスワードが新しいパスワードと不一致です"
            }

        }
    });
}