package cn.com.frame.filter;

import cn.com.frame.common.tools.file.PropertiesReader;
import cn.com.frame.common.tools.reflect.SystemReflect;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class WebAppContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent event) {
        // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent event) {
        // TODO Auto-generated method stub
        String[] tableArr = new String[]{"cn.com.frame.model.SfBCommonChild", "cn.com.frame.model.SfBConfig", "cn.com.frame.model.SfBMessage"
                , "cn.com.frame.model.SfBQuickcommon", "cn.com.frame.model.SfBSmallcommon", "cn.com.frame.model.SfSDatacode"
                , "cn.com.frame.model.SfSGroup", "cn.com.frame.model.SfSGroupRole", "cn.com.frame.model.SfSLog", "cn.com.frame.model.SfSRole"
                , "cn.com.frame.model.SfSRolePerm", "cn.com.frame.model.SfSUser", "cn.com.frame.model.SfSUserGroup", "cn.com.frame.model.SfSUserRole"};
        SystemReflect systemReflect = new SystemReflect();
        systemReflect.reflect(tableArr);
        //System.out.println("========获取Spring WebApplicationContext");
        new PropertiesReader().init();
        //启动h2数据库
//        System.out.println("========启动h2");
    }

}
