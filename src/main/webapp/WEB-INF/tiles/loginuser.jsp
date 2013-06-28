<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.cn.ld.modules.user.domain.LoginUserDetails" %>
<%@ page import="com.cn.ld.modules.user.domain.User" %>
<%
  User user = LoginUserDetails.getLoginUser() ;
%>
<h1>Hello, <%=user.getName()%> &nbsp;<%=user.getAge()%> &nbsp;<%=user.getSex()%></h1>