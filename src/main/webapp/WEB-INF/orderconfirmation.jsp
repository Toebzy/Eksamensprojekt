<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<t:pagetemplate>
    <jsp:attribute name="header">
            Din ordre er bestilt og registreret!
    </jsp:attribute>
    <jsp:attribute name="footer">
            Ordrebekræftelse
    </jsp:attribute>
    <jsp:body>
        <h1>Tak for din ordre!</h1>
        <h4>Du ville modtage en mail med ordrebekræftelsen, samt modtage en stykliste så snart din ordre er betalt og godkendt!</h4>
        <a href="${pageContext.request.contextPath}/mypage">Klik her</a> for at komme til "Min Side" og betale for din ordre.
        <h4>Kontaktinformationer, adresse samt åbningstider kan du finde i bunden af siden.</h4>
    </jsp:body>
</t:pagetemplate>