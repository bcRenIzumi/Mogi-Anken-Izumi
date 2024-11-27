package jp.co.benesse.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jp.co.benesse.web.annotation.AppDescription;
import jp.co.benesse.web.constants.MessageConstants;
import jp.co.benesse.web.constants.UrlConstants;
import jp.co.benesse.web.dto.AdminLoginScreenDto;
import jp.co.benesse.web.entity.MstAdminEntity;
import jp.co.benesse.web.exception.WebParamException;
import jp.co.benesse.web.exception.WebUnexpectedException;
import jp.co.benesse.web.service.MstAdminService;
import jp.co.benesse.web.util.CheckUtil;
import jp.co.benesse.web.util.StringUtil;

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
     * @param model モデル
     * @return 遷移先
     * @throws WebParamException
     * @throws WebUnexpectedException
     */
    @GetMapping(UrlConstants.ADMIN_LOGIN)
    // 画面IDと画面名を設定する。ログのXXXXX部分がID置換される
    @AppDescription(id = "ADMIN_LOGIN", name = "管理者ログイン画面表示")
    public String showAdminLogin(Model model)
            throws WebParamException, WebUnexpectedException {

        AdminLoginScreenDto adminLoginScreenDto = new AdminLoginScreenDto();
        adminLoginScreenDto.setErrorMessage("");
        model.addAttribute("dto", adminLoginScreenDto);

        return "admin-login";
    }

    /**
     * 管理者ログイン画面 : ログインボタン押下
     * 
     * @param model モデル
     * @return 遷移先
     * @throws WebParamException
     * @throws WebUnexpectedException
     */
    @PostMapping(UrlConstants.ADMIN_LOGIN)
    // 画面IDと画面名を設定する。ログのXXXXX部分がID置換される
    @AppDescription(id = "ADMIN_LOGIN", name = "ログイン")
    public String executeAdminLogin(@RequestParam String adminId, @RequestParam String password, Model model)
            throws WebParamException, WebUnexpectedException {

        String errorMessage = "";
        boolean isError = false;

        // 管理者IDとパスワードのどちらかが空白の場合
        if (!StringUtils.hasLength(adminId) || !StringUtils.hasLength(password)) {
            errorMessage = StringUtils.hasLength(errorMessage) ? errorMessage : MessageConstants.ADMIN_NOT_ENTERED;
            isError = true;
        }

        // 管理者IDとパスワードのどちらかが16文字以上の場合
        if (StringUtil.length(adminId) > limit || StringUtil.length(password) > limit) {
            errorMessage = StringUtils.hasLength(errorMessage) ? errorMessage : MessageConstants.ADMIN_LENGTH_OVER;
            isError = true;
        }

        // 管理者IDとパスワードのどちらかに半角英数字以外の文字が使われているの場合
        if (!CheckUtil.isRegex(adminId, "^[a-zA-Z0-9]+$") || !CheckUtil.isRegex(password, "^[a-zA-Z0-9]+$")) {
            errorMessage = StringUtils.hasLength(errorMessage) ? errorMessage : MessageConstants.ADMIN_USE_ILLEGAL_CHAR;
            isError = true;
        }

        // 管理者IDとハッシュ化パスワードの組に一致するレコードを取得
        List<MstAdminEntity> mstAdminEntityList = mstAdminService.getAdminList(adminId, password);
        if (mstAdminEntityList.size() == 0) {
            errorMessage = StringUtils.hasLength(errorMessage) ? errorMessage : MessageConstants.ADMIN_WRONG_ID_PASS;
            isError = true;
        }

        if (isError) {
            AdminLoginScreenDto adminLoginScreenDto = new AdminLoginScreenDto();
            adminLoginScreenDto.setErrorMessage(errorMessage);
            model.addAttribute("dto", adminLoginScreenDto);

            return "admin-login";
        }

        // セッションに利用者IDと利用者名を保存
        session.setAttribute("userId", adminId);
        session.setAttribute("userName", mstAdminEntityList.get(0).getAdminName());

        return UrlConstants.REDIRECT + UrlConstants.ADMIN_MENU;
    }
}
