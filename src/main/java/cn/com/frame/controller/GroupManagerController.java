package cn.com.frame.controller;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.model.SfSGroup;
import cn.com.frame.service.GroupManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pengbin on 2017/4/13.
 */
@Controller
@RequestMapping("/groupController")
public class GroupManagerController extends BaseController{
    @Autowired
    public void setService(GroupManagerService service){
        super.service = service;
    }
    public GroupManagerController() {
        commonInstance = new SfSGroup();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfSGroup");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfSGroup");
    }
}
