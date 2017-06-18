package cn.com.frame.service;

import cn.com.frame.mapper.SfBCommonChildMapper;
import cn.com.frame.model.SfBCommonChild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 王晨 on 2017/4/13.
 */
@Service
public class CommonChildManagerService extends BaseService {
    @Autowired
    public void setMapper(SfBCommonChildMapper mapper) {
        super.mapper = mapper;
    }

    public CommonChildManagerService() {
        SfBCommonChild sfBCommonChild = new SfBCommonChild();
        commonInstance = sfBCommonChild;
    }
}
