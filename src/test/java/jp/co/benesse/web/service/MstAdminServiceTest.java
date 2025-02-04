package jp.co.benesse.web.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
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
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import ch.qos.logback.core.util.StringUtil;
import jp.co.benesse.web.BaseTest;
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

    /**
     * getAdminList:ケース1
     * 
     * @throws NoSuchAlgorithmException
     * @throws WebUnexpectedException
     * @throws NoSuchRecordException
     */
    @Test
    public void getAdminListCase1() throws NoSuchAlgorithmException, WebUnexpectedException, NoSuchRecordException {

        List<MstAdminEntity> result = new ArrayList<>();
        MstAdminEntity entity = new MstAdminEntity();
        result.add(entity);
        when(mstAdminRepository.selectByIdAndPass(any(), any())).thenReturn(result);
        assertEquals(result, mstAdminService.getAdminList("test", "test"));
    }

    /**
     * getAdminList:ケース2
     * 
     * @throws NoSuchAlgorithmException
     * @throws WebUnexpectedException
     * @throws NoSuchRecordException
     */
    @Test
    public void getAdminNoListCase2() throws NoSuchAlgorithmException, WebUnexpectedException, NoSuchRecordException {

        List<MstAdminEntity> result = new ArrayList<>();
        when(mstAdminRepository.selectByIdAndPass(any(), any())).thenReturn(result);
        assertThrows(NoSuchRecordException.class, () -> {
            mstAdminService.getAdminList("test", "test");
        });
    }

    /**
     * getAdminList:ケース3
     * 
     * @throws NoSuchAlgorithmException
     * @throws WebUnexpectedException
     * @throws NoSuchRecordException
     */
    @Test
    public void getAdminListCase3()
            throws NoSuchAlgorithmException, WebUnexpectedException, NoSuchRecordException {

        doThrow(new WebUnexpectedException("error")).when(mstAdminRepository).selectByIdAndPass(any(), any());
        assertThrows(WebUnexpectedException.class, () -> {
            mstAdminService.getAdminList("test", "test");
        });
    }

    /**
     * getAdminList:ケース4
     * 
     * @throws NoSuchAlgorithmException
     * @throws WebUnexpectedException
     * @throws NoSuchRecordException
     */
    @Test
    public void getAdminListCase4()
            throws NoSuchAlgorithmException, WebUnexpectedException, NoSuchRecordException {

        List<MstAdminEntity> result = new ArrayList<>();
        MstAdminEntity entity = new MstAdminEntity();
        result.add(entity);

        MstAdminService mstAdminServiceSpy = spy(MstAdminService.class);
        doThrow(new NoSuchAlgorithmException()).when(mstAdminServiceSpy).hashPassword(any());

        assertThrows(NoSuchAlgorithmException.class, () -> {
            mstAdminServiceSpy.getAdminList("test", "test");
        });
    }

    /**
     * hashPassword:ケース1
     * 
     * @throws NoSuchMethodException
     * @throws NoSuchAlgorithmException
     * @throws WebUnexpectedException
     * @throws NoSuchRecordException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    @Test
    public void hashPasswordCase1()
            throws NoSuchMethodException, NoSuchAlgorithmException, WebUnexpectedException, NoSuchRecordException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Method method = MstAdminService.class.getDeclaredMethod("hashPassword", String.class);
        method.setAccessible(true);

        String result = (String) method.invoke(mstAdminService, "pass");
        assertEquals(result, "d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1");
    }

    /**
     * hashPassword:ケース2
     * 
     * @throws NoSuchMethodException
     * @throws NoSuchAlgorithmException
     * @throws WebUnexpectedException
     * @throws NoSuchRecordException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    @Test
    public void hashPasswordCase2()
            throws NoSuchMethodException, NoSuchAlgorithmException, WebUnexpectedException, NoSuchRecordException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Method method = MstAdminService.class.getDeclaredMethod("hashPassword", String.class);
        method.setAccessible(true);

        String result = (String) method.invoke(mstAdminService, (Object) null);
        assertEquals(result, null);
    }

    /**
     * hashPassword:ケース3
     * 
     * @throws NoSuchMethodException
     * @throws NoSuchAlgorithmException
     * @throws WebUnexpectedException
     * @throws NoSuchRecordException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    @Test
    public void hashPasswordCase3()
            throws NoSuchMethodException, NoSuchAlgorithmException, WebUnexpectedException, NoSuchRecordException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Method method = MstAdminService.class.getDeclaredMethod("hashPassword", String.class);
        method.setAccessible(true);

        String result = (String) method.invoke(mstAdminService, "");
        assertEquals(result, "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855");
    }
}
