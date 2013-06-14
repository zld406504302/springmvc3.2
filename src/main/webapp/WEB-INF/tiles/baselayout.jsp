<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
        + request.getServerName() + ":" + request.getServerPort()
        + path + "/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:insertAttribute name="title" /></title>
        <tilesx:useAttribute name="stylesheets" classname="java.util.List" />
        <c:forEach items="${stylesheets}" var="src">
           <link rel="stylesheet" type="text/css" href="<c:url value='${src}.css' />" />
        </c:forEach>
        <tilesx:useAttribute id="scripts" name="scripts" classname="java.util.List" />
        <c:forEach items="${scripts}" var="src">
            <script type="text/javascript" src="<c:url value='${src}.js' />"></script>
        </c:forEach>
    </head>
    <body>
        <div id="head">
        <div id="banner"></div>
        <div id="menu"><jsp:include page="/WEB-INF/tiles/menu.jsp"/></div>	
        </div>
        <div id="main"><tiles:insertAttribute name="main" /></div>
        <div id="footer"><jsp:include page="/WEB-INF/tiles/footer.jsp"/></div>
    </body>
</html>