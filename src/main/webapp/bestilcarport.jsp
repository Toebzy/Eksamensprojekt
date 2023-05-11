<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<t:pagetemplate>
    <jsp:attribute name="header">
             Bestil Carport
        <h6> Nedenfor kan du lave din egen carport ud fra dine given mål </h6>
    </jsp:attribute>
    <jsp:attribute name="footer">
            Bestil Carport
    </jsp:attribute>
    <jsp:body>
        <form action="bestilcarport" method="post">
            <label for="length">Længde:</label>
            <input type="number" id="length" name="length" placeholder="Længde i cm" min="1" max="999" required/>
            <br>
            <label for="width">Bredde:</label>
            <input type="number" id="width" name="bredde" placeholder="Bredde i cm" min="1" max="999" required/>
            <br>
            <label for="heigth">Højde:</label>
            <input type="number" id="heigth" name="højde" placeholder="Højde i cm" min="1" max="999" required/>
            <br>
            <input type="checkbox" id="carportAttribute1" name="vehicle1" value="carportAttribute1">
            <label for="carportAttribute1">carportAttribute2</label>
            <br>
            <input type="checkbox" id="carportAttribute2" name="vehicle1" value="carportAttribute2">
            <label for="carportAttribute2">carportAttribute2</label>
            <br>
            <input type="submit" value="Bestil Carport"/>
        </form>
    </jsp:body>
</t:pagetemplate>