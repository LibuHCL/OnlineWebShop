<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Categories List</title>
  <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
  <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<div class="generic-container">
  <%@include file="authheader.jsp" %>
  <div class="panel panel-default">
    <div class="panel-heading"><span class="lead">List of Categories </span></div>
    <table class="table table-hover">
      <thead>
      <tr>
        <th>Category ID</th>
        <th>Category Title</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${listCategories}" var="category">
        <tr>
          <td>${category.categoryId}</td>
          <td>${category.title}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
  <div class="well">
    <a href="<c:url value='/idealo/onlinewebshop/new/category' />">Create Category</a>
    <br/><br/>
    <a href="<c:url value='/idealo/onlinewebshop/new/item' />">Create Item For Category</a>
  </div>
</div>
</body>
</html>