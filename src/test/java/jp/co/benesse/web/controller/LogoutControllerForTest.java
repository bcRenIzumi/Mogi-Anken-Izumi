package jp.co.benesse.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;
import jp.co.benesse.web.annotation.AppDescription;
import jp.co.benesse.web.constants.UrlConstants;

/**
 * <pre>
 * ログアウト用のコントローラークラスをテストするためのクラス
 *
 * 作成日：2024/11/27
 * </pre>
 *
 * @author ren_izumi
 * @version 1.0
 */
public class LogoutControllerForTest extends LogoutController {

    /**
     * セッションを外部からセットする
     * 
     * @param session セッション
     */
    public void setSession(HttpSession session) {
        this.session = session;
    }
}
