package jp.co.benesse.web.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;
import jp.co.benesse.web.BaseTest;
import jp.co.benesse.web.constants.UrlConstants;

@ExtendWith(MockitoExtension.class)
public class LogoutControllerTest extends BaseTest {

    /**
     * logout:ケース1
     * 
     * @throws Exception
     */
    @Test
    public void logoutCase1() throws Exception {

        LogoutControllerForTest logoutController = new LogoutControllerForTest();

        // MockHttpSessionのインスタンスを作成
        MockHttpSession session = new MockHttpSession();

        // セッションにキーとバリューをセット
        String testKey = "testKey";
        String testValue = "testValue";
        session.setAttribute(testKey, testValue);

        // logoutControllerにセッションをセット
        logoutController.setSession(session);

        // セッションにデータがセットされていることを確認
        assertEquals(testValue, session.getAttribute(testKey));

        // セッションが有効であることを確認
        assertEquals(false, session.isInvalid());

        // ログアウト
        String result = logoutController.logout();
        assertEquals(UrlConstants.REDIRECT + UrlConstants.ADMIN_LOGIN, result);

        // セッションが無効化されたことを確認
        assertEquals(true, session.isInvalid());
    }
}
