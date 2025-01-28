package jp.co.benesse.web.controller;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Binding;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpSession;
import jp.co.benesse.web.BaseTest;
import jp.co.benesse.web.constants.ScreenConstants;
import jp.co.benesse.web.constants.UrlConstants;
import jp.co.benesse.web.entity.MstAdminEntity;
import jp.co.benesse.web.exception.NoSuchRecordException;
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

    @Mock
    private BindingResult bindingResult;

    @Test
    public void showAdminLoginNullTest() throws Exception {
        AdminLoginForm adminLoginForm = null;
        String expected = ScreenConstants.ADMIN_LOGIN;
        String result = adminLoginController.showAdminLogin(adminLoginForm);

        // 遷移先が正しいか確認
        assertEquals(expected, result);
    }

    @Test
    public void showAdminLoginTest() throws Exception {
        AdminLoginForm adminLoginForm = new AdminLoginForm();
        adminLoginForm.setAdminId("adminId");
        adminLoginForm.setPassword("password");
        String expected = ScreenConstants.ADMIN_LOGIN;
        String result = adminLoginController.showAdminLogin(adminLoginForm);

        // 遷移先が正しいか確認
        assertEquals(expected, result);
    }

    @Test
    public void executeAdminLoginNullTest() throws Exception {
        AdminLoginForm adminLoginForm = null;
        assertThrows(NullPointerException.class, () -> {
            adminLoginController.executeAdminLogin(adminLoginForm, bindingResult);
        });
    }

    @Test
    public void executeAdminLoginTest() throws Exception {
        AdminLoginForm adminLoginForm = new AdminLoginForm();
        adminLoginForm.setAdminId("A001");
        adminLoginForm.setPassword("pass");

        List<MstAdminEntity> mstAdminEntityList = new ArrayList<>();
        MstAdminEntity entity = new MstAdminEntity();
        entity.setAdminId("A001");
        entity.setAdminName("test");
        entity.setPassword("d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1");
        entity.setRoleCode("01");
        entity.setLogicDelFlg("0");
        entity.setCreateBy("admin1");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime createTime = LocalDateTime.parse("2024-11-21 04:44:58.240", formatter);
        entity.setCreateTime(createTime);
        entity.setUpdateBy("admin");
        LocalDateTime upDateTime = LocalDateTime.parse("2024-11-21 04:44:58.240", formatter);
        entity.setUpdateTime(upDateTime);
        mstAdminEntityList.add(entity);

        when(mstAdminService.getAdminList(any(), any())).thenReturn(mstAdminEntityList);

        String expected = UrlConstants.REDIRECT + UrlConstants.ADMIN_MENU;
        String result = adminLoginController.executeAdminLogin(adminLoginForm, bindingResult);

        // 遷移先が正しいか確認
        assertEquals(expected, result);
    }

    @Test
    public void executeAdminLoginExceptionTest() throws Exception {
        AdminLoginForm adminLoginForm = new AdminLoginForm();
        adminLoginForm.setAdminId("A001");
        adminLoginForm.setPassword("pass");

        when(mstAdminService.getAdminList(any(), any())).thenThrow(new NoSuchRecordException());

        String expected = ScreenConstants.ADMIN_LOGIN;
        String result = adminLoginController.executeAdminLogin(adminLoginForm, bindingResult);

        // 遷移先が正しいか確認
        assertEquals(expected, result);
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
