package cn.com.frame.service;

import cn.com.frame.mapper.SfSGroupRoleMapper;
import cn.com.frame.model.SfSGroupRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 王晨 on 2017/4/13.
 */
@Service
public class GroupRoleManagerService extends BaseService {
    @Autowired
    public void setMapper(SfSGroupRoleMapper mapper){
        super.mapper = mapper;
    }

    public GroupRoleManagerService() {
        SfSGroupRole sfSGroupRole = new SfSGroupRole();
        commonInstance = sfSGroupRole;
    }
}
