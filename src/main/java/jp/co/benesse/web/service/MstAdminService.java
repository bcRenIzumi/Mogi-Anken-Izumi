package jp.co.benesse.web.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import jp.co.benesse.web.entity.MstAdminEntity;
import jp.co.benesse.web.exception.NoSuchRecordException;
import jp.co.benesse.web.exception.WebParamException;
import jp.co.benesse.web.exception.WebUnexpectedException;
import jp.co.benesse.web.repository.MstAdminRepository;
import jp.co.benesse.web.util.MessageUtil;
import jp.co.benesse.web.util.StringUtil;

/**
 * <pre>
 * 管理者情報のサービス
 *
 * 作成日：2024/11/21
 * </pre>
 *
 * @author ren_izumi
 * @version 1.0
 */
@Service
public class MstAdminService {

    /** テスト */
    @Autowired
    private MstAdminRepository mstAdminRepository;

    /**
     * 管理者情報取得
     * 
     * @param adminId  管理者情報ID
     * @param password パスワード
     * @return 管理者情報リスト
     * @throws WebUnexpectedException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchRecordException
     */
    public List<MstAdminEntity> getAdminList(String adminId, String password)
            throws WebUnexpectedException, NoSuchAlgorithmException, NoSuchRecordException {

        String hashedPassword = hashPassword(password);
        List<MstAdminEntity> result = mstAdminRepository.selectByIdAndPass(adminId, hashedPassword);
        if (CollectionUtils.isEmpty(result)) {
            throw new NoSuchRecordException(MessageUtil.getMessage("db.select.error.message", "管理者情報"));
        }
        return result;
    }

    /**
     * パスワードをハッシュ化する
     * 
     * @param password ハッシュ化前のパスワード
     * @return ハッシュ化されたパスワード
     * @throws NoSuchAlgorithmException
     */
    private static String hashPassword(String password) throws NoSuchAlgorithmException {

        return StringUtil.hashString(password);
    }
}
