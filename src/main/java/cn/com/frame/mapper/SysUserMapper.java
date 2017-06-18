package cn.com.frame.mapper;

import cn.com.frame.model.SysUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends Mapper<SysUser> {

    public List<SysUser> getUserList(Map<String, Object> params);

}