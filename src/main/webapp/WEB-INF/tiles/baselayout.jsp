<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:insertAttribute name="title" /></title>
        <tilesx:useAttribute name="stylesheets" classname="java.util.List" />
        <c:forEach items="${stylesheets}" var="src">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/<c:url value='${src}.css' />" />
        </c:forEach>
        <tilesx:useAttribute id="scripts" name="scripts" classname="java.util.List" />
        <c:forEach items="${scripts}" var="src">
        <script type="text/javascript" src="${pageContext.request.contextPath}/<c:url value='${src}.js'/>"></script>
        </c:forEach>
    </head>
    <body>
        <div id="head">
	        <div id="banner"></div>
	        <div id="login_user"><tiles:insertAttribute name="login_user" /></div>
	        <div class="clear_float"></div>	
	        <div id="topmenu"><jsp:include page="/WEB-INF/tiles/topmenu.jsp"/></div>
        </div> 
 		<div class="clear_float"></div>
        <div id="main">
	        <div id="leftmenu"><tiles:insertAttribute name="left_menu" /></div>
	        <div id="content"><tiles:insertAttribute name="content" /></div>
	        <div class="clear_float"></div>
        </div>
        <div id="footer"><jsp:include page="/WEB-INF/tiles/footer.jsp"/></div>
    </body>
</html>