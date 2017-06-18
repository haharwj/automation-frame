package cn.com.frame.compoment;

import tk.mybatis.mapper.common.*;

/**
 * Created by Sirius on 2017/4/10.
 */
public interface CustomMapper<T> extends
        BaseMapper<T>,
        ExampleMapper<T>,
        RowBoundsMapper<T>,
        ConditionMapper<T>,
        Marker {
}
