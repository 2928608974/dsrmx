<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-07-02
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图片上传</title>
</head>
<body>
<h2><font color="#a52a2a" size="4">图片上传</font></h2>

<form action="/front/uploadFile" method="post" enctype="multipart/form-data">
    <input type="file" name="uploadFile" accept="image/*"><br><br>
    <input type="submit" value="上传">
</form>
<br>
<h2><font color="#a52a2a" size="4">图片显示</font></h2>

<img src="/image/${newFileName}" width="200px" height="180px">
<br>
<h4><font color="#a52a2a" size="4">文件名: ${newFileName}</font></h4>

</body>
</html>
