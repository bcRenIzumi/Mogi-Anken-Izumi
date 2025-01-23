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
public class LogoutControllerTest extends BaseTest {

    @InjectMocks
    private LogoutController logoutController;

    @Mock
    private HttpSession session;

    @Test
    public void testLogout() throws Exception {
        String result = logoutController.logout();
        assertEquals(UrlConstants.REDIRECT + UrlConstants.ADMIN_LOGIN, result);
    }
}
