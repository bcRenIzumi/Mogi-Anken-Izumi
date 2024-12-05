package jp.co.benesse.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 管理者ログイン用フォーム
 *
 * 作成日：2024/11/29
 * </pre>
 *
 * @author ren_izumi
 * @version 1.0
 */
@Getter
@Setter
public class AdminLoginForm {

    /** 管理者ID */
    @NotBlank
    @Size(max = 16)
    // @Pattern(regexp = "^[a-zA-Z0-9]*$", message =
    // "{validationError.alphanumeric.message}")
    private String adminId;

    /** パスワード */
    @NotBlank
    @Size(max = 16)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "{validationError.alphanumeric.message}")
    private String password;
}
