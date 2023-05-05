<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<t:pagetemplate>
    <jsp:attribute name="header">
             Opret Bruger
    </jsp:attribute>
    <jsp:attribute name="footer">
            Opret Bruger
    </jsp:attribute>
    <jsp:body>
        <form action="opretbruger" method="post">
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" placeholder="123@123.dk"/>
            <label for="navn">Navn:</label>
            <input type="text" id="navn" name="navn"/>
            <br>
            <label for="password">Kodeord: </label>
            <input type="password" id="password" name="password" placeholder="Kodeord"/>
            <label for="gentagkodeord">Gentag Kodeord: </label>
            <input type="password" id="gentagkodeord" name="gentagkodeord" placeholder="Kodeord"/>
            <br>

            <input type="submit" value="Opret Bruger"/>
        </form>
    </jsp:body>
</t:pagetemplate>
