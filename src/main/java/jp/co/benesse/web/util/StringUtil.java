package jp.co.benesse.web.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <pre>
 * Stringに関するユーティリティクラス.
 *
 * 作成日：2024/11/26
 * </pre>
 *
 * @author ren_izumi
 * @version 1.0
 */
public class StringUtil {

    /**
     * コンストラクタ
     */
    private StringUtil() {
    }

    /**
     * 長さを判定(nullは0返却)
     * 
     * @param str 対象文字列
     * @return 長さ
     */
    public static int length(String str) {
        return (str != null) ? str.length() : 0;
    }

    /**
     * 文字列をSHA-256でハッシュ化する
     * 
     * @param text ハッシュ化する文字列
     * @return ハッシュ化された文字列。
     * @throws NoSuchAlgorithmException
     */
    public static String hashString(String text) throws NoSuchAlgorithmException {

        StringBuffer sb = new StringBuffer();

        MessageDigest md = MessageDigest.getInstance("SHA256");
        byte[] cipherBytes = md.digest(text.getBytes());

        for (int i = 0; i < cipherBytes.length; i++) {
            sb.append(String.format("%02x", cipherBytes[i] & 0xff));
        }

        return sb.toString();
    }
}
