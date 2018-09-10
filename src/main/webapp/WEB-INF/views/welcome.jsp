<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <%@include file="headerUser.html"%>
    </c:if>         
     
    <table style="width:100%">
            <tr>
                <th style="width:20%">id</th>
                <th style="width:20%">ilosc</th>
                <th style="width:20%">data</th>
                <th style="width:20%">co</th>
                <th style="width:20%">status</th>

            </tr>
            <c:forEach var="order" items="${ownorders}" varStatus="status">
                <!--form:form  action = "" method="POST" modelAttribute="orderList"-->
                <tr>
                    <td><c:out value="${order.id}"/></td>  <!--productNames(${status.index})-->
                    <td><c:out value="${order.name}" /></td>
                    <td><c:out value="${order.date}" /></td>
                    <td><c:out value="${order.productAmount}" /></td>
                    <td><c:out value="${order.status}" /></td>
                </tr>
                
                <!--/form:form-->    
            </c:forEach>
        </table>
    
</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
