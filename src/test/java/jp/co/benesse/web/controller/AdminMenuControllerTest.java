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

@ExtendWith(MockitoExtension.class)
public class AdminMenuControllerTest extends BaseTest {

    @InjectMocks
    private AdminMenuController adminMenuController;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    /**
     * showAdminMenu:ケース1
     */
    @Test
    public void showAdminMenuCase1() {

        when(session.getAttribute(CommonConstants.USER_ID)).thenReturn("userId");
        when(session.getAttribute(CommonConstants.USER_NAME)).thenReturn("userName");

        assertThrows(NullPointerException.class, () -> {
            adminMenuController.showAdminMenu(null);
        });
    }

    /**
     * showAdminMenu:ケース2
     * 
     * @throws Exception
     */
    @Test
    public void showAdminMenuCase2() throws Exception {
        String result = adminMenuController.showAdminMenu(model);
        assertEquals(UrlConstants.REDIRECT + UrlConstants.ADMIN_LOGIN, result);
    }

    /**
     * showAdminMenu:ケース3
     * 
     * @throws Exception
     */
    @Test
    public void showAdminMenuCase3() throws Exception {

        when(session.getAttribute(CommonConstants.USER_ID)).thenReturn("userId");
        String result = adminMenuController.showAdminMenu(model);
        assertEquals(UrlConstants.REDIRECT + UrlConstants.ADMIN_LOGIN, result);
    }

    /**
     * showAdminMenu:ケース4
     */
    @Test
    public void showAdminMenuUserCase4() {

        when(session.getAttribute(CommonConstants.USER_ID)).thenReturn("userId");
        when(session.getAttribute(CommonConstants.USER_NAME)).thenReturn("userName");

        String result = adminMenuController.showAdminMenu(model);

        // モデルにDTOが追加されているか確認
        verify(model).addAttribute(eq("dto"), any(AdminMenuScreenDto.class));

        // 遷移先が正しいか確認
        assertEquals(ScreenConstants.ADMIN_MENU, result);
    }
}
