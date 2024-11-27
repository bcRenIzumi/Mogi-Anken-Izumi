package jp.co.benesse.web.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 管理者情報エンティティ
 *
 * 作成日：2024/11/21
 * </pre>
 *
 * @author ren_izumi
 * @version 1.0
 */
@Getter
@Setter
public class MstAdminEntity {

    /** 管理者ID */
    private String adminId;

    /** 管理者氏名 */
    private String adminName;

    /** パスワード（SHA-256でハッシュ化） */
    private String password;

    /** 管理者ロールコード */
    private String roleCode;

    /** 論理削除フラグ */
    private String logicDelFlg;

    /** CREATE_BY（作成者） */
    private String createBy;

    /** CREATE_TIME（作成日時） */
    private LocalDateTime createTime;

    /** UPDATE_BY（更新者） */
    private String updateBy;

    /** UPDATE_TIME（更新日時） */
    private LocalDateTime updateTime;
}
