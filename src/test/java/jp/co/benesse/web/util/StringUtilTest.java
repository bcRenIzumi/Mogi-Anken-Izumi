package jp.co.benesse.web.util;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

/**
 * StringUtil用のテストクラス
 * 
 */
public class StringUtilTest {

    /**
     * length:ケース1
     */
    @Test
    public void lengthCase1() {
        String str = "test";
        assertEquals(4, StringUtil.length(str));
    }

    /**
     * length:ケース2
     */
    @Test
    public void lengthCase2() {
        String str = null;
        assertEquals(0, StringUtil.length(str));
    }

    /**
     * length:ケース3
     */
    @Test
    public void lengthCase3() {
        String str = "";
        assertEquals(0, StringUtil.length(str));
    }

    /**
     * hashString:ケース1
     * 
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void hashStringCase1() throws NoSuchAlgorithmException {
        String str = "pass";
        assertEquals("d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1", StringUtil.hashString(str));
    }

    /**
     * hashString:ケース2
     * 
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void hashStringCase2() throws NoSuchAlgorithmException {
        String str = null;
        assertEquals(null, StringUtil.hashString(str));
    }

    /**
     * hashString:ケース3
     * 
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void hashStringCase3() throws NoSuchAlgorithmException {
        String str = "";
        assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", StringUtil.hashString(str));
    }
}
