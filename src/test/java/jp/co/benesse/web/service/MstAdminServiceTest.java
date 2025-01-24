package jp.co.benesse.web.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    @Test
    public void hashPasswordTest()
            throws NoSuchMethodException, NoSuchAlgorithmException, WebUnexpectedException, NoSuchRecordException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Method method = MstAdminService.class.getDeclaredMethod("hashPassword", String.class);
        method.setAccessible(true);

        String result = (String) method.invoke(mstAdminService, "pass");
        assertEquals(result, "d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1");
    }

    @Test
    public void hashPasswordNullTest()
            throws NoSuchMethodException, NoSuchAlgorithmException, WebUnexpectedException, NoSuchRecordException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Method method = MstAdminService.class.getDeclaredMethod("hashPassword", String.class);
        method.setAccessible(true);

        String result = (String) method.invoke(mstAdminService, (Object) null);
        assertEquals(result, null);
    }
}
