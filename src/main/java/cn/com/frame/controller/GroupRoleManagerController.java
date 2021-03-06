package cn.com.frame.controller;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.model.SfSGroupRole;
import cn.com.frame.service.GroupRoleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pengbin on 2017/4/13.
 */
@RequestMapping("/grouproleController")
@Controller
public class GroupRoleManagerController extends BaseController {
    @Autowired
    public void setService(GroupRoleManagerService service) {
        super.service = service;
    }

    public GroupRoleManagerController() {
        commonInstance = new SfSGroupRole();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfSGroupRole");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfSGroupRole");
    }
}
