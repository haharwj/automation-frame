package cn.com.frame.controller;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.model.SfBMessage;
import cn.com.frame.service.MessageManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pengbin on 2017/4/13.
 */
@Controller
@RequestMapping("/messageController")
public class MessageManagerController extends BaseController{
    @Autowired
    public void setService(MessageManagerService service){
        super.service = service;
    }
    public MessageManagerController() {
        commonInstance = new SfBMessage();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfBMessage");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfBMessage");
    }
}
