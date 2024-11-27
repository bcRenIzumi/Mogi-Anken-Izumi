package jp.co.benesse.web.constants;

/**
 * <pre>
 * メッセージ一覧
 * 
 * 作成日：2024/11/22
 * </pre>
 * 
 * @author ren_izumi
 * @version 1.0
 */
public class MessageConstants {

    /**
     * コンストラクタ
     */
    private MessageConstants() {
    }

    /** 管理者ログイン画面：ID or パスワードの未入力 */
    public static final String ADMIN_NOT_ENTERED = "入力必須項目です";

    /** 管理者ログイン画面：ID or パスワードのどちらかが17文字以上の場合 */
    public static final String ADMIN_LENGTH_OVER = "ID、パスワードは16文字以下です";

    /** 管理者ログイン画面：ID or パスワードのどちらかに半角英数字以外が使われている場合 */
    public static final String ADMIN_USE_ILLEGAL_CHAR = "IDとパスワードに用いられるのは半角英数字のみです";

    /** 管理者ログイン画面：ID or パスワードのどちらかが間違っている場合 */
    public static final String ADMIN_WRONG_ID_PASS = "管理者IDまたはパスワードが違います";
}
