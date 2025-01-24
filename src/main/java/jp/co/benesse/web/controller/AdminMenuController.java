package jp.co.benesse.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.benesse.web.annotation.AppDescription;
import jp.co.benesse.web.constants.CommonConstants;
import jp.co.benesse.web.constants.ScreenConstants;
import jp.co.benesse.web.constants.UrlConstants;
import jp.co.benesse.web.dto.AdminMenuScreenDto;
import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 * メニュー画面のコントローラークラス
 *
 * 作成日：2024/11/26
 * </pre>
 *
 * @author ren_izumi
 * @version 1.0
 */
@Controller
public class AdminMenuController {

    /** セッション */
    @Autowired
    protected HttpSession session;

    /**
     * メニュー画面 : 画面表示
     * 
     * @param model モデル
     * @return 遷移先
     */
    @GetMapping(UrlConstants.ADMIN_MENU)
    @AppDescription(id = "ADMIN_MENU", name = "メニュー画面表示")
    public String showAdminMenu(Model model) {

        if (StringUtils.isEmpty((String) session.getAttribute(CommonConstants.USER_ID))
                || StringUtils.isEmpty((String) session.getAttribute(CommonConstants.USER_NAME))) {
            return UrlConstants.REDIRECT + UrlConstants.ADMIN_LOGIN;
        }

        AdminMenuScreenDto adminMenuScreenDto = new AdminMenuScreenDto();

        adminMenuScreenDto.setUserId((String) session.getAttribute(CommonConstants.USER_ID));
        adminMenuScreenDto.setUserName((String) session.getAttribute(CommonConstants.USER_NAME));

        model.addAttribute("dto", adminMenuScreenDto);

        return ScreenConstants.ADMIN_MENU;
    }

}
