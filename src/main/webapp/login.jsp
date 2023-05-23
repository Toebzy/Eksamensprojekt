<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>
<t:pagetemplate>
    <jsp:attribute name="header">
             Login
    </jsp:attribute>

    <jsp:attribute name="footer">
            Login
    </jsp:attribute>
    <jsp:body>
        <br>
        <h3 class="infotext">Du kan logge ind her</h3>
        <form class="infotext" action="login" method="post">
            <label for="username">Email: </label>
            <input type="text" id="username" name="username"/>
            <label for="password">Kodeord:</label>
            <input type="password" id="password" name="password"/>
            <input type="submit" class="btn" value="Log in"/>
        </form>
        <p class="error"> ${requestScope.errormessage}</p>
        <p class="infotext">Har du ikke en bruger? <a
                href="createuser.jsp">Tilmeld dig her</a></p>
    </jsp:body>
</t:pagetemplate>