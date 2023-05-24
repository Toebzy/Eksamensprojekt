<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>
<t:pagetemplate>
    <jsp:attribute name="header">
             Log ind
    </jsp:attribute>

    <jsp:attribute name="footer">
            Login
    </jsp:attribute>
    <jsp:body>
        <br>
        <form class="infotext loginwidth" action="login" method="post">
            <div class="colleft login">
                <div><label class="login" for="username">Email:</label></div>
                <div><input class="login" type="text" id="username" name="username"/></div>
            </div>
            <div><br></div>
            <div class="colleft login">
                <div><label class="login" for="password">Kodeord:</label></div>
                <div><input class="login" type="password" id="password" name="password"/></div>
            </div>
            <div><br></div>
            <div><input class="login btn" type="submit" value="Log ind"/></div>
        </form>
        <p class="error"> ${requestScope.errormessage}</p>
        <p class="infotext center">Har du ikke en bruger? <a
                href="createuser.jsp">Tilmeld dig her</a></p>
    </jsp:body>
</t:pagetemplate>