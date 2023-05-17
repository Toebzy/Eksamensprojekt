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
        <form class="infotext" action="stykliste" method="post">
            <label for="length">Længde:</label>
            <input type="number" id="length" name="length" placeholder="Længde" min="1" max="2160" required/>
            <br>
            <label for="width">Bredde:</label>
            <input type="number" id="width" name="width" placeholder="Bredde" min="1" max="600" required/>
            <br>
            <input type="submit" value="Bestil Carport"/>
        </form>
    </jsp:body>
</t:pagetemplate>