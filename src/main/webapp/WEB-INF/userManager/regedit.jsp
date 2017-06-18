<%--
  Created by IntelliJ IDEA.
  User: 王晨
  Date: 2017/4/25
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>注册页面</title>
    <script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="jslib/system/tools/util/baseframe.js"></script>
    <script src="jslib/system/tools/util/securty.js"></script>

    <script>
        var success =  ${success};
        alert(success);
        if(true==success){
            alert("注册成功");
            window.location.href="http://localhost:8088/frame/login.jsp";
        }else{
            alert("注册失败");
            window.location.href="http://localhost:8088/frame/register.jsp";
        }
    </script>
</head>
<body>
${success}

</body>
</html>
