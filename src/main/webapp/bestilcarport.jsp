<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<t:pagetemplate>
    <jsp:attribute name="header">
             Bestil Carport
        <h6 class="center">Nedenfor kan du bestille din egen carport ud fra dine given mål</h6>
    </jsp:attribute>
    <jsp:attribute name="footer">
            Bestil Carport
    </jsp:attribute>
    <jsp:body>
        <form class="infotext" action="bestilcarportservlet" method="post">
            <label for="length">Længde:</label>
            <input class="lb" type="number" id="length" value="${requestScope.length}" name="length" placeholder="Længde" min="100" max="2160" required/>
            <label for="width">Bredde:</label>
            <input class="lb" type="number" id="width" value="${requestScope.width}" name="width" placeholder="Bredde" min="100" max="600" required/>
            <label for="width">Højde:</label>
            <input class="lb" type="number" id="height" value="${requestScope.height}" name="height" placeholder="Højde" min="100" max="210" required/>
            <input type="hidden" id="userid" name="userid" value="${sessionScope.user.userid}"/>
            <input type="submit" class="btn" value="Bestil Carport" name="button"/>
            <input type="submit" class="btn" value="Se pris" name="button"/>
        </form>
        <p class="col">${requestScope.msg}</p>
    </jsp:body>
</t:pagetemplate>