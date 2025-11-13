package jp.co.benesse.web.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.Locale;

import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.context.TestPropertySource;

import jp.co.benesse.web.BaseTest;
import jp.co.benesse.web.constants.CommonConstants;

/**
 * MessageUtil用のテストクラス
 * 
 * @author bc0109695
 */
@SpringBootTest
@TestPropertySource(locations = { "classpath:application-test.properties" })
public class MessageUtilTest extends BaseTest {

    @MockBean
    private MessageSource messageSource;

    /**
     * テスト前処理
     */
    @BeforeEach
    public void setUp() throws Exception {
        // ThreadContextをクリア
        ThreadContext.clearAll();
        
        // リフレクションを使ってMessageUtilのstatic MessageSourceフィールドにモックを設定
        Field field = MessageUtil.class.getDeclaredField("messageSource");
        field.setAccessible(true);
        field.set(null, messageSource);
    }

    /**
     * テスト後処理
     */
    @AfterEach
    public void tearDown() {
        // ThreadContextをクリア
        ThreadContext.clearAll();
    }

    /**
     * getMessage:ケース1 - 通常のメッセージ取得（置換文字列なし）
     */
    @Test
    public void getMessageCase1() {
        String key = "test.message";
        String expectedMessage = "テストメッセージ";
        
        when(messageSource.getMessage(eq(key), any(), eq(Locale.JAPAN)))
                .thenReturn(expectedMessage);
        
        String actualMessage = MessageUtil.getMessage(key);
        
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * getMessage:ケース2 - パラメータ付きメッセージ取得
     */
    @Test
    public void getMessageCase2() {
        String key = "test.message";
        String param = "テストパラメータ";
        String expectedMessage = "テストメッセージ: テストパラメータ";
        
        when(messageSource.getMessage(eq(key), any(), eq(Locale.JAPAN)))
                .thenReturn(expectedMessage);
        
        String actualMessage = MessageUtil.getMessage(key, param);
        
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * getMessage:ケース3 - エラーコード置換パートが含まれるメッセージ（ThreadContextに機能IDがある場合）
     */
    @Test
    public void getMessageCase3() {
        String key = "test.message";
        String funcId = "SAMPLE";
        String messageWithReplacePart = "WE03-XXXXX-001::エラーメッセージ";
        String expectedMessage = "WE03-SAMPLE-001::エラーメッセージ";
        
        // ThreadContextに機能IDを設定
        ThreadContext.put(CommonConstants.FUNC_ID, funcId);
        
        when(messageSource.getMessage(eq(key), any(), eq(Locale.JAPAN)))
                .thenReturn(messageWithReplacePart);
        
        String actualMessage = MessageUtil.getMessage(key);
        
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * getMessage:ケース4 - エラーコード置換パートが含まれるメッセージ（ThreadContextに機能IDがない場合）
     */
    @Test
    public void getMessageCase4() {
        String key = "test.message";
        String messageWithReplacePart = "WE03-XXXXX-001::エラーメッセージ";
        String expectedMessage = "WE03-XXXXX-001::エラーメッセージ";
        
        // ThreadContextに機能IDを設定しない（クリア状態）
        
        when(messageSource.getMessage(eq(key), any(), eq(Locale.JAPAN)))
                .thenReturn(messageWithReplacePart);
        
        String actualMessage = MessageUtil.getMessage(key);
        
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * getMessage:ケース5 - エラーコード置換パートが含まれるメッセージ（ThreadContextに機能IDがnullの場合）
     */
    @Test
    public void getMessageCase5() {
        String key = "test.message";
        String messageWithReplacePart = "WE03-XXXXX-001::エラーメッセージ";
        String expectedMessage = "WE03-XXXXX-001::エラーメッセージ";
        
        // ThreadContextに機能IDをnullで設定
        ThreadContext.put(CommonConstants.FUNC_ID, null);
        
        when(messageSource.getMessage(eq(key), any(), eq(Locale.JAPAN)))
                .thenReturn(messageWithReplacePart);
        
        String actualMessage = MessageUtil.getMessage(key);
        
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * getMessage:ケース6 - 複数のパラメータ付きメッセージ取得
     */
    @Test
    public void getMessageCase6() {
        String key = "test.message";
        String param1 = "パラメータ1";
        String param2 = "パラメータ2";
        String expectedMessage = "テストメッセージ: パラメータ1, パラメータ2";
        
        when(messageSource.getMessage(eq(key), any(), eq(Locale.JAPAN)))
                .thenReturn(expectedMessage);
        
        String actualMessage = MessageUtil.getMessage(key, param1, param2);
        
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * getMessage:ケース7 - エラーコード置換パートが複数含まれるメッセージ
     */
    @Test
    public void getMessageCase7() {
        String key = "test.message";
        String funcId = "SAMPLE";
        String messageWithReplacePart = "WE03-XXXXX-001::エラーメッセージ XXXX-XXXXX-002";
        String expectedMessage = "WE03-SAMPLE-001::エラーメッセージ XXXX-SAMPLE-002";
        
        // ThreadContextに機能IDを設定
        ThreadContext.put(CommonConstants.FUNC_ID, funcId);
        
        when(messageSource.getMessage(eq(key), any(), eq(Locale.JAPAN)))
                .thenReturn(messageWithReplacePart);
        
        String actualMessage = MessageUtil.getMessage(key);
        
        assertEquals(expectedMessage, actualMessage);
    }
}
