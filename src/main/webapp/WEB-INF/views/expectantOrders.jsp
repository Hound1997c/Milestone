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
        


        <h2 class="form-signin-heading">Serve Product</h2>
        <table style="width:90%">
            <tr>
                <th style="width:15%">Produkt</th>
                <th style="width:15%">Data Zamowienia</th>
                <th style="width:15%">Kto zamowil</th>
                <th style="width:15%">Ile</th>
                <th style="width:15%">Zatwierdz</th>
                <th style="width:15%">Odmow</th>

            </tr>
            <c:forEach var="order" items="${orderList}" varStatus="status">
                <!--form:form  action = "" method="POST" modelAttribute="orderList"-->
                <tr>
                    <td><c:out value="${order.product.name}"/></td>  <!--productNames(${status.index})-->
                    <td><c:out value="${order.date}" /></td>
                    <td><c:out value="${order.user.name}" /></td>
                    <td><c:out value="${order.productAmount}" /> </td>
                    <td>
                        <a href="${contextPath}/expectantOrders/accept/${order.id}">Zatwierdz</a>
                        <!--button onclick="${contextPath}/expectantOrders/accept/${order.id}" class="btn btn-lg btn-primary btn-block" >Zatwierdz</button-->
                    </td>
                    <td>
                        <a href="${contextPath}/expectantOrders/decline/${order.id}">Odmow</a>
                    </td>
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