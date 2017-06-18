package cn.com.frame.common.tools.file;

import cn.com.frame.common.util.ParamUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Sirius on 2017/6/14.
 */
public class PropertiesReader {

    public PropertiesReader() {

    }

    public void init() {
        // 读取配置文件
        InputStream input = null;
        try {
            input = this.getClass().getResourceAsStream("/permission-pages.properties");
            Properties properties = new Properties();
            properties.load(input);
            String pagestring = properties.getProperty("permission");
            String[] split = pagestring.split(",");
            ParamUtil.commonPage = split;
            pagestring = properties.getProperty("action");
            split = pagestring.split(",");
            ParamUtil.commonAccess = split;
            pagestring = properties.getProperty("tail");
            split = pagestring.split(",");
            ParamUtil.commonType = split;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
