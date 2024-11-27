package jp.co.benesse.web.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 管理者ログイン画面の表示用DTO
 * 
 * <pre>
 * 作成日 : 2024/11/22
 * </pre>
 * 
 * @author ren_izumi
 * @version 1.0
 */
@Getter
@Setter
public class AdminLoginScreenDto {

    /** エラーメッセージ */
    private String errorMessage;
}
