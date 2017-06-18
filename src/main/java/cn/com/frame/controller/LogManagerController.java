package cn.com.frame.controller;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.model.SfSLog;
import cn.com.frame.service.LogManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pengbin on 2017/4/13.
 */
@Controller
@RequestMapping("/logController")
public class LogManagerController extends BaseController{
    @Autowired
    public void setService(LogManagerService service){
        super.service = service;
    }
    public LogManagerController() {
        commonInstance = new SfSLog();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfSLog");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfSLog");
    }
}
