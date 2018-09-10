<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.*" %>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



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
        <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style>
            table, th, td {
                border: 1px solid black;
            }
        </style>
    </head>
    <body>
    
     <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
            <%@include file="header.html"%>
     </c:if>
        <!--%@include file="headerjsp.jsp"%-->
        

        <table style="width:90%">
            <tr>
                <th style="width:30%">Nick</th>
                <th style="width:30%">Imie</th>
                <th style="width:30%">Nazwisko</th>

            </tr>
            <c:forEach var="user" items="${People}" varStatus="status">
                <!--form:form  action = "" method="POST" modelAttribute="orderList"-->
                <tr>
                    <td><c:out value="${user.name}"/></td>  <!--productNames(${status.index})-->
                    <td><c:out value="${user.surname}" /></td>
                    <td><c:out value="${user.username}" /></td>
                </tr>
                
                <!--/form:form-->    
            </c:forEach>
        </table>

        <!-- /container -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="${contextPath}/resources/js/bootstrap.min.js"></script>

        <script>
                    function goBack() {
                        window.history.back();
                    }
        </script>
    </body>
</html>
