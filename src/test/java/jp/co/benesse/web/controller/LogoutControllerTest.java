package jp.co.benesse.web.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import jakarta.servlet.http.HttpSession;
import jp.co.benesse.web.BaseTest;
import jp.co.benesse.web.constants.UrlConstants;

@ExtendWith(MockitoExtension.class)
public class LogoutControllerTest extends BaseTest {

    @InjectMocks
    private LogoutController logoutController;

    @Mock
    private HttpSession session;

    /**
     * logout:ケース1
     * 
     * @throws Exception
     */
    @Test
    public void logoutCase1() throws Exception {
        String result = logoutController.logout();
        assertEquals(UrlConstants.REDIRECT + UrlConstants.ADMIN_LOGIN, result);

        // session.invalidate()が呼び出されたことを確認
        verify(session).invalidate();
    }
}
