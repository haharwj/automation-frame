package cn.com.frame.service;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.utils.SqlUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* service的超类
* */
public abstract class BaseService {
    protected Mapper mapper;

    protected Object commonInstance;

    public Object getCommonInstance() {
        return commonInstance;
    }

    public void setCommonInstance(Object commonInstance) {
        this.commonInstance = commonInstance;
    }

    /**
     * 通用方法findByCondition
     * 可以按照条件分页查询数据
     * *
     * Params:
     * start         分页开始位置
     * limit         分页每页数量
     * condition     查询条件
     * orderby       排序条件
     * *
     * Returns:
     * List类型     结果List
     */
    public List findByCondition(String condition, int start, int limit, String orderby) {
        List result = new ArrayList();
        condition = new SqlUtils().checkSql(condition, commonInstance);
        Example example = new Example(commonInstance.getClass());
        example.createCriteria().andCondition(condition);
        if (orderby != null) {
            if (orderby.matches(".*[Dd][Ee][Ss][Cc]")) {
                example.orderBy((orderby.replaceAll("[Dd][Ee][Ss][Cc]", "")).trim()).desc();
            } else if (orderby.matches(".*[Aa][Ss][Cc]")) {
                example.orderBy((orderby.replaceAll("[Aa][Ss][Cc]", "")).trim()).asc();
            } else {
                example.orderBy(orderby.trim()).asc();
            }
        }
        start = start >= 0 ? start : 0;
        limit = limit > 0 ? limit : (limit = 100000);
        result = mapper.selectByExampleAndRowBounds(example, new RowBounds(start, limit));
        return result;
    }

    /**
     * 通用方法findCommonListCount
     * 可以按照条件查询数据总数
     * *
     * Params:
     * condition     查询条件
     * *
     * Returns:
     * int类型        结果总数
     */
    public int findCountByCondition(String condition) {
        condition = new SqlUtils().checkSql(condition, commonInstance);
        Example example = new Example(commonInstance.getClass());
        example.createCriteria().andCondition(condition);
        return mapper.selectCountByExample(example);
    }

    /**
     * 通用方法saveOrUpdate
     * 新增或修改指定实体
     * *
     * Params:
     * object         新增或修改的实体
     * *
     * Returns:
     * boolean类型    true/false是否执行成功
     */
    public Boolean saveOrUpdate(Object commonInstance) {
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        CommonReflect commonReflect = new CommonReflect();
        commonReflect.setTableName(commonInstance);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        if (commonReflect.invokeGetMethod(commonInstance, "id") != null && ((Long) commonReflect.invokeGetMethod(commonInstance, "id")) != 0L) {
            commonReflect.invokeSetMethod(commonInstance, "updater", session.getAttribute("SYS_USER_UUID"));
            commonReflect.invokeSetMethod(commonInstance, "updateday", time);
            commonReflect.invokeSetMethod(commonInstance, "updatetime", time.toString());
            int result = mapper.updateByPrimaryKey(commonInstance);
            return result > 0;
        } else {
            commonReflect.invokeSetMethod(commonInstance, "id", null);
            commonReflect.invokeSetMethod(commonInstance, "owner", session.getAttribute("SYS_USER_UUID"));
            commonReflect.invokeSetMethod(commonInstance, "createday", time);
            int result = mapper.insert(commonInstance);
            return result > 0;
        }
    }

    /**
     * 通用方法delete
     * 按条件删除数据
     * *
     * Params:
     * condition      删除的条件
     * type
     * *
     * Returns:
     * boolean类型    是否执行成功true/false
     */
    public boolean delete(String condition) {
        int result = 0;
        if (!condition.equals("")) {
            condition = new SqlUtils().checkSql(condition, commonInstance);
            Example example = new Example(commonInstance.getClass());
            example.createCriteria().andCondition(condition);
            result = mapper.deleteByExample(example);
        }
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }
}