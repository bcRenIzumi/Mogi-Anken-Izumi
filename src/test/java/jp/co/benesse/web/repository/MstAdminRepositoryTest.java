package jp.co.benesse.web.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import jakarta.transaction.Transactional;
import jp.co.benesse.web.BaseTest;
import jp.co.benesse.web.CsvDataSetLoader;
import jp.co.benesse.web.entity.MstAdminEntity;
import jp.co.benesse.web.exception.WebUnexpectedException;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@Transactional
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class MstAdminRepositoryTest extends BaseTest {

    /** テスト対象クラス */
    @Autowired
    private MstAdminRepository dao;

    @Test
    @DatabaseSetup(value = "classpath:dao/MstAdminRepository/")
    public void selectByIdAndPassTest() throws WebUnexpectedException {

        // 期待値作成
        MstAdminEntity expected = new MstAdminEntity();
        expected.setAdminId("A001");
        expected.setAdminName("test");
        expected.setPassword("d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1");
        expected.setRoleCode("01");
        expected.setLogicDelFlg("0");
        expected.setCreateBy("admin1");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime createTime = LocalDateTime.parse("2024-11-21 04:44:58.240", formatter);
        expected.setCreateTime(createTime);
        expected.setUpdateBy("admin");
        LocalDateTime upDateTime = LocalDateTime.parse("2024-11-21 04:44:58.240", formatter);
        expected.setUpdateTime(upDateTime);

        // テスト対象メソッド実行
        MstAdminEntity result = dao
                .selectByIdAndPass("A001", "d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1").get(0);

        // samePropertyValuesAsを使ってentityの他の項目も丸ごと比較が望ましい
        // 不要な項目が取得されていないか、確認ができる
        assertThat(result, is(samePropertyValuesAs(expected)));
    }

    @Test
    @DatabaseSetup(value = "classpath:dao/MstAdminRepository/")
    public void selectByIdAndPassNullTest() throws WebUnexpectedException {

        // テスト対象メソッド実行
        List<MstAdminEntity> result = dao
                .selectByIdAndPass(null, null);

        assertEquals(result.size(), 0);
    }

    @Test
    @DatabaseSetup(value = "classpath:dao/MstAdminRepository/")
    public void selectByIdAndPassAdminIdNullTest() throws WebUnexpectedException {

        // テスト対象メソッド実行
        List<MstAdminEntity> result = dao
                .selectByIdAndPass("A002", "pass");

        assertEquals(result.size(), 0);
    }
}
