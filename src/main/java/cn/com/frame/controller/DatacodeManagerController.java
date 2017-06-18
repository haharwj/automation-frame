package cn.com.frame.controller;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.model.SfSDatacode;
import cn.com.frame.service.DatacodeManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pengbin on 2017/4/13.
 */
@Controller
@RequestMapping("/datacodeController")
public class DatacodeManagerController extends BaseController{
    @Autowired
    public void setService(DatacodeManagerService service){
        super.service = service;
    }

    public DatacodeManagerController() {
        commonInstance = new SfSDatacode();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfSDatacode");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfSDatacode");
    }
}
