<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.*" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
    <head>
        <title>Spring Page Redirection</title>
        <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
        
    </head>

    <body>
        
        <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <%@include file="headerUser.html"%>
        </c:if>
        
        <h2 class="form-signin-heading">Order Product</h2>
        <table>
            <tr>

                <th>Product Id</th>
                <th>Product Name</th>
                <th>Maker Id</th>
                <th>Amount</th>
                <th>Submit</th>

            </tr>
            <c:forEach var="textTutorial" items="${textProductList}" varStatus="status">
                <form:form  action = "${contextPath}/orderProduct/${textTutorial.id}/${textTutorial.name}" method="POST" modelAttribute="orderForm">
                    <tr>
                        
                        <td><c:out value="${textTutorial.id}" /></td>
                        <td><c:out value="${textTutorial.name}" /> </td>
                        <td><c:out value="${textTutorial.user.id}" /></td>
                        <td>
                            <spring:bind path="productAmount">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="number" path="productAmount" class="form-control" ></form:input>
                                    <form:errors path="productAmount"></form:errors>
                                </div>
                            </spring:bind>
                            
                            <!--input type="number"></input-->
                        </td>
                        <td>
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
                        </td>

                    </tr>
                </form:form>    
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

