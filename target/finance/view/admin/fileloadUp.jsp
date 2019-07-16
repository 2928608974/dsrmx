
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/statics/style/authority/basic_layout.css" rel="stylesheet"
          type="text/css">
    <link href="${pageContext.request.contextPath}/statics/style/authority/common_style.css" rel="stylesheet"
          type="text/css">
    <title>文件上传</title>
    <script type="text/javascript">
        function myclose(){
            parent.$.fancybox.close();
            <%--location.href="${pageContext.request.contextPath}/front/frontUser"--%>
        }
    </script>

</head>
<body >
  <div style="width: 600px;height: 200px;border: 1px solid black;margin: 0 auto" >
   <form action="${pageContext.request.contextPath}/admin/fileLoadUP" method="post" enctype="multipart/form-data">
       <h3>请选择需要上传的文件</h3>
       <input type="file"   name="file"/>
       <input type="submit" value="提交" class="ui_input_btn01" />

   </form>

   <a  href="javascript:myclose();">关闭</a>
  </div>
</body>
</html>
