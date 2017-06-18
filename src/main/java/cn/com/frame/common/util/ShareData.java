package cn.com.frame.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 共享数据源，提供整个服务器共享数据
 * @author 杜乐
 * 
 * Date : 2012-8-3
 */
public class ShareData {
    /**
     * session共享区
     */
    public static Map sessionmap = new HashMap();
}
