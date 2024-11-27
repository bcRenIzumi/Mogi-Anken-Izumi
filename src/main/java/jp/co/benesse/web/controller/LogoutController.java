package jp.co.benesse.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * @throws WebParamException
     * @throws WebUnexpectedException
     */
    @GetMapping(UrlConstants.LOGOUT)
    @AppDescription(id = "LOGOUT", name = "ログアウト")
    public String logout(Model model)
            throws WebParamException, WebUnexpectedException {

        session.invalidate();
        return UrlConstants.REDIRECT + UrlConstants.ADMIN_LOGIN;
    }
}
