package cn.com.frame.controller;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.model.SfBQuickcommon;
import cn.com.frame.service.QuickcommonManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pengbin on 2017/4/13.
 */
@Controller
@RequestMapping("/quickcommonController")
public class QuickcommonManagerController extends BaseController{
    @Autowired
    public void setService(QuickcommonManagerService service){
        super.service = service;
    }
    public QuickcommonManagerController() {
        commonInstance = new SfBQuickcommon();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfBQuickcommon");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfBQuickcommon");
    }
}
