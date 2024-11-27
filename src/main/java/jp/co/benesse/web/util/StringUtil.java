package jp.co.benesse.web.util;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Objects;

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
}
