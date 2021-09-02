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
    <title>Success</title>
  </head>
  <body>
  <h1>SUCCESS</h1>
  </body>

  <form action="./rest" method="POST">
    <input type="hidden" name="_method" value="PUT" />
    <input type="test" name="name" placeholder="name"/><p/>
    <input type="submit" value="test_REST_PUT" />
  </form>

</html>
