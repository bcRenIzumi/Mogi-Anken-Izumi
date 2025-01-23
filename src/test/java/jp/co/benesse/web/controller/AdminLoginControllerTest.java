package jp.co.benesse.web.controller;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import jp.co.benesse.web.BaseTest;
import jp.co.benesse.web.constants.CommonConstants;
import jp.co.benesse.web.constants.ScreenConstants;
import jp.co.benesse.web.constants.UrlConstants;
import jp.co.benesse.web.dto.AdminMenuScreenDto;
import jp.co.benesse.web.form.AdminLoginForm;
import jp.co.benesse.web.service.MstAdminService;

@ExtendWith(MockitoExtension.class)
public class AdminLoginControllerTest extends BaseTest {

    @InjectMocks
    private AdminLoginController adminLoginController;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @Mock
    private MstAdminService mstAdminService;

    @Test
    public void showAdminLoginTest() throws Exception {
        AdminLoginForm adminLoginForm = null;
        String expected = ScreenConstants.ADMIN_LOGIN;
        String result = adminLoginController.showAdminLogin(adminLoginForm);
    }

    // @Test
    // public void testShowAdminMenuUserNoUserName() throws Exception {

    // when(session.getAttribute(CommonConstants.USER_ID)).thenReturn("userId");
    // String result = adminMenuController.showAdminMenu(model);
    // assertEquals(UrlConstants.REDIRECT + UrlConstants.ADMIN_LOGIN, result);
    // }

    // @Test
    // public void testShowAdminMenuUserLoggedIn() {

    // when(session.getAttribute(CommonConstants.USER_ID)).thenReturn("userId");
    // when(session.getAttribute(CommonConstants.USER_NAME)).thenReturn("userName");

    // String result = adminMenuController.showAdminMenu(model);

    // // モデルにDTOが追加されているか確認
    // verify(model).addAttribute(eq("dto"), any(AdminMenuScreenDto.class));

    // // 遷移先が正しいか確認
    // assertEquals(ScreenConstants.ADMIN_MENU, result);
    // }

    // @Test
    // public void testShowAdminMenuNull() {

    // when(session.getAttribute(CommonConstants.USER_ID)).thenReturn("userId");
    // when(session.getAttribute(CommonConstants.USER_NAME)).thenReturn("userName");

    // assertThrows(NullPointerException.class, () -> {
    // adminMenuController.showAdminMenu(null);
    // });
    // }
}
