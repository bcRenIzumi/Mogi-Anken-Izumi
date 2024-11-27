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
import jp.co.benesse.web.dto.AdminMenuScreenDto;
import jp.co.benesse.web.entity.MstAdminEntity;
import jp.co.benesse.web.exception.WebParamException;
import jp.co.benesse.web.exception.WebUnexpectedException;
import jp.co.benesse.web.service.MstAdminService;
import jp.co.benesse.web.util.CheckUtil;
import jp.co.benesse.web.util.StringUtil;

/**
 * <pre>
 * 管理者メニュー画面のコントローラークラス
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
     * @throws WebParamException
     * @throws WebUnexpectedException
     */
    @GetMapping(UrlConstants.ADMIN_MENU)
    @AppDescription(id = "ADMIN_MENU", name = "管理者メニュー画面表示")
    public String showAdminMenu(Model model)
            throws WebParamException, WebUnexpectedException {

        AdminMenuScreenDto adminMenuScreenDto = new AdminMenuScreenDto();
        adminMenuScreenDto.setUserId((String) session.getAttribute("userId"));
        adminMenuScreenDto.setUserName((String) session.getAttribute("userName"));

        model.addAttribute("dto", adminMenuScreenDto);

        return "admin-menu";
    }

}
