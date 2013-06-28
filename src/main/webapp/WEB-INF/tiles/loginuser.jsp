<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.cn.ld.modules.user.domain.LoginUserDetails" %>
<%@ page import="com.cn.ld.modules.user.domain.User" %>
<%
  User user = LoginUserDetails.getLoginUser() ;
%>
<ul>
<li>Hello,<%=user.getName()%> </li>
<li><%=user.getAge()%></li>
<li><%=user.getSex()%><li>
<li><a href="<c:url value='/logout'/>">sign out</a></li>
</ul>