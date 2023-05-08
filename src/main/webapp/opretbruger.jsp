<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<t:pagetemplate>
    <jsp:attribute name="header">
             Opret Bruger
        <h6> Udfyld venligst din information her for at lave en bruger </h6>
    </jsp:attribute>
    <jsp:attribute name="footer">
            Opret Bruger
    </jsp:attribute>
    <jsp:body>
        <form action="opretbruger" method="post">
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" placeholder="123@123.dk" required/>
            <label for="name">Navn:</label>
            <input type="text" id="name" name="name" required/>
            <br>
            <label for="password">Kodeord:</label>
            <input type="password" id="password" name="password" placeholder="Kodeord" required/>
            <label for="gentagkodeord">Gentag Kodeord:</label>
            <input type="password" id="gentagkodeord" name="gentagkodeord" placeholder="Kodeord" required/>
            <br>
            <label for="address">Adresse:</label>
            <input type="text" id="address" name="address" required/>
            <label for="zipcode">Postnummer:</label>
            <input type="number" id="zipcode" name="zipcode" placeholder="2700 el. 4200"  required/>
            <br>
            <label for="phonenumber">Telefonnummer:</label>
            <input type="number" id="phonenumber" name="phonenumber" required/>
            <br>
            <input type="submit" value="Opret Bruger"/>
            ${requestScope.msg}
        </form>
    </jsp:body>
</t:pagetemplate>