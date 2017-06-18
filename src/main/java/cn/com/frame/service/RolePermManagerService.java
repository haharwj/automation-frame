package cn.com.frame.service;

import cn.com.frame.mapper.SfSRolePermMapper;
import cn.com.frame.model.SfSRolePerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 王晨 on 2017/4/13.
 */
@Service
public class RolePermManagerService extends BaseService {
    @Autowired
    public void setMapper(SfSRolePermMapper mapper) {
        super.mapper = mapper;
    }

    @Autowired
    private SfSRolePermMapper rolePermMapper;
    @Autowired
    private RoleManagerService roleService;

    public RolePermManagerService() {
        SfSRolePerm sfSRolePerm = new SfSRolePerm();
        commonInstance = sfSRolePerm;
    }

    public boolean saveOrUpdateRolePermission(String roleuuid, String key) {
        SfSRolePerm rolePerm = null;
        List<SfSRolePerm> rolePerms = this.findByCondition("a01='"+roleuuid+"'",0,0,null);
        if(rolePerms.size()<=0){
            rolePerm = new SfSRolePerm();
        }else{
            rolePerm = rolePerms.get(0);
        }
        rolePerm.setA01(roleuuid);
        rolePerm.setA10(key);
        rolePerm.setUuid(UUID.randomUUID()+"");
        return this.saveOrUpdate(rolePerm);
    }

    public List<String> findRolePermission(String roleuuid, String rolename){
        List<String> rights = new ArrayList<String>();
        List<SfSRolePerm> rolePerms = this.findByCondition("a01='"+roleuuid+"'",0,0,null);
        if(rolePerms.size()<=0){
            return rights;
        }else{
           String[] perms = rolePerms.get(0).getA10().split(";");
           for(int i = 0;i<perms.length;i++){
               rights.add(perms[i]);
           }
           return rights;
        }
    }
}
