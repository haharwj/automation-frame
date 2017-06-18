package cn.com.frame.service;

import cn.com.frame.common.builder.Sys;
import cn.com.frame.mapper.SfSGroupMapper;
import cn.com.frame.mapper.SfSRoleMapper;
import cn.com.frame.mapper.SfSUserMapper;
import cn.com.frame.mapper.SfSUserRoleMapper;
import cn.com.frame.model.SfSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by 王晨 on 2017/4/13.
 */
@Service
public class UserManagerService extends BaseService {
    @Autowired
    private SfSUserMapper userMapper;
    @Autowired
    private SfSUserRoleMapper userRoleMapper;
    @Autowired
    private SfSRoleMapper roleMapper;
    @Autowired
    private SfSGroupMapper groupMapper;

    @Autowired
    private void setMapper(SfSUserMapper mapper) {
        super.mapper = mapper;
    }


    public UserManagerService() {
        SfSUser sfsuser = new SfSUser();
        commonInstance = sfsuser;
    }

    /**
     * 用户名是否重复  重复返回true
     * @param username
     */
    public boolean isDuplicate(String username) {
        SfSUser user = new SfSUser();
        user.setA11(username);
        SfSUser user2 = userMapper.selectOne(user);
        if (user2 == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 用户的注册
     */
    public boolean register(String username, String password) {
        if (isDuplicate(username)) {
            return false;
        } else {
            SfSUser user = new SfSUser();
            user.setUuid(UUID.randomUUID()+"");
            user.setA11(username);
            user.setA12(Sys.getSecurityTools().md5Security(password));
            user.setCreateday(new Date());
            user.setOwner(username);
            int i = userMapper.insert(user);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        }
    }
    /**
     *通过username和password返回一个user对象
     */
    public SfSUser needSfsUser(String username,String password){
        SfSUser user = new SfSUser();
        user.setA11(username);
        user.setA12(password);
        return userMapper.selectOne(user);
    }
}
