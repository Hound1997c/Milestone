<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.*" %>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        
        <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
        <title>JSP Page</title>
        
    </head>
    <body>
        
        
        <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
            <%@include file="header.html"%>
        </c:if>
        
        
            
        

        <h2 class="form-signin-heading">Historia Zamowien</h2>
        <table style="width:100%">
            <tr>
                <th style="width:20%">Produkt</th>
                <th style="width:20%">Data Zamowienia</th>
                <th style="width:20%">Kto zamowil</th>
                <th style="width:20%">Ile</th>
                <th style="width:20%">Status</th>

            </tr>
            <c:forEach var="order" items="${orderList}" varStatus="status">
                <!--form:form  action = "" method="POST" modelAttribute="orderList"-->
                <tr>
                    <td><c:out value="${order.product.name}"/></td>  <!--productNames(${status.index})-->
                    <td><c:out value="${order.date}" /></td>
                    <td><c:out value="${order.user.name}" /></td>
                    <td><c:out value="${order.productAmount}" /> </td>
                    <td><c:out value="${order.status}" /> </td>
                </tr>

                <!--/form:form-->    
            </c:forEach>
        </table>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
        <script>
                    function goBack() {
                        window.history.back();
                    }
        </script>
    </body>
</html>
