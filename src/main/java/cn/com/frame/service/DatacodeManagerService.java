package cn.com.frame.service;

import cn.com.frame.mapper.SfSDatacodeMapper;
import cn.com.frame.model.SfSDatacode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 王晨 on 2017/4/13.
 */
@Service
public class DatacodeManagerService extends BaseService {
    @Autowired
    public void setMapper(SfSDatacodeMapper mapper){
        super.mapper = mapper;
    }

    public DatacodeManagerService() {
        SfSDatacode sfSDatacode = new SfSDatacode();
        commonInstance = sfSDatacode;
    }
}
