<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>登录</title>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/statics/scripts/jquery/jquery-1.7.1.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/style/authority/all_style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/style/authority/index_style.css"/>

    <style type="text/css">
        #top {
            color: #38BCA4;
            font-size: 20px;
            font-weight: bold;
            margin: 0 auto;
            text-align: center;
            padding-top: 50px;

        }

    </style>


</head>
<body>
<div id="login_center">
    <div id="top">
        长沙市金融办<br/>
        非法经营案个人资金返还查询系统
    </div>
    <div class="register">
        <table cellspacing="0" cellpadding="0" width="630px">

            <tr>
                <td>姓名：</td>
                <td>
                    <input type="text" id="userName" onblur="userNameCheck()" placeholder="请输入用户名称"/>
                    <span></span>
                </td>
            </tr>
            <tr>
                <td>身份证号码：</td>
                <td><input type="text" id="idCard" onblur="idcardCheck()" placeholder="请输入身份证号码"/> <span></span></td>
            </tr>
            <tr>
                <td>手机号码：</td>
                <td><input type="number" onblur="phoneCheck()" id="userMobile" placeholder="请输入正确的手机号码"/> <span></span>
                </td>
            </tr>
            <tr>
                <td>验证码：</td>
                <td>
                    <input type="number" onblur="codeCheck()" id="code" class="re_yan"/><input type="button"
                                                                                               value="获取验证码"
                                                                                               class="re_yan_btn obtain generate_code"
                                                                                               onclick="settime(this);"/>
                    <span class="cuo">请输入正确的验证码</span>
                </td>
            </tr>

            <tr>
                <td></td>
                <td>
                    <button onclick="dologin()">立即登录</button>
                </td>
            </tr>
        </table>
    </div>


</div>

</body>


<script type="text/javascript">


    /*回车事件*/
    function EnterPress(e) { //传入 event
        var e = e || window.event;
        if (e.keyCode == 13) {
            $("#submitForm").attr("action", "/admin/userlogin").submit();
        }
    }

    //倒计时
    var countdown = 60;


    function settime(val) {
        if (countdown == 0) {
            val.removeAttribute("disabled");
            val.value = "获取验证码";
            countdown = 60;
            return false;
        } else {
            if (countdown == 60) {
                var phone = $('#userMobile').val();
                $.get(
                    "${pageContext.request.contextPath}/front/SendCode/" + phone
                )
            }

            // 异步请求发送验证码
            val.setAttribute("disabled", true);
            val.value = "重新发送(" + countdown + ")";
            countdown--;
        }
        setTimeout(function () {
            settime(val);
        }, 1000);
    }

    /**
     *  姓名非空验证
     */

    function userNameCheck() {
        var userName = $("#userName").val();
        if (userName.length <= 0) {

            $("#userName").next().html("*请输入姓名");
            return false;
        }
        $("#userName").next().html("");
        return true;

    }


    /**

     *  验证身份证
     */
    function idcardCheck() {
        var idcard = $("#idcard").val();
        var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if (reg.test(idcard)) {
            $("#idcard").next().html("");
            return true;
        }

        $("#idcard").next().html("*身份证格式错误");
        return false;
    }

    /**
     * 验证码验证
     */
    function codeCheck() {

        if ($("#code").val() == undefined || $("#code").val() == null || $("#code").val() == '') {

            $("#code").next().html("*请输入验证码");
            return false;
        }
        $("#code").next().html("");
        return true;
    }

    /**
     *  手机号码格式验证
     */
    function phoneCheck() {
        var user_mobile = $("#user_mobile").val();
        var regMobile = /^(?:\+?86)?1(?:3\d{3}|5[^4\D]\d{2}|8\d{3}|7(?:[01356789]\d{2}|4(?:0\d|1[0-2]|9\d))|9[189]\d{2}|6[567]\d{2}|4(?:[14]0\d{3}|[68]\d{4}|[579]\d{2}))\d{6}$/;

        if (!regMobile.test(user_mobile)) {

            $("#user_mobile").next().html("手机格式不正确");
            return true;
        }
        return true;
    }


    /**
     * 登录验证
     * @returns {boolean}
     */
    function dologin() {
        //  if (userNameCheck() && idcardCheck() && codeCheck() && phoneCheck()) {
        //获取用户名
        var userName = $('#userName').val();
        //获取密码
        var idCard = $('#idCard').val();
        //获取手机号码
        var phone = $('#userMobile').val();
        //获取验证码
        var code = $('#code').val();
        // 页面非空和格式验证通过  异步提交
        $.post(
            "${pageContext.request.contextPath}/front/indexCode",
            {"userName": userName, "idCard": idCard, "phone": phone, "code": code},
            function (result) {
                if (result == 0) {
                    /*账号密码错误*/
                    alert("账号或密码错误,请检查后重新登陆");
                } else if (result == -1) {
                    /*验证码错误*/
                    alert("验证码错误");
                } else {
                    alert("登陆成功");
                    location.href = "${pageContext.request.contextPath}/front/index";
                }
            }
        )


        //   }

    }
</script>
</html>