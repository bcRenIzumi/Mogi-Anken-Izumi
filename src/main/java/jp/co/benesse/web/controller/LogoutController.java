package jp.co.benesse.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.benesse.web.annotation.AppDescription;
import jp.co.benesse.web.constants.UrlConstants;

/**
 * <pre>
 * ログアウト用のコントローラークラス
 *
 * 作成日：2024/11/27
 * </pre>
 *
 * @author ren_izumi
 * @version 1.0
 */
@Controller
public class LogoutController {

    /** セッション */
    @Autowired
    protected HttpSession session;

    /**
     * ログアウト
     * 
     */
    @GetMapping(UrlConstants.LOGOUT)
    @AppDescription(id = "LOGOUT", name = "ログアウト")
    public String logout() {

        session.invalidate();
        return UrlConstants.REDIRECT + UrlConstants.ADMIN_LOGIN;
    }
}
