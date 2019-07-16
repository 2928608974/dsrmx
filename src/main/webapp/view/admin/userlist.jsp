<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/statics/scripts/jquery/jquery-1.7.1.js"></script>
    <link href="${pageContext.request.contextPath}/statics/style/authority/basic_layout.css" rel="stylesheet"
          type="text/css">
    <link href="${pageContext.request.contextPath}/statics/style/authority/common_style.css" rel="stylesheet"
          type="text/css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/statics/scripts/authority/commonAll.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/statics/scripts/fancybox/jquery.fancybox-1.3.4.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/statics/scripts/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/statics/style/authority/jquery.fancybox-1.3.4.css"
          media="screen"></link>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/statics/scripts/artDialog/artDialog.js?skin=default"></script>
    <title>信息管理系统</title>
    <script type="text/javascript">
        $(document).ready(function () {

            /**  分页跳转 */
            $(".page").click(function () {
                var valueName = $(this).attr("value");

                if ("首页" == valueName) {
                    $("#pageNum").val(1);

                } else if ("上一页" == valueName) {
                    var pageIndex = $("#pageNum").val();
                    $("#pageNum").val(parseInt(pageIndex)  - 1);

                }
                else if ("下一页" == valueName) {
                    var pageIndex = $("#pageNum").val();
                    $("#pageNum").val(parseInt(pageIndex) + 1);

                } else if ("尾页" == valueName) {

                    var pages = $("#totalPages").val();
                    $("#pageNum").val(pages);
                }
                // 提交表单
                $("#submitForm").submit();

            })


            /** 新增   **/
            $("#addBtn").fancybox({
                'href': '${pageContext.request.contextPath}/admin/addfrontuser',
                'width': 733,
                'height': 530,
                'type': 'iframe',
                'hideOnOverlayClick': false,
                'showCloseButton': true,
                'onClosed': function () {
                    window.location.href = '/front/frontUser';
                }
            });

            /** 导入  **/
            $("#importBtn").fancybox({
                'href': '/admin/infile',
                'width': 633,
                'height': 260,
                'type': 'iframe',
                'hideOnOverlayClick': false,
                'showCloseButton': false,
                'onClosed': function () {
                    window.location.href = '${pageContext.request.contextPath}/front/frontUser';
                }
            });

            /**编辑   **/
            $("a.edit").fancybox({
                'width': 733,
                'height': 530,
                'type': 'iframe',
                'hideOnOverlayClick': false,
                'showCloseButton': true,
                'onClosed': function () {
                    window.location.href = '${pageContext.request.contextPath}/front/frontUser';
                }
            });
        });
        /** 用户角色   **/
        var userRole = '';

        /** 模糊查询来电用户  **/
        function search() {
            $("#submitForm").attr("action", "${pageContext.request.contextPath}/front/frontUser").submit();
        }


        /** Excel导出  **/
        function exportExcel() {
            if (confirm('您确定要导出吗？')) {
               location.href="${pageContext.request.contextPath}/admin/userExport";
            }
        }


        /** 删除 **/
        function del(fyID) {
            // 非空判断
            if (fyID == '') return;
            if (confirm("您确定要删除吗？")) {
                $("#submitForm").attr("action", "/xngzf/archives/delFangyuan.action?fyID=" + fyID).submit();
            }
        }

        /** 批量删除 **/
        function batchDel() {
            if ($("input[name='IDCheck']:checked").size() <= 0) {
                art.dialog({icon: 'error', title: '友情提示', drag: false, resize: false, content: '至少选择一条', ok: true,});
                return;
            }
            // 1）取出用户选中的checkbox放入字符串传给后台,form提交
            var allIDCheck = "";
            $("input[name='IDCheck']:checked").each(function (index, domEle) {
                bjText = $(domEle).parent("td").parent("tr").last().children("td").last().prev().text();
// 			alert(bjText);
                // 用户选择的checkbox, 过滤掉“已审核”的，记住哦
                if ($.trim(bjText) == "已审核") {
// 				$(domEle).removeAttr("checked");
                    $(domEle).parent("td").parent("tr").css({color: "red"});
                    $("#resultInfo").html("已审核的是不允许您删除的，请联系管理员删除！！！");
// 				return;
                } else {
                    allIDCheck += $(domEle).val() + ",";
                }
            });
            // 截掉最后一个","
            if (allIDCheck.length > 0) {
                allIDCheck = allIDCheck.substring(0, allIDCheck.length - 1);
                // 赋给隐藏域
                $("#allIDCheck").val(allIDCheck);

                if (confirm("您确定要批量删除这些记录吗？")) {
                    // 提交form
                    $("#submitForm").attr("action", "/front/del").submit();
                }
            }
        }


        /** 输入页跳转 **/
        function jumpInputPage(totalPage) {
            // 如果“跳转页数”不为空
            if ($("#jumpNumTxt").val() != '') {
                var pageNum = parseInt($("#jumpNumTxt").val());
                // 如果跳转页数在不合理范围内，则置为1
                if (pageNum < 1 || pageNum > totalPage) {
                    art.dialog({
                        icon: 'error',
                        title: '友情提示',
                        drag: false,
                        resize: false,
                        content: '请输入合适的页数，\n自动为您跳到首页',
                        ok: true,
                    });
                    pageNum = 1;
                }
                $("#pageNum").val(pageNum);
                $("#submitForm").attr("action", "${pageContext.request.contextPath}/front/frontUser").submit();
            }else {
                // “跳转页数”为空
                art.dialog({
                    icon: 'error',
                    title: '友情提示',
                    drag: false,
                    resize: false,
                    content: '请输入合适的页数，\n自动为您跳到首页',
                    ok: true,
                });
                $("#submitForm").attr("action", "${pageContext.request.contextPath}/front/frontUser?pageNum=" + 1).submit();
            }
        }
    </script>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<form id="submitForm" name="submitForm" action="${pageContext.request.contextPath}/front/frontUser" method="get">

    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>

                    <div id="box_center">
                        用户名称
                        <input name="userName" style="height:30px" value="${myuser.userName}"/>
                        身份证
                        <input name="idCard" style="height:30px" value="${myuser.idCard}"/>
                        手机号码
                        <input name="phone" style="height:30px" value="${myuser.phone}"/>
                        是否确认
                        <input type="radio" name="state" value=""/>全部
                        <input type="radio" name="state" value="0" <c:if test="${myuser.state==0}">checked</c:if>/>确认
                        <input type="radio" name="state" value="1" <c:if test="${myuser.state==1}">checked</c:if>/>未确认
                        <input type="button" value="查询" class="ui_input_btn01" onclick="search();"/>

                    </div>
                    <div id="box_bottom">

                        <input type="button" value="新增" class="ui_input_btn01 " id="addBtn"/>
                        <input type="button" value="删除" class="ui_input_btn01 " onclick="batchDel();"/>
                        <input type="button" value="导入" class="ui_input_btn01 " id="importBtn"/>
                        <input type="button" value="导出" class="ui_input_btn01 " onclick="exportExcel();"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th>#</th>
                        <th width="30"><input type="checkbox" id="all" onclick="selectOrClearAllCheckbox(this);"/>
                        </th>

                        <th>ID</th>
                        <th>名称</th>
                        <th>身份证</th>
                        <th>手机号码</th>
                        <th>地区</th>
                        <th>邮寄地址</th>
                        <th>金钱</th>
                        <th>状态</th>
                        <th>备注</th>

                        <th>操作</th>
                    </tr>

                    <c:forEach var="user" items="${frontUserPage.getResult()}" varStatus="state">
                        <tr>
                            <td>${state.index+1}</td>
                            <td><input type="checkbox" name="IDCheck" value="${user.userId}" class="acb"/></td>

                            <td>${user.userId}</td>
                            <td>${user.userName}</td>
                            <td>${user.idCard}</td>
                            <td>${user.phone}</td>
                            <td>${user.province}${user.city}${user.area}</td>
                            <td>${user.address}</td>
                            <td>${user.money}</td>
                            <td>
                                <c:if test="${user.state==0}">已确认</c:if>
                                <c:if test="${user.state==1}">未确认</c:if>
                            </td>
                            <td>${user.remark}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/front/frontById?uid=${user.userId}" class="edit" >编辑</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="ui_tb_h30">
                <div class="ui_flt" style="height: 30px; line-height: 30px;">
                    共有
                    <span class="ui_txt_bold04">${frontUserPage.getTotal()}</span>
                    条记录，当前第
                    <span class="ui_txt_bold04">${frontUserPage.getPageNum()}
						/
                        ${frontUserPage.getPages()}</span>
                    页
                </div>
                <div class="ui_frt">
                    <!--    如果是第一页，则只显示下一页、尾页 -->
                    <input id="pageNum" name="pageNum" value="${frontUserPage.getPageNum()}" type="hidden"/>
                    <input id="totalPages" name="pages" value="${frontUserPage.getPages()}" type="hidden"/>

                    <input type="button" value="首页" class="ui_input_btn01 page" />
                    <input type="button" value="上一页" class="ui_input_btn01 page"/>
                    <input type="button" value="下一页" class="ui_input_btn01 page"/>
                    <input type="button" value="尾页" class="ui_input_btn01 page"/>
                    <!--     如果是最后一页，则只显示首页、上一页 -->
                    转到第<input type="text" id="jumpNumTxt" class="ui_input_txt01"/>页
                    <input type="button" class="ui_input_btn01" value="跳转" onclick="jumpInputPage(${frontUserPage.getPages()});"/>
                </div>
            </div>
        </div>
    </div>
</form>
<div style="display:none">
    <script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script>
</div>
</body>

</html>

