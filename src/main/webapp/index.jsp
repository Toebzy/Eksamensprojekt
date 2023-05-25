<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>
<t:pagetemplate>
    <jsp:attribute name="header">
        Velkommen til Fog
    </jsp:attribute>
    <jsp:attribute name="footer">
        Velkommen til Fog
    </jsp:attribute>
    <jsp:body>
        <div class="center">
            <div class="col image">
                <img src="images/carporteksempel1.webp">
            </div>
        </div>
        <c:if test="${sessionScope.user != null}">
            <p class ="center">Hej ${sessionScope.user.name}, velkommen til Fog.</p>
        </c:if>
        <c:if test="${sessionScope.user == null}">
            <p class ="center">Du er ikke logget ind endnu, du kan logge ind eller oprette en ny bruger her: <a
                    href="login.jsp">Login</a></p>
        </c:if>
    </jsp:body>
</t:pagetemplate>