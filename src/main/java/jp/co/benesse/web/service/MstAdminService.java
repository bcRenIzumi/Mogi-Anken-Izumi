package jp.co.benesse.web.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jp.co.benesse.web.entity.MstAdminEntity;
import jp.co.benesse.web.exception.NoSuchRecordException;
import jp.co.benesse.web.exception.WebParamException;
import jp.co.benesse.web.exception.WebUnexpectedException;
import jp.co.benesse.web.repository.MstAdminRepository;
import jp.co.benesse.web.util.MessageUtil;

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
     * @param adminId 管理者情報ID
     * @return オプト
     * @throws WebUnexpectedException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchRecordException
     * @throws WebParamException
     */
    public List<MstAdminEntity> getAdminList(String adminId, String password)
            throws WebUnexpectedException, NoSuchAlgorithmException, NoSuchRecordException {

        String hashedPassword = toHash(password);
        List<MstAdminEntity> result = mstAdminRepository.selectByIdAndPass(adminId, hashedPassword);
        if (result.size() == 0 || result == null) {
            throw new NoSuchRecordException(MessageUtil.getMessage("db.select.error.message", "管理者情報"));
        }
        return result;
    }

    /**
     * 文字列を指定したSHA-256でハッシュ化する
     * 
     * @param text ハッシュ化する文字列
     * @return ハッシュ化された文字列。
     * @throws NoSuchAlgorithmException
     */
    private static String toHash(String text) throws NoSuchAlgorithmException {

        StringBuffer sb = new StringBuffer();

        MessageDigest md = MessageDigest.getInstance("SHA256");
        byte[] cipherBytes = md.digest(text.getBytes());

        for (int i = 0; i < cipherBytes.length; i++) {
            sb.append(String.format("%02x", cipherBytes[i] & 0xff));
        }

        return sb.toString();
    }
}
