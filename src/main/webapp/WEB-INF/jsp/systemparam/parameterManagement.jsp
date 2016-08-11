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

<div class="container">
  <a class="btn waves-light waves-effect white-text" style="float: right;" href="${pageContext.request.contextPath}/systemparam/new/${projectId}/">添加</a>
    <table class="table table-bordered table-hover">
     <thead style="color: #999b91; font-size: 20px;">
      <tr>
       <th>参数ID</th>
       <th>参数名称</th>
       <th>参数值</th>
       <th>参数路径</th>
       <th>正则匹配</th>
       <th>操作</th>
      </tr>
     </thead>
     <tbody>
     <c:forEach var="item" items="${parameterList}">
        <tr>
          <td>${item.parameter_id}</td>
          <td>${item.parameter_name}</td>
          <td>${item.parameter_value}</td>
          <td>${item.parameter_path}</td>
          <td>${item.regular}</td>
          <td>
            <a class="btn waves-light waves-effect white-text" href="${pageContext.request.contextPath}/systemparam/changeParam/${item.uuid}/${item.parameter_id}/">修改</a>
            <a class="btn waves-light waves-effect white-text" href="${pageContext.request.contextPath}/systemparam/delete/${item.uuid}/${item.parameter_id}/">删除</a>
            <a class="btn waves-light waves-effect white-text" href="${pageContext.request.contextPath}/systemparam/readFile/${item.parameter_id}/">同步</a>
          </td>
        </tr>
     </c:forEach>
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
<div id="alert-modal" class="modal">
    <div class="modal-content">
        <p class="grey-text">提示</p>
        <p class="text-alert"></p>
    </div>
    <div class="modal-footer">
        <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">关闭</a>
    </div>
</div>
</body>
</html>