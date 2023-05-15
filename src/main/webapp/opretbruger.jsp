<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<t:pagetemplate>
    <jsp:attribute name="header">
             Opret Bruger
        <h6 style="text-align: center"> Udfyld venligst din information her, for at lave en bruger </h6>
    </jsp:attribute>
    <jsp:attribute name="footer">
            Opret Bruger
    </jsp:attribute>
    <jsp:body>
        <form style="margin:auto; width: 190px" action="opretbruger" method="post">
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" placeholder="123@123.dk" required/>
            <label for="name">Navn:</label>
            <input type="text" id="name" name="name" placeholder="Navn" required/>
            <label for="password">Kodeord:</label>
            <input type="password" id="password" name="password" placeholder="Kodeord" required/>
            <label for="gentagkodeord">Gentag Kodeord:</label>
            <input type="password" id="gentagkodeord" name="gentagkodeord" placeholder="Kodeord" required/>
            <label for="address">Adresse:</label>
            <input type="text" id="address" name="address" placeholder="Addresse" required/>
            <label for="zipcode">Postnummer:</label>
            <input type="number" id="zipcode" name="zipcode" placeholder="2700 el. 4200" required/>
            <label for="phonenumber">Telefonnummer:</label>
            <input type="number" id="phonenumber" name="phonenumber" placeholder="Telefonnummer" required/>
            <input type="submit" class="btn" style="margin-top: 10px; margin-left: 33px" value="Opret Bruger"/>
           <p class="error"> ${requestScope.msg}</p>
        </form>
    </jsp:body>
</t:pagetemplate>