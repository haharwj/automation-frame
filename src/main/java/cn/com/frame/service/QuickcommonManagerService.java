package cn.com.frame.service;

import cn.com.frame.mapper.SfBQuickcommonMapper;
import cn.com.frame.model.SfBQuickcommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 王晨 on 2017/4/13.
 */
@Service
public class QuickcommonManagerService extends BaseService {
    @Autowired
    public void setMapper(SfBQuickcommonMapper mapper){
        super.mapper = mapper;
    }
    public QuickcommonManagerService()
    {
        SfBQuickcommon sfBQuickcommon= new SfBQuickcommon();
        commonInstance = sfBQuickcommon;
    }
}
