package cn.com.frame.controller;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.model.SfBCommonChild;
import cn.com.frame.service.CommonChildManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pengbin on 2017/4/13.
 */
@Controller
@RequestMapping("/commonchildController")
public class CommonChildManagerController extends BaseController{
    @Autowired
    public void setService(CommonChildManagerService service){
        super.service = service;
    }
    public CommonChildManagerController() {
        commonInstance = new SfBCommonChild();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfBCommonChild");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfBCommonChild");
    }

}
