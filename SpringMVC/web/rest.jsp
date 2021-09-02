<%--
  Created by IntelliJ IDEA.
  User: Yuri
  Date: 2020/11/4
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>REST</title>
</head>
<body>
<h1>REST</h1>
</body>

<hr/>

<form action="uploadFile" method="post" enctype="multipart/form-data">
    文件: <input type="file" name="file"/><p/>
    描述: <input type="text" name="desc"/><p/>
    <input type="submit" value="提交"/>
</form>

</html>
