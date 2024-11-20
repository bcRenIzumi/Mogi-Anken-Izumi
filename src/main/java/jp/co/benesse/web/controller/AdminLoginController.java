package jp.co.benesse.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jp.co.benesse.web.annotation.AppDescription;
import jp.co.benesse.web.common.TokenProcessor;
import jp.co.benesse.web.constants.UrlConstants;
import jp.co.benesse.web.enums.SampleKbn;
import jp.co.benesse.web.exception.WebParamException;
import jp.co.benesse.web.exception.WebUnexpectedException;
import jp.co.benesse.web.exception.WebViewHandlingException;
import jp.co.benesse.web.form.SampleForm;
import jp.co.benesse.web.service.SampleService;
import jp.co.benesse.web.util.MessageUtil;

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

    /** リクエスト */
    @Autowired
    private HttpServletRequest request;

    /** サンプルサービス */
    @Autowired
    private SampleService sampleService;

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
    @AppDescription(id = "ADMIN_LOGIN", name = "管理者ログイン画面")
    public String adminLogin(Model model)
            throws WebParamException, WebUnexpectedException {

        return "admin-login";
    }
}
