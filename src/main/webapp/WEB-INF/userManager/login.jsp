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
    <title>用户登录之后</title>
    <script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="../jslib/system/tools/util/baseframe.js"></script>
    <script src="../jslib/system/tools/util/securty.js"></script>
    <script>
        $(function(){
           var success =  ${success};
           if(true==success){
               alert("登录成功");
           }else{
               alert("登录失败");
               window.location.href="http://localhost:8088/frame/login.jsp";
           }
           $("#configRole").click(function () {
               alert("准备配置用户角色");
               $.ajax({
                   url:"http://localhost:8088/frame/userManager/configUserRole",
                    data:{
                       useruuid:"4acee043-069a-4538-b8ac-902da6ffae94",
                        rolename:"admin"
                    },
                   dataType:"json",
                   error:function(request,ststus,errorThrow){
                        this;
                   },
                   success:function(data){
                       alert(data.success);
                   }

                   });
           });
            $("#deleteUserRole").click(function () {
                alert("删除用户角色");
                $.ajax({
                    url:"http://localhost:8088/frame/userManager/deteleUserRole",
                    data:{
                        useruuid:"4acee043-069a-4538-b8ac-902da6ffae94",
                    },
                    dataType:"json",
                    error:function(request,ststus,errorThrow){
                        this;
                    },
                    success:function(data){
                        alert(data.success);
                    }

                });
            });
            $("#findRolePermission").click(function () {
                alert("获取用户权限");
                $.ajax({
                    url:"http://localhost:8088/frame/userManager/findRolePermission",
                    data:{
                        roleuuid:"asdasddeeerrrghgsdfsfaaczxvbcb",
                        rolename:"admin"
                    },
                    dataType:"json",
                    error:function(request,ststus,errorThrow){
                        this;
                    },
                    success:function(data){
                        alert(data.displayList);
                    }

                });
            });
            $("#saveOrUpdateRolePermission").click(function () {
                alert("插入或更新用户权限");
                $.ajax({
                    url:"http://localhost:8088/frame/userManager/saveOrUpdateRolePermission",
                    data:{
                        roleuuid:"asdasddeeerrrghgsdfsfaaczxvbcb",
                        key:"eat;sleep;play"
                    },
                    dataType:"json",
                    error:function(request,ststus,errorThrow){
                        this;
                    },
                    success:function(data){
                        alert(data.success);
                    }

                });
            });
            $("#saveOrUpdateuserGroup").click(function () {
                alert("更新用户的组织机构");
                $.ajax({
                    url:"http://localhost:8088/frame/userManager/saveOrUpdateUserGroup",
                    data:{
                       useruuid:"4acee043-069a-4538-b8ac-902da6ffae94",
                        username:"wjj",
                        groupuuid:"qqqqwwwwweeee",
                        groupname:"泰森科技",
                        groupid:"shanxitaiyuan"
                    },
                    dataType:"json",
                    error:function(request,ststus,errorThrow){
                        this;
                    },
                    success:function(data){
                        alert(data.success);
                    }

                });
            });
            $("#deleteUserGroup").click(function () {
                alert("删除用户的组织机构");
                $.ajax({
                    url:"http://localhost:8088/frame/userManager/deleteUserGroup",
                    data:{
                        useruuid:"4acee043-069a-4538-b8ac-902da6ffae94"
                    },
                    dataType:"json",
                    error:function(request,ststus,errorThrow){
                        this;
                    },
                    success:function(data){
                        alert(data.success);
                    }

                });
            });
            $("#getSameGroupUseruuid").click(function () {
                alert("获取到相同组织机构的所有用户的uuid");
                $.ajax({
                    url:"http://localhost:8088/frame/userManager/getSameGroupUseruuid",
                    type:"post",
                    data:{
                        useruuid:"4acee043-069a-4538-b8ac-902da6ffae94"
                    },
                    dataType:"json",
                    error:function(request,ststus,errorThrow){
                        this;
                    },
                    success:function(data){
                        alert(data.displayList);
                    }

                });
            });


            $("#userList").click(function () {
                alert("用户列表");
                $.ajax({
                    url:"http://localhost:8088/frame/userManager/list",
                    type:"post",
                    data:{
                        condition :S.D("a01='_user'"),
                        orderby:S.D("asc"),
                        start:S.D("0"),
                        limit:S.D("5"),
                        nopage:S.D("false")
                    },
                    dataType:"json",
                    error:function(request,ststus,errorThrow){
                        this;
                    },
                    success:function(data){
                        alert(data.success);
                        alert(data.list);
                        alert(data.count);
                    }

                });
            });

            $("#userManager").click(function () {
                alert("用户增改");
                $.ajax({
                    url:"http://localhost:8088/frame/userManager/saveOrUpdate",
                    type:"post",
                    data:{
                        id:"-1",
                        a01:"_user",
                        a10:"王麻子",
                        a11:"${SYS_USER_USERNAME}",
                        a13:"男",
                        a14:"1",
                        a15:"lower"
                    },
                    dataType:"json",
                    error:function(request,ststus,errorThrow){
                        this;
                    },
                    success:function(data){
                        alert(data.success);
                        alert(data.instance);
                    }

                });
            });
            $("#deleteUser").click(function () {
                alert("删除用户");
                $.ajax({
                    url:"http://localhost:8088/frame/userManager/delete",
                    type:"post",
                    data:{
                       id:S.D("47")
                    },
                    dataType:"json",
                    error:function(request,ststus,errorThrow){
                        this;
                    },
                    success:function(data){
                        alert(data.success);
                    }

                });
            });


            $("#groupList").click(function () {
                alert("组织机构列表及查询接口");
                $.ajax({
                    url:"http://localhost:8088/frame/groupController/list",
                    type:"post",
                    data:{
                        condition :S.D("a01='_group'"),
                        orderby:S.D("asc"),
                        start:S.D("0"),
                        limit:S.D("5"),
                        nopage:S.D("true")
                    },
                    dataType:"json",
                    error:function(request,ststus,errorThrow){
                        this;
                    },
                    success:function(data){
                        alert(data.success);
                        alert(data.list);
                        alert(data.count);
                    }

                });
            });
            $("#groupManager").click(function () {
                alert("组织机构信息的增加修改");
                $.ajax({
                    url:"http://localhost:8088/frame/groupController/saveOrUpdate",
                    type:"post",
                    data:{
                        a01:" _group",
                        groupid:"qwdscfvr",
                        groupname:"泰森",
                    },
                    dataType:"json",
                    error:function(request,ststus,errorThrow){
                        this;
                    },
                    success:function(data){
                        alert(data.success);
                        alert(data.instance);
                    }

                });
            });

            /*$("#pushSessionByUser").click(function () {
                alert("根据用户设置session");
                $.ajax({
                    url:"http://localhost:8088/frame/userManager/pushSessionByUser",
                    type:"post",
                    data:{
                        user:
                    },
                    dataType:"json",
                    error:function(request,ststus,errorThrow){
                        this;
                    },
                    success:function(data){
                        alert(data.success);
                        alert(data.instance);
                    }

                });
            });*/

        });

    </script>
</head>
<body>

${success}
${loginUser.id}
${loginUser.uuid}
${loginUser.a01}
${loginUser.a10}
${loginUser.a11}
${loginUser.a12}<br/>
<button id="configRole">为用户配置角色</button><br/>
<button id="deleteUserRole">删除用户角色</button><br/>
<button id="findRolePermission">获取用户权限</button><br/>
<button id="saveOrUpdateRolePermission">写入用户权限</button><br/>

<hr/>
<button id="saveOrUpdateuserGroup">更新用户的组织机构</button><br/>
<button id="deleteUserGroup">删除用户的组织机构</button><br/>
<button id="getSameGroupUseruuid">获取到相同组织机构的所有用户的uuid</button><br/>

<hr/>
<h2>用户管理</h2>
<button id="userList">用户列表及查询接口</button><br/>
<button id="userManager">用户信息的增加修改</button><br/>
<button id="deleteUser">删除用户</button><br/>

<h2>组织管理</h2>
<button id="groupList">组织机构列表及查询接口</button><br/>
<button id="groupManager">组织机构信息的增加修改</button><br/>

<h2>pushSessionByUser</h2>
<button id="pushSessionByUser">pushSessionByUser</button>

</body>
</html>
