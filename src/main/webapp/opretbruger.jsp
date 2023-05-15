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
        <form style="margin:auto; padding:5px; width: 420px; height: 155px;" action="opretbruger" method="post">
            <input type="text" class="inputbox" id="email" name="email" placeholder="Email" required/>
            <input type="text" class="inputbox" id="name" name="name" placeholder="Navn" required/>
            <input type="password" class="inputbox" id="password" name="password" placeholder="Kodeord" required/>
            <input type="password" class="inputbox" id="gentagkodeord" name="gentagkodeord" placeholder="Gentag Kodeord" required/>
            <input type="text" class="inputbox" id="address" name="address" placeholder="Addresse" required/>
            <input type="number" class="inputbox" id="zipcode" name="zipcode" placeholder="Postnummmer" required/>
            <input type="number" class="inputbox2"  id="phonenumber" name="phonenumber" placeholder="Telefonnummer" required/>
            <input type="submit" class="btn" style="margin-left:147px" value="Opret Bruger"/>
           <p class="error"> ${requestScope.msg}</p>
        </form>

    </jsp:body>
</t:pagetemplate>

