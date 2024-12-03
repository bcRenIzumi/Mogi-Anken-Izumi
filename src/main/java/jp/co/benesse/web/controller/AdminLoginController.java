package jp.co.benesse.web.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.benesse.web.annotation.AppDescription;
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

    @ModelAttribute
    public AdminLoginForm setUpAdminLoginForm() {
        return new AdminLoginForm();
    }

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
     * @param model モデル
     * @return 遷移先
     */
    @GetMapping(UrlConstants.ADMIN_LOGIN)
    @AppDescription(id = "ADMIN_LOGIN", name = "管理者ログイン画面表示")
    public String showAdminLogin(Model model) {

        AdminLoginForm adminLoginForm = new AdminLoginForm();
        model.addAttribute("adminLoginForm", adminLoginForm);

        return "admin-login";
    }

    /**
     * 管理者ログイン画面 : ログインボタン押下
     * 
     * @param adminLoginForm 管理者ログインフォーム
     * @param bindingResult  リザルト
     * @param model          モデル
     * @return 遷移先
     * @throws WebUnexpectedException
     * @throws NoSuchAlgorithmException
     */
    @PostMapping(UrlConstants.ADMIN_LOGIN)
    @AppDescription(id = "ADMIN_LOGIN", name = "ログイン")
    public String executeAdminLogin(@Validated AdminLoginForm adminLoginForm, BindingResult bindingResult, Model model)
            throws WebUnexpectedException, NoSuchAlgorithmException {

        String adminId = adminLoginForm.getAdminId();
        String password = adminLoginForm.getPassword();

        if (bindingResult.hasErrors()) {
            rejectVaidationErrors(bindingResult);
            return "admin-login";
        }

        // 管理者IDとハッシュ化パスワードの組に一致するレコードを取得
        try {
            List<MstAdminEntity> mstAdminEntityList = mstAdminService.getAdminList(adminId, password);
            // セッションに利用者IDと利用者名を保存
            session.setAttribute("userId", adminId);
            session.setAttribute("userName", mstAdminEntityList.get(0).getAdminName());

            return UrlConstants.REDIRECT + UrlConstants.ADMIN_MENU;
        } catch (NoSuchRecordException e) {
            rejectGlobalError(bindingResult, "validationError.wrong.message", "ID、またはパスワード");

            return "admin-login";
        }
    }

    /**
     * バリデーションエラーメッセージ追加
     * 
     * @param bindingResult リザルト
     */
    private void rejectVaidationErrors(BindingResult bindingResult) {

        boolean isRejectNotBlank = false;
        boolean isRejectSize = false;
        boolean isRejectPattern = false;

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String fieldName = fieldError.getField(); // エラーが発生したフィールド名
            String errorCode = fieldError.getCode(); // エラーコード

            if (errorCode != null) {

                // エラーの種類によって処理を分ける
                switch (errorCode) {
                    case "NotBlank":
                        // 必須項目が未入力の場合の処理
                        rejectFieldError(isRejectNotBlank, bindingResult, fieldName, "validationError.required.message",
                                "ID、パスワード");
                        isRejectNotBlank = true;
                        break;
                    case "Size":
                        // サイズ制限を超えた場合の処理
                        rejectFieldError(isRejectSize, bindingResult, fieldName, "validationError.maxLength.message",
                                "ID、パスワード", "16文字");
                        isRejectSize = true;
                        break;
                    case "Pattern":
                        // パターンが一致しない場合の処理
                        rejectFieldError(isRejectPattern, bindingResult, fieldName,
                                "validationError.alphanumeric.message",
                                "ID、パスワード");
                        isRejectPattern = true;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * フィールドエラーメッセージ追加
     * 
     * @param isReject      追加済みか否か
     * @param bindingResult リザルト
     * @param fieldName     フィールド名
     * @param errorCode     エラーコード
     * @param args          エラーメッセージ引数
     */
    private void rejectFieldError(boolean isReject, BindingResult bindingResult, String fieldName, String errorCode,
            String... args) {
        if (!isReject) {
            bindingResult.rejectValue(fieldName, errorCode, args, "");
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
        bindingResult.reject(errorCode, args, "");
    }
}