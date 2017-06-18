package cn.com.frame.controller;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.model.SfSUserRole;
import cn.com.frame.service.UserRoleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 王晨 on 2017/4/11.
 */
@Controller
@RequestMapping("/userRoleController")
public class UserRoleManagerController extends BaseController{
    @Autowired
    public void setService(UserRoleManagerService service){
        super.service = service;
    }
    public UserRoleManagerController() {
        commonInstance = new SfSUserRole();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfSUserRole");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfSUserRole");
    }
}
