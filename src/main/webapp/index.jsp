<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         <h1 style="text-align: center">Velkommen til Fog <h1/>
    </jsp:attribute>

    <jsp:attribute name="footer">
        Velkommen til Fog
    </jsp:attribute>

    <jsp:body>

        <c:if test="${sessionScope.user != null}">
            <p>You are logged in with the role of "${sessionScope.user.role}".</p>
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <p style="text-align: center">Du er ikke logget ind endnu, du kan logge ind her eller oprette en ny bruger: <a
                    href="login.jsp">Login</a></p>
        </c:if>

    </jsp:body>

</t:pagetemplate>