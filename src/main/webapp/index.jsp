<%--
  Created by IntelliJ IDEA.
  User: luozc-kf1b
  Date: 2016/4/1
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>测试</title>
</head>

<body>
<h2>Hello World!</h2>
<form action="user/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file"><br>
    <input type="submit" value="submit">
</form>
<form action="user/user/1" method="post">
    <input type="hidden" name="_method" value="PUT">
    <input type="submit" value="put">
</form>

<form action="user/user/1" method="post">
    <input type="submit" value="post">
</form>

<form action="user/user/1" method="get">
    <input type="submit" value="get">
</form>

<form action="user/user/1" method="post">
    <input type="hidden" name="_method" value="DELETE">
    <input type="submit" value="delete">
</form>
</body>
</html>
