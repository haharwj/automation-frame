package cn.com.frame.secure.filter;

import cn.com.frame.common.util.ParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Sirius on 2017/6/14.
 */
public class DefaultNoSessionHttpPermissionFilter implements Filter {

    private static Logger logger = null;

    @Override
    public void init(FilterConfig config) {
        logger = LoggerFactory.getLogger(DefaultNoSessionHttpPermissionFilter.class);
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);
        String url = request.getRequestURI();
        if (url.equals(request.getContextPath()) || url.equals(request.getContextPath() + "/")) {
            chain.doFilter(req, response);
            return;
        }

        for (int i = 0; i < ParamUtil.commonType.length; i++) {
            if (url.lastIndexOf(".") != -1 && url.substring(url.lastIndexOf(".") + 1).equals(ParamUtil.commonType[i])) {
                chain.doFilter(req, response);
                return;
            }
        }

        for (int i = 0; i < ParamUtil.commonPage.length; i++) {
            if (url.indexOf("?") != -1 ? url.substring(0, url.indexOf("?")).equals(ParamUtil.commonPage[i]) : url.equals(ParamUtil.commonPage[i])) {
                chain.doFilter(req, response);
                return;
            }
        }

        for (int i = 0; i < ParamUtil.commonAccess.length; i++) {
            if (url.indexOf(ParamUtil.commonAccess[i]) != -1) {
                chain.doFilter(req, response);
                return;
            }
        }


        // 获取session用户
        if (session == null || session.getAttribute("SYS_USER") == null) {
            logger.info("----敏感监测----匿名用户访问地址：" + url + "；IP地址：" + request.getRemoteAddr() + "；服务：" + request.getContextPath() + "；服务器地址：" + request.getServerName() + "；协议：" + request.getProtocol() + "；调用类型：" + request.getMethod());
            response.getOutputStream().write("sessionout".getBytes());
            return;
        }
        // 执行请求
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        logger = null;
    }
}
