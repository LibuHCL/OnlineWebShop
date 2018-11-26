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
    <div class="panel-heading"><span class="lead">List of Items for the category </span></div>
    <table class="table table-hover">
      <thead>
      <tr>
        <th>Item ID</th>
        <th>Item Title</th>
        <th>Item Text</th>
        <th>Item Price</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${listItems}" var="item">
        <tr>
          <td>${item.itemId}</td>
          <td>${item.title}</td>
          <td>${item.text}</td>
          <td>${item.price}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
  <div class="well">
    <a href="<c:url value='/idealo/onlinewebshop/add/item' />">Create Item for Category</a>
    <br/><br/>
  </div>
</div>
</body>
</html>