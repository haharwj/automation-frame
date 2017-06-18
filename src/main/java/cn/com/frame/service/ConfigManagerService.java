package cn.com.frame.service;

import cn.com.frame.mapper.SfBConfigMapper;
import cn.com.frame.model.SfBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 王晨 on 2017/4/13.
 */
@Service
public class ConfigManagerService extends BaseService {
    @Autowired
    public void setMapper(SfBConfigMapper mapper){
        super.mapper = mapper;
    }

    public ConfigManagerService() {
        SfBConfig sfBConfig = new SfBConfig();
        commonInstance = sfBConfig;
    }
}
