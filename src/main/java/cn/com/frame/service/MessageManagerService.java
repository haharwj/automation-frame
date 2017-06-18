package cn.com.frame.service;

import cn.com.frame.mapper.SfBMessageMapper;
import cn.com.frame.model.SfBMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 王晨 on 2017/4/13.
 */
@Service
public class MessageManagerService extends BaseService {
    @Autowired
    public void setMapper(SfBMessageMapper mapper){
        super.mapper = mapper;
    }

    public MessageManagerService() {
        SfBMessage sfBMessage = new SfBMessage();
        commonInstance = sfBMessage;
    }
}
