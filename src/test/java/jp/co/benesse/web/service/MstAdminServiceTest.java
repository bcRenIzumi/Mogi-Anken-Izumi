package jp.co.benesse.web.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.servlet.http.HttpSession;
import jp.co.benesse.web.BaseTest;
import jp.co.benesse.web.constants.UrlConstants;
import jp.co.benesse.web.entity.MstAdminEntity;
import jp.co.benesse.web.exception.NoSuchRecordException;
import jp.co.benesse.web.exception.WebUnexpectedException;
import jp.co.benesse.web.repository.MstAdminRepository;

@ExtendWith(MockitoExtension.class)
public class MstAdminServiceTest extends BaseTest {

    @InjectMocks
    private MstAdminService mstAdminService;

    @Mock
    private MstAdminRepository mstAdminRepository;

    @Test
    public void getAdminListTest() throws NoSuchAlgorithmException, WebUnexpectedException, NoSuchRecordException {

        List<MstAdminEntity> result = new ArrayList<>();
        MstAdminEntity entity = new MstAdminEntity();
        result.add(entity);
        when(mstAdminRepository.selectByIdAndPass(any(), any())).thenReturn(result);
        assertEquals(result, mstAdminService.getAdminList("test", "test"));
    }

    @Test
    public void getAdminNoListTest() throws NoSuchAlgorithmException, WebUnexpectedException, NoSuchRecordException {

        List<MstAdminEntity> result = new ArrayList<>();
        when(mstAdminRepository.selectByIdAndPass(any(), any())).thenReturn(result);
        assertThrows(NoSuchRecordException.class, () -> {
            mstAdminService.getAdminList("test", "test");
        });
    }
}
