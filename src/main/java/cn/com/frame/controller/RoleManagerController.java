package cn.com.frame.controller;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.model.SfSRole;
import cn.com.frame.service.RoleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pengbin on 2017/4/13.
 */
@Controller
@RequestMapping("/roleController")
public class RoleManagerController extends BaseController{
    @Autowired
    public void setService(RoleManagerService service){
        super.service = service;
    }
    public RoleManagerController() {
        commonInstance = new SfSRole();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfSRole");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfSRole");
    }
}
