<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script>

        $(function () {

            //如果顶层窗口不是当前窗口
            if (window.top != window) {
                //将顶层窗口设置为当前窗口
                window.top.location = window.location;
            }


            //在页面加载完毕后，使得用户名文本框自动取得焦点
            $("#loginAct").focus();

            //点击登录
            $("#submitBtn").click(function () {

                login();

            })

            //回车登录
            $(window).keydown(function (event) {

                var code = event.keyCode;

                //如果码值为13，说明是回车键
                if (code == 13) {

                    //执行登录操作
                    login();

                }

            })


        })


        function login() {

            //验证账号密码是否为空

            var loginAct = $.trim($("#loginAct").val());
            var loginPwd = $.trim($("#loginPwd").val());


            if (loginAct == "" || loginPwd == "") {

                $("#msg").html("账号密码不能为空");

                //及时终止掉方法
                return false;

            }

            var flag = "";

            if ($("#flag").prop("checked")) {

                flag = "a";

            }

            //验证账号密码是否正确
            $.ajax({

                url: "settings/user/login.do", //请求路径
                data: {

                    "loginAct": loginAct,
                    "loginPwd": loginPwd,
                    "flag": flag

                },	//请求参数
                type: "post", //请求方式
                dataType: "json", //接收响应信息的方式
                success: function (data) {

                    /*data: {"success":true/false,"msg":?} */

                    if (data.success) {

                        //登录成功
                        //跳转到欢迎页
                        window.location.href = "workbench/toIndex.do";

                    } else {

                        //登录失败
                        $("#msg").html(data.msg);

                    }


                }

            })


        }

    </script>
</head>
<body>
<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
    <img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
</div>
<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
    <div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">
        CRM &nbsp;<span style="font-size: 12px;">&copy;2019&nbsp;xxxx</span></div>
</div>

<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
    <div style="position: absolute; top: 0px; right: 60px;">
        <div class="page-header">
            <h1>登录</h1>
        </div>
        <form class="form-horizontal" role="form">
            <div class="form-group form-group-lg">
                <div style="width: 350px;">
                    <input id="loginAct" class="form-control" type="text" placeholder="用户名">
                </div>
                <div style="width: 350px; position: relative;top: 20px;">
                    <input id="loginPwd" class="form-control" type="password" placeholder="密码">
                </div>
                <div class="checkbox" style="position: relative;top: 30px; left: 10px;">
                    <label>
                        <input id="flag" type="checkbox"> 十天内免登录
                    </label>
                    &nbsp;&nbsp;
                    <span id="msg" style="color: red"></span>
                </div>
                <button id="submitBtn" type="button" class="btn btn-primary btn-lg btn-block"
                        style="width: 350px; position: relative;top: 45px;">登录
                </button>
            </div>
        </form>
    </div>
</div>
</body>
</html>