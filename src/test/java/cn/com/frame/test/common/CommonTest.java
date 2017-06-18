package cn.com.frame.test.common;

import cn.com.frame.utils.DateUtils;
import cn.com.frame.utils.FileUtils;
import cn.com.frame.utils.RandomUtils;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class CommonTest {


    @Test
    public void testSimpleDateFromat() {
        String timeFromatedString = DateUtils.getCurrentTimeFromatedString();
        assertNotNull(timeFromatedString);
        System.out.println(timeFromatedString);
    }

    @Test
    public void testRandomString() {
        String randomString = RandomUtils.randomString();
        assertNotNull(randomString);
        System.out.println(randomString);
    }


    @Test
    public void testFileUtls() {
        String fileType = FileUtils.getFileType("sfdsafasfimage");
        System.out.println(fileType);
    }

}
