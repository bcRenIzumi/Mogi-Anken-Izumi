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

    /**
     * showAdminLogin:ケース1
     * 
     * @throws Exception
     */
    @Test
    public void showAdminLoginCase1() throws Exception {
        AdminLoginForm adminLoginForm = null;
        String expected = ScreenConstants.ADMIN_LOGIN;
        String result = adminLoginController.showAdminLogin(adminLoginForm);

        // 遷移先が正しいか確認
        assertEquals(expected, result);
    }

    /**
     * showAdminLogin:ケース2
     * 
     * @throws Exception
     */
    @Test
    public void showAdminLoginCase2() throws Exception {
        AdminLoginForm adminLoginForm = new AdminLoginForm();
        adminLoginForm.setAdminId("adminId");
        adminLoginForm.setPassword("password");
        String expected = ScreenConstants.ADMIN_LOGIN;
        String result = adminLoginController.showAdminLogin(adminLoginForm);

        // 遷移先が正しいか確認
        assertEquals(expected, result);
    }

    /**
     * executeAdminLogin:ケース1
     * 
     * @throws Exception
     */
    @Test
    public void executeAdminLoginCase1() throws Exception {
        AdminLoginForm adminLoginForm = null;
        assertThrows(NullPointerException.class, () -> {
            adminLoginController.executeAdminLogin(adminLoginForm, bindingResult);
        });
    }

    /**
     * executeAdminLogin:ケース2
     * 
     * @throws Exception
     */
    @Test
    public void executeAdminLoginCase2() throws Exception {
        AdminLoginForm adminLoginForm = new AdminLoginForm();

        List<MstAdminEntity> mstAdminEntityList = new ArrayList<>();
        MstAdminEntity entity = new MstAdminEntity();
        mstAdminEntityList.add(entity);

        when(mstAdminService.getAdminList(any(), any())).thenReturn(mstAdminEntityList);

        String expected = UrlConstants.REDIRECT + UrlConstants.ADMIN_MENU;
        String result = adminLoginController.executeAdminLogin(adminLoginForm, bindingResult);

        // 遷移先が正しいか確認
        assertEquals(expected, result);
    }

    /**
     * executeAdminLogin:ケース3
     * 
     * @throws Exception
     */
    @Test
    public void executeAdminLoginCase3() throws Exception {
        AdminLoginForm adminLoginForm = new AdminLoginForm();

        when(mstAdminService.getAdminList(any(), any())).thenThrow(new NoSuchRecordException());

        String expected = ScreenConstants.ADMIN_LOGIN;
        String result = adminLoginController.executeAdminLogin(adminLoginForm, bindingResult);

        // 遷移先が正しいか確認
        assertEquals(expected, result);
    }

    /**
     * executeAdminLogin:ケース4
     * 
     * @throws Exception
     */
    @Test
    public void executeAdminLoginCase4() throws Exception {
        AdminLoginForm adminLoginForm = null;
        assertThrows(NullPointerException.class, () -> {
            adminLoginController.executeAdminLogin(adminLoginForm, null);
        });
    }
}
