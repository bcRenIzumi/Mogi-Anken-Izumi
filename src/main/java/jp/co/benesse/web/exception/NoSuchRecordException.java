package jp.co.benesse.web.exception;

/**
 * <pre>
 * レコード取得不可Exception
 *
 * 作成日：2024/12/02
 * </pre>
 *
 * @author ren_izumi
 * @version 1.0
 */
public class NoSuchRecordException extends WebBaseException {

    /**
     * コンストラクタ
     * 
     * @param errorMessage エラーメッセージ
     */
    public NoSuchRecordException(String errorMessage) {
        super(errorMessage);
    }
}
