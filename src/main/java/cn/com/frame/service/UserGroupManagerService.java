package cn.com.frame.service;

import cn.com.frame.mapper.SfSUserGroupMapper;
import cn.com.frame.model.SfSUserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王晨 on 2017/4/13.
 */
@Service
public class UserGroupManagerService extends BaseService {
    @Autowired
    public void setMapper(SfSUserGroupMapper mapper){
        super.mapper = mapper;
    }
    @Autowired
    private SfSUserGroupMapper userGroupMapper;

    public UserGroupManagerService(){
        SfSUserGroup usergroup = new SfSUserGroup();
        commonInstance = usergroup;
    }
    /**
     * 保存或者更新用户的组织
     */
    public boolean saveOrUpdateUserGroup(String useruuid,String username,String groupuuid,String groupid,String groupname){
        SfSUserGroup userGroup = null;
        List<SfSUserGroup> userGroups = this.findByCondition("useruuid='"+useruuid+"'and groupuuid='"+groupuuid+"'",0,0,null);
        if(userGroups.size()<=0){
            userGroup = new SfSUserGroup();
        }else{
            userGroup = userGroups.get(0);
        }
        userGroup.setUseruuid(useruuid);
        userGroup.setUsername(username);
        userGroup.setGroupname(groupname);
        userGroup.setGroupuuid(groupuuid);
        userGroup.setGroupid(groupid);
        return this.saveOrUpdate(userGroup);
    }
    /**
     * 删除用户的组织机构
     */
    public boolean deleteUserGroup(String useruuid){
        List<SfSUserGroup> userGroups = this.findByCondition("useruuid='"+useruuid+"'",0,0,null);
        if(userGroups.size()<=0){
            return false;
        }else{
            return this.delete("id='"+userGroups.get(0).getId()+"'");
        }
    }
    /**
     * 获取到相同组织机构的所有用户的uuid
     */
    public List saveAllGroupUser(String useruuid){
        List<String> uuids = new ArrayList<String>();
        SfSUserGroup userGroup = null;
        List<SfSUserGroup> userGroups = this.findByCondition("useruuid='"+useruuid+"'",0,0,null);
        if(userGroups.size()<=0){
            return uuids;
        }else{
            userGroup = userGroups.get(0);
            List<SfSUserGroup> userGroups2 = this.findByCondition("groupuuid='"+userGroup.getGroupuuid()+"'",0,0,null);
            for(SfSUserGroup usergroup:userGroups2){
                uuids.add(usergroup.getUseruuid());
            }
            return uuids;
        }
    }
}
