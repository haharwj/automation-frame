package cn.com.frame.service;

import cn.com.frame.mapper.SfSGroupMapper;
import cn.com.frame.model.SfSGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 王晨 on 2017/4/13.
 */
@Service
public class GroupManagerService extends BaseService {
    @Autowired
    public void setMapper(SfSGroupMapper mapper){
        super.mapper = mapper;
    }

    public GroupManagerService() {
        SfSGroup sfSGroup = new SfSGroup();
        commonInstance = sfSGroup;
    }
}
