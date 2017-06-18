package cn.com.frame.service;

import cn.com.frame.mapper.SfSLogMapper;
import cn.com.frame.model.SfSLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 王晨 on 2017/4/13.
 */
@Service
public class LogManagerService extends BaseService {
    @Autowired
    public void setMapper(SfSLogMapper mapper){
        super.mapper = mapper;
    }

    public LogManagerService() {
        SfSLog sfSLog = new SfSLog();
        commonInstance = sfSLog;
    }
}
