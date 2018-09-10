<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
        
        <h2 class="form-signin-heading">AddProduct</h2>

        <div class="container">

            <form:form method="POST" modelAttribute="productForm" bclass="form-signin">
                
                <spring:bind path="name">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="name" class="form-control" placeholder="name of product"
                                    autofocus="true"></form:input>
                        <form:errors path="name"></form:errors>
                        </div>
                    </spring:bind>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
            </form:form>

        </div> 
        
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script>
                    function goBack() {
                        window.history.back();
                    }
        </script>
    </body>
</html>