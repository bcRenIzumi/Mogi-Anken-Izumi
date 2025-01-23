package jp.co.benesse.web.util;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

/**
 * StringUtil用のテストクラス
 * 
 */
public class StringUtilTest {

    @Test
    public void lengthNormTest() {
        String str = "test";
        assertEquals(4, StringUtil.length(str));
    }

    @Test
    public void lengthNullTest() {
        String str = null;
        assertEquals(0, StringUtil.length(str));
    }

    @Test
    public void lengthZeroTest() {
        String str = "";
        assertEquals(0, StringUtil.length(str));
    }

    @Test
    public void hashStringNormTest() throws NoSuchAlgorithmException {
        String str = "pass";
        assertEquals("d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1", StringUtil.hashString(str));
    }

    @Test
    public void hashStringNullTest() throws NoSuchAlgorithmException {
        String str = null;
        assertEquals(null, StringUtil.hashString(str));
    }

    @Test
    public void hashStringZeroTest() throws NoSuchAlgorithmException {
        String str = "";
        assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", StringUtil.hashString(str));
    }
}
