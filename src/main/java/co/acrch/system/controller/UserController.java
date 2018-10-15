package co.acrch.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import co.acrch.common.annotation.Log;
import co.acrch.common.controller.BaseController;
import co.acrch.common.domain.QueryRequest;
import co.acrch.common.domain.ResponseBo;
import co.acrch.common.util.FileUtils;
import co.acrch.common.util.MD5Utils;
import co.acrch.system.domain.User;
import co.acrch.system.service.UserService;

@Controller
public class UserController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    private static final String ON = "on";

    @RequestMapping("user")
    @RequiresPermissions("user:list")
    public String index(Model model) {
        User user = super.getCurrentUser();
        model.addAttribute("user", user);
        return "system/user/user";
    }

    @RequestMapping("user/checkUserName")
    @ResponseBody
    public boolean checkUserName(String username, String oldusername) {
        if (StringUtils.isNotBlank(oldusername) && username.equalsIgnoreCase(oldusername)) {
            return true;
        }
        User result = this.userService.findByName(username);
        return result == null;
    }

    @RequestMapping("user/getUser")
    @ResponseBody
    public ResponseBo getUser(Long userId) {
        try {
            User user = this.userService.findById(userId);
            return ResponseBo.ok(user);
        } catch (Exception e) {
            log.error("該当ユーザが存在しません、ご確認ください！", e);
            return ResponseBo.error("該当ユーザが存在しません、ご確認ください！");
        }
    }

    @Log("社員リスト取得")
    @RequestMapping("user/list")
    @RequiresPermissions("user:list")
    @ResponseBody
    public Map<String, Object> userList(QueryRequest request, User user) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<User> list = this.userService.findUserWithDept(user, request);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
    }

    @RequestMapping("user/excel")
    @ResponseBody
    public ResponseBo userExcel(User user) {
        try {
            List<User> list = this.userService.findUserWithDept(user, null);
            return FileUtils.createExcelByPOIKit("社員情報表（Excel）", list, User.class);
        } catch (Exception e) {
            log.error("社員情報（Excel）が出力失敗しました！", e);
            return ResponseBo.error("社員情報（Excel）が出力失敗しました！");
        }
    }

    @RequestMapping("user/csv")
    @ResponseBody
    public ResponseBo userCsv(User user) {
        try {
            List<User> list = this.userService.findUserWithDept(user, null);
            return FileUtils.createCsv("社員情報表（Csv）", list, User.class);
        } catch (Exception e) {
            log.error("社員情報（Csv）が出力失敗しました！", e);
            return ResponseBo.error("社員情報（Csv）が出力失敗しました！");
        }
    }

    @RequestMapping("user/regist")
    @ResponseBody
    public ResponseBo regist(User user) {
        try {
            User result = this.userService.findByName(user.getUsername());
            if (result != null) {
                return ResponseBo.warn("該当アカウントが既に存在しています！");
            }
            this.userService.registUser(user);
            return ResponseBo.ok();
        } catch (Exception e) {
            log.error("アカウントが登録失敗しました！", e);
            return ResponseBo.error("アカウントが登録失敗しました！");
        }
    }

    @Log("テーマ変更")
    @RequestMapping("user/theme")
    @ResponseBody
    public ResponseBo updateTheme(User user) {
        try {
            this.userService.updateTheme(user.getTheme(), user.getUsername());
            return ResponseBo.ok();
        } catch (Exception e) {
            log.error("テーマ変更失敗しました！", e);
            return ResponseBo.error("テーマ変更失敗しました！");
        }
    }

    @Log("ユーザ新規")
    @RequiresPermissions("user:add")
    @RequestMapping("user/add")
    @ResponseBody
    public ResponseBo addUser(User user, Long[] roles) {
        try {
            if (ON.equalsIgnoreCase(user.getStatus()))
                user.setStatus(User.STATUS_VALID);
            else
                user.setStatus(User.STATUS_LOCK);
            this.userService.addUser(user, roles);
            return ResponseBo.ok("ユーザが追加成功しました！");
        } catch (Exception e) {
            log.error("ユーザが追加失敗しました！", e);
            return ResponseBo.error("ユーザが追加失敗しました！");
        }
    }

    @Log("ユーザ変更")
    @RequiresPermissions("user:update")
    @RequestMapping("user/update")
    @ResponseBody
    public ResponseBo updateUser(User user, Long[] rolesSelect) {
        try {
            if (ON.equalsIgnoreCase(user.getStatus()))
                user.setStatus(User.STATUS_VALID);
            else
                user.setStatus(User.STATUS_LOCK);
            this.userService.updateUser(user, rolesSelect);
            return ResponseBo.ok("ユーザが変更成功しました！");
        } catch (Exception e) {
            log.error("ユーザが変更失敗しました！", e);
            return ResponseBo.error("ユーザが変更失敗しました！");
        }
    }

    @Log("ユーザ削除")
    @RequiresPermissions("user:delete")
    @RequestMapping("user/delete")
    @ResponseBody
    public ResponseBo deleteUsers(String ids) {
        try {
            this.userService.deleteUsers(ids);
            return ResponseBo.ok("ユーザが削除成功しました！");
        } catch (Exception e) {
            log.error("ユーザが削除失敗しました！", e);
            return ResponseBo.error("ユーザが削除失敗しました！");
        }
    }

    @RequestMapping("user/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password) {
        User user = getCurrentUser();
        String encrypt = MD5Utils.encrypt(user.getUsername().toLowerCase(), password);
        return user.getPassword().equals(encrypt);
    }

    @RequestMapping("user/updatePassword")
    @ResponseBody
    public ResponseBo updatePassword(String newPassword) {
        try {
            this.userService.updatePassword(newPassword);
            return ResponseBo.ok("パスワードが変更しました！");
        } catch (Exception e) {
            log.error("パスワード変更が失敗しました！", e);
            return ResponseBo.error("パスワード変更が失敗しました！");
        }
    }

    @RequestMapping("user/profile")
    public String profileIndex(Model model) {
        User user = super.getCurrentUser();
        user = this.userService.findUserProfile(user);
        String ssex = user.getSsex();
        if (User.SEX_MALE.equals(ssex)) {
            user.setSsex("セックス：メイル");
        } else if (User.SEX_FEMALE.equals(ssex)) {
            user.setSsex("セックス：フィーメイル");
        } else {
            user.setSsex("セックス：シークレット");
        }
        model.addAttribute("user", user);
        return "system/user/profile";
    }

    @RequestMapping("user/getUserProfile")
    @ResponseBody
    public ResponseBo getUserProfile(Long userId) {
        try {
            User user = new User();
            user.setUserId(userId);
            return ResponseBo.ok(this.userService.findUserProfile(user));
        } catch (Exception e) {
            log.error("個人情報取得が失敗しました！", e);
            return ResponseBo.error("個人情報取得が失敗しました！");
        }
    }

    @RequestMapping("user/updateUserProfile")
    @ResponseBody
    public ResponseBo updateUserProfile(User user) {
        try {
            this.userService.updateUserProfile(user);
            return ResponseBo.ok("個人情報が更新しました！");
        } catch (Exception e) {
            log.error("個人情報更新が失敗しました！", e);
            return ResponseBo.error("個人情報更新が失敗しました！");
        }
    }

    @RequestMapping("user/changeAvatar")
    @ResponseBody
    public ResponseBo changeAvatar(String imgName) {
        try {
            String[] img = imgName.split("/");
            String realImgName = img[img.length - 1];
            User user = getCurrentUser();
            user.setAvatar(realImgName);
            this.userService.updateNotNull(user);
            return ResponseBo.ok("アバターが変更しました！");
        } catch (Exception e) {
            log.error("アバター変更が失敗しました！", e);
            return ResponseBo.error("アバター変更が失敗しました！");
        }
    }
}
