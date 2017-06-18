<%--
  Created by IntelliJ IDEA.
  User: 王晨
  Date: 2017/4/26
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<form action="userManager/login.html" method="post">
    <input type="username" name="username" placeholder="请输入用户名"><span></span><br/>
    <input type="password" name="password" placeholder="请输入密码"><span></span><br/>
    <div onclick="changeImg()">
        <img id="img" src="userManager/getVcode" />
    </div>
    <div >
        <input id="vcode" name="vcode"  type="text"  placeholder="输入验证码">
    </div>
    <input type="submit" value="登录">
</form>


<!-- 触发JS刷新-->
<script type="text/javascript">
    function changeImg(){
        var img = document.getElementById("img");
        img.src = "userManager/getVcode?date="+new Date();
    }
</script>
</body>
</html>
