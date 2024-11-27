package jp.co.benesse.web.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.benesse.web.entity.MstAdminEntity;
import jp.co.benesse.web.entity.SampleEntity;
import jp.co.benesse.web.exception.WebUnexpectedException;

/**
 * <pre>
 * 管理者情報マスタリポジトリ
 *
 * 作成日：2024/11/21
 * </pre>
 *
 * @author ren_izumi
 * @version 1.0
 */
@EnableAutoConfiguration
@Repository
public class MstAdminRepository extends SqlGeneratorBaseRepository {

    /**
     * IDとパスワードから管理者情報取得
     * 
     * @param adminId        管理者ID
     * @param hashedPassword ハッシュ化したパスワード
     * @return オプト
     * @throws WebUnexpectedException
     */
    public List<MstAdminEntity> selectByIdAndPass(String adminId, String hashedPassword) throws WebUnexpectedException {

        // SQLに渡すパラメータを設定
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("adminId", adminId)
                .addValue("hashedPass", hashedPassword);
        // 動的なSQLの作成
        String sql = getSql(params);
        return kgwebjt.query(sql, params, new BeanPropertyRowMapper<>(MstAdminEntity.class));
    }
}
