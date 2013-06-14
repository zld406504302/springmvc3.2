<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="static/style/main.css" />
</head>
<body>
<form action="<%=path%>/user/login/doLogin" method="post">
<div><span class="span_width_60 text_algin_right">username:</span><span><input name="name"/></span></div>
<div><span class="span_width_60 text_algin_right">password:</span><span><input type="password" name="password"/></span></div>
<div><span class="span_width_60 text_algin_right">sex:</span><span><input type="radio" name="sex" value="男" checked="checked"/><input type="radio" name="sex" value="女"/></span></div>
<div><span class="span_width_60 text_algin_right">age:</span><span><input type="text" name="age" /></span></div>
<div><span class="span_width_60 text_algin_right"><input type="submit" value='submit'/></span></div>
</form>
</body>
</html>
