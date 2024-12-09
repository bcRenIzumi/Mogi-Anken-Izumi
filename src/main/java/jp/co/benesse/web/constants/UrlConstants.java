package jp.co.benesse.web.constants;

/**
 * <pre>
 * URL一覧
 * 
 * 作成日：2024/06/18
 * 更新日：2024/06/18
 * </pre>
 * 
 * @author BC)yoda
 * @version 1.0
 */
public class UrlConstants {

    /**
     * コンストラクタ
     */
    private UrlConstants() {
    }

    /** リダイレクト */
    public static final String REDIRECT = "redirect:";

    /** サンプル画面 */
    public static final String VIEW_SAMPLE = "/sample";

    /** システムエラー */
    public static final String VIEW_ERROR = "/system_error";

    /** 管理者ログイン画面 */
    public static final String ADMIN_LOGIN = "/login";

    /** メニュー画面 */
    public static final String ADMIN_MENU = "/admin-menu";

    /** ログアウト */
    public static final String LOGOUT = "/logout";
}
