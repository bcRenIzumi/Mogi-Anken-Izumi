package jp.co.benesse.web.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.benesse.web.annotation.AppDescription;
import jp.co.benesse.web.constants.CommonConstants;
import jp.co.benesse.web.constants.ScreenConstants;
import jp.co.benesse.web.constants.UrlConstants;
import jp.co.benesse.web.entity.MstAdminEntity;
import jp.co.benesse.web.exception.NoSuchRecordException;
import jp.co.benesse.web.exception.WebUnexpectedException;
import jp.co.benesse.web.form.AdminLoginForm;
import jp.co.benesse.web.service.MstAdminService;

/**
 * <pre>
 * 管理者ログイン画面のコントローラークラス
 *
 * 作成日：2024/11/19
 * </pre>
 *
 * @author ren_izumi
 * @version 1.0
 */
@Controller
public class AdminLoginController {

    /** 管理者情報のID及びパスワードの文字数制限 */
    @Value("${spring.data.admin.limit}")
    private Integer limit;

    /** 管理者情報サービス */
    @Autowired
    private MstAdminService mstAdminService;

    /** セッション */
    @Autowired
    protected HttpSession session;

    /**
     * 管理者ログイン画面 : 画面表示
     * 
     * @param adminLoginForm 管理者ログインフォーム
     * @return 遷移先
     */
    @GetMapping(UrlConstants.ADMIN_LOGIN)
    @AppDescription(id = "ADMIN_LOGIN", name = "管理者ログイン画面表示")
    public String showAdminLogin(AdminLoginForm adminLoginForm) {

        return ScreenConstants.ADMIN_LOGIN;
    }

    /**
     * 管理者ログイン画面 : ログインボタン押下
     * 
     * @param adminLoginForm 管理者ログインフォーム
     * @param bindingResult  リザルト
     * @return 遷移先
     * @throws WebUnexpectedException
     * @throws NoSuchAlgorithmException
     */
    @PostMapping(path = { UrlConstants.INDEX, UrlConstants.ADMIN_LOGIN })
    @AppDescription(id = "ADMIN_LOGIN", name = "ログイン")
    public String executeAdminLogin(@Validated AdminLoginForm adminLoginForm, BindingResult bindingResult)
            throws WebUnexpectedException, NoSuchAlgorithmException {

        if (bindingResult == null) {
            throw new NullPointerException("バインディングリザルトが空です");
        }

        if (bindingResult.hasErrors()) {
            return ScreenConstants.ADMIN_LOGIN;
        }

        try {
            // 管理者IDとハッシュ化パスワードの組に一致するレコードを取得
            String adminId = adminLoginForm.getAdminId();
            List<MstAdminEntity> mstAdminEntityList = mstAdminService.getAdminList(adminId,
                    adminLoginForm.getPassword());

            // セッションに利用者IDと利用者名を保存
            session.setAttribute(CommonConstants.USER_ID, adminId);
            session.setAttribute(CommonConstants.USER_NAME, mstAdminEntityList.get(0).getAdminName());

            // メニュー画面にリダイレクト
            return UrlConstants.REDIRECT + UrlConstants.ADMIN_MENU;
        } catch (NoSuchRecordException e) {
            // エラーを追加
            rejectGlobalError(bindingResult, "user.input.wrong.message", "ID、またはパスワード");
            return ScreenConstants.ADMIN_LOGIN;
        }
    }

    /**
     * グローバルエラーメッセージ追加
     * 
     * @param bindingResult リザルト
     * @param errorCode     エラーコード
     * @param args          エラーメッセージ引数
     */
    private void rejectGlobalError(BindingResult bindingResult, String errorCode, String... args) {
        if (bindingResult == null) {
            return;
        }
        bindingResult.reject(errorCode, args, "");
    }
}