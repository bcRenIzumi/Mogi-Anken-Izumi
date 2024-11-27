package jp.co.benesse.web.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jp.co.benesse.web.entity.MstAdminEntity;
import jp.co.benesse.web.entity.SampleEntity;
import jp.co.benesse.web.exception.WebParamException;
import jp.co.benesse.web.exception.WebUnexpectedException;
import jp.co.benesse.web.repository.MstAdminRepository;
import jp.co.benesse.web.repository.SampleRepository;
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
     * @throws WebParamException
     */
    public List<MstAdminEntity> getAdminList(String adminId, String hashedPassword)
            throws WebUnexpectedException, WebParamException {

        List<MstAdminEntity> result = mstAdminRepository.selectByIdAndPass(adminId, hashedPassword);
        return result;
    }
}
