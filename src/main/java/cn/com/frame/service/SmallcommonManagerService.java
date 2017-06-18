package cn.com.frame.service;

import cn.com.frame.mapper.SfBSmallcommonMapper;
import cn.com.frame.model.SfBSmallcommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 王晨 on 2017/4/13.
 */
@Service
public class SmallcommonManagerService extends BaseService {
    @Autowired
    public void setMapper(SfBSmallcommonMapper mapper){
        super.mapper = mapper;
    }

    public SmallcommonManagerService() {
        SfBSmallcommon sfBSmallcommon = new SfBSmallcommon();
        commonInstance = sfBSmallcommon;
    }
}
