<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: luozc-kf1b
  Date: 2016/4/28
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${sysinfo}" var="entry">
    <c:out value="${entry.key}" />
    <br>

    <c:forEach items="${entry.value}" var="entry">
        <c:out value="${entry.key}" />:
        <c:out value="${entry.value}" />;
    </c:forEach>
    <br>


</c:forEach>

</body>
</html>
