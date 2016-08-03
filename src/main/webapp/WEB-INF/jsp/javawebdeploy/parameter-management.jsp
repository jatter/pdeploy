<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/2
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
    <title>系统参数管理</title>
  <link rel="stylesheet" href="http://cdn.bootcss.com/materialize/0.97.0/css/materialize.min.css">
  <link href="${pageContext.request.contextPath}/resources/css/icon.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/bootstrap-3.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
  <script src="http://cdn.bootcss.com/materialize/0.97.0/js/materialize.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/bootstrap-3.3.5/dist/js/bootstrap.min.js33"></script>
</head>
<body style="font-family: 'Roboto', 'Droid Sans Fallback', '微软雅黑';min-height: 100vh;display: flex;flex-direction: column;">
<nav>
  <div class="nav-wrapper">
    <a href="${pageContext.request.contextPath}/" class="brand-logo center">参数管理</a>
  </div>
</nav>

<div class="container" style="margin-top: 35px;">
  <table class="table table-bordered table-hover">
    <thead style="color: #999b91; font-size: 20px;">
    <tr>
      <th>项目名称</th>
      <th>参数名称</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td>项目a</td>
      <td>参数a</td>
      <td>删除/修改</td>
    </tr>
    <tr>
      <td>项目b</td>
      <td>参数b</td>
      <td>删除/修改</td>
    </tr>
    <tr>
      <td>项目c</td>
      <td>参数c</td>
      <td>删除/修改</td>
    </tr>
    </tbody>
  </table>
</div>


<footer class="page-footer" style="padding-top: 0; margin-top: 40px;">
  <div class="footer-copyright">
    <div class="container">
      Copyright © 2016 <a class="grey-text text-lighten-4" href="http://xxgblog.com" target="_blank">http://xxgblog.com</a>. All rights reserved.
      <a class="grey-text text-lighten-4 right" href="https://github.com/wucao/JDeploy" target="_blank">GitHub</a>
    </div>
  </div>
</footer>

</body>
</html>