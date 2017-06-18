package cn.com.frame.service;

import cn.com.frame.mapper.SfSRoleMapper;
import cn.com.frame.model.SfSRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 王晨 on 2017/4/13.
 */
@Service
public class RoleManagerService extends BaseService{
    @Autowired
    public void setMapper(SfSRoleMapper mapper){
        super.mapper = mapper;
    }

    public RoleManagerService() {
        SfSRole sfSRole = new SfSRole();
        commonInstance = sfSRole;
    }






}
