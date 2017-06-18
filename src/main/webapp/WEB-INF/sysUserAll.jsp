<%--
  Created by IntelliJ IDEA.
  User: dule
  Date: 2017/4/1
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="jslib/system/tools/util/baseframe.js"></script>
    <script src="jslib/system/tools/util/securty.js"></script>
</head>
<body>
<script>
    function test() {
        $.ajax({
            cache: true,
            type: "post",
            url: "quickcommonController/delete",
            data: {
                id: S.D("30"),
                condition: S.D(" id>20 ")
//                clean:S.D("uuid,a01"),
//                sort:S.D("id desc"),
//                orderby:S.D("id desc"),
//                child:S.D(true),
//                sortchild:S.D("id desc")
//                a01:"test",a02:"test",a03:"test"
            },
            async: false,
            error: function (request) {
            },
            success: function (data) {
                var list = eval(data);

                alert(data);
                for (var i = 0; i < list.length; i++) {
                    alert(list[i].a11);
                }
            }

        });
    }
    function q() {
        $.ajax({
            cache: true,
            type: "post",
            url: "userManager/configUserRole",
            data: {
                useruuid: "qqqqqqqqqqqqqq",
                rolename: "管理员 "
            },
            async: false,
            error: function (request) {
            },
            success: function (data) {
                var list = eval(data);

                alert(data);
                for (var i = 0; i < list.length; i++) {
                    alert(list[i].a11);
                }
            }

        });
    }


    function w() {
        $.ajax({
            cache: true,
            type: "post",
            url: "userManager/updateUserRole",
            data: {
                useruuid: "qqqqqqqqqqqqqq",
                username: "wangchen",
                rolename: "系统管理员",
                roleid: "sys_permission"
            },
            async: false,
            error: function (request) {
            },
            success: function (data) {
                var list = eval(data);
                alert(data);
                for (var i = 0; i < list.length; i++) {
                    alert(list[i].a11);
                }
            }

        });
    }
    function e() {
        $.ajax({
            cache: true,
            type: "post",
            url: "userManager/deteleUserRole",
            data: {
                useruuid: "qqqqqqqqqqqqqq"

            },
            async: false,
            error: function (request) {
            },
            success: function (data) {
                var list = eval(data);
                alert(data);
                for (var i = 0; i < list.length; i++) {
                    alert(list[i].a11);
                }
            }

        });
    }
    function qq() {
        $.ajax({
            cache: true,
            type: "post",
            url: "userManager/saveOrUpdateUserGroup",
            data: {
                useruuid: "qqqqqqqqqqqqqq",
                username: "wangchen",
                groupuuid: "tertetetr",
                groupname: "山西泰森",
                groupid: "weqe"

            },
            async: false,
            error: function (request) {
            },
            success: function (data) {
                var list = eval(data);
                alert(data);
                for (var i = 0; i < list.length; i++) {
                    alert(list[i].a11);
                }
            }

        });
    }
    function ww() {
        $.ajax({
            cache: true,
            type: "post",
            url: "userManager/deleteUserGroup",
            data: {
                useruuid: "qqqqqqqqqqqqqq"

            },
            async: false,
            error: function (request) {
            },
            success: function (data) {
                var list = eval(data);
                alert(data);
                for (var i = 0; i < list.length; i++) {
                    alert(list[i].a11);
                }
            }

        });
    }
    function qqq() {
        $.ajax({
            cache: true,
            type: "post",
            url: "userManager/findRolePermission",
            data: {
                useruuid: "qqqqqqqqqqqqqq",
                rolename: "系统管理员"
            },
            async: false,
            error: function (request) {
            },
            success: function (data) {
                var list = eval(data);
                alert(data);
                for (var i = 0; i < list.length; i++) {
                    alert(list[i].a11);
                }
            }

        });
    }
    function www() {
        $.ajax({
            cache: true,
            type: "post",
            url: "userManager/saveOrUpdateRolePermission",
            data: {
                roleuuid: "qqqqqqqqqqqqqq",
                key: "吃饭,睡觉,打豆豆"
            },
            async: false,
            error: function (request) {
            },
            success: function (data) {
                var list = eval(data);
                alert(data);
                for (var i = 0; i < list.length; i++) {
                    alert(list[i].a11);
                }
            }

        });
    }
    function asd() {
        $.ajax({
            type: "post",
            url: "userManager/login.html",
            data: {
                username: "qqqqqqqqqqqqqq",
                password: "178954621",
                encode:"true",
                vcode:"qwer",
            },
            async: false,
            error: function (request) {
            },
            success: function (data) {
                var list = eval(data);
                alert(data);
                for (var i = 0; i < list.length; i++) {
                    alert(list[i].a11);
                }
            }

        });
    }
    function zxc() {
        $.ajax({
            type: "post",
            url: "userManager/regedit.html",
            data: {
                username: "qqqqqqqqqqqqqq",
                password: "178954621",
                vcode:"qwer"
            },
            async: false,
            error: function (request) {
            },
            success: function (data) {
                var list = eval(data);
                alert(data);
                for (var i = 0; i < list.length; i++) {
                    alert(list[i].a11);
                }
            }

        });
    }


</script>
<button onclick="test()">aaaaa</button>
<button onclick="test1()">aaaaa</button>
<button onclick="test2()">aaaaa</button>
<hr/>
<button onclick="zxc()">注册 </button>
<button onclick="asd()">登陆</button>
<hr/>
<button onclick="q()">配置用户角色</button>
<button onclick="w()">修改用户的角色</button>
<button onclick="e()">删除用户的角色</button>


<hr/>
<button onclick="qq()">更新用户的组织机构</button>
<button onclick="ww()">删除用户的组织机构</button>

<hr/>
<button onclick="qqqq()">获取用户权限</button>
<button onclick="www()">更新用户权限</button>


</body>
</html>
