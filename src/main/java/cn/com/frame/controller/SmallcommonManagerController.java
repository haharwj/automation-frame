package cn.com.frame.controller;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.model.SfBSmallcommon;
import cn.com.frame.service.SmallcommonManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pengbin on 2017/4/13.
 */
@Controller
@RequestMapping("/smallcommonController")
public class SmallcommonManagerController extends BaseController{
    @Autowired
    public void setService(SmallcommonManagerService service){
        super.service = service;
    }
    public SmallcommonManagerController() {
        commonInstance = new SfBSmallcommon();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfBSmallcommon");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfBSmallcommon");
    }
}
