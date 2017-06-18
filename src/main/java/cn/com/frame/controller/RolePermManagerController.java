package cn.com.frame.controller;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.model.SfSRolePerm;
import cn.com.frame.service.RolePermManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pengbin on 2017/4/13.
 */
@Controller
@RequestMapping("/rolepermController")
public class RolePermManagerController extends BaseController{
    @Autowired
    public void setService(RolePermManagerService service){
        super.service = service;
    }
    public RolePermManagerController() {
        commonInstance = new SfSRolePerm();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfSRolePerm");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfSRolePerm");
    }
}
