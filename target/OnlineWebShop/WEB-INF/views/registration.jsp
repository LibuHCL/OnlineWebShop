<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Create Category</title>
  <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
  <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<div class="generic-container">
  <%@include file="authheader.jsp" %>

  <div class="well lead">Category Registration Form</div>
  <form:form method="post" action="/idealo/onlinewebshop/add/category" modelAttribute="category" class="form-horizontal">

    <div class="row">
      <div class="form-group col-md-12">
        <label class="col-md-3 control-lable" for="title">Title</label>
        <div class="col-md-7">
          <form:input type="text" path="title" id="title" class="form-control input-sm"/>
          <div class="has-error">
            <form:errors path="title" class="help-inline"/>
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="form-actions floatRight">
            <input type="submit" value="Create" class="btn btn-primary btn-sm"/>
      </div>
    </div>
  </form:form>
</div>
</body>
</html>