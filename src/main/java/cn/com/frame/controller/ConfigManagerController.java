package cn.com.frame.controller;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.model.SfBConfig;
import cn.com.frame.service.ConfigManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pengbin on 2017/4/13.
 */
@Controller
@RequestMapping("/configController")
public class ConfigManagerController extends BaseController{
    @Autowired
    public void setService(ConfigManagerService service){
        super.service = service;
    }
    public ConfigManagerController() {
        commonInstance = new SfBConfig();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfBConfig");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfBConfig");
    }
}
