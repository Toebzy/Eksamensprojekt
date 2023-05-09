<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Carport eksempel
    </jsp:attribute>

    <jsp:attribute name="footer">
        Carport eksempel
    </jsp:attribute>

    <jsp:body>
        <div class="picture">
            <img src="images/carporteksempel1.webp" width="450" class="img-fluid">
            <div>
                <p1>Carport med skur</p1>
            </div>
        </div>
        <br>
        <div class="picture">
            <img src="images/carporteksemepl2.png" width="450" class="img-fluid">
            <div>
                <p1>Carport med skur</p1>
            </div>
        </div>
        <br>
        <div class="picture">
            <img src="images/carporteksempel3.jpg" width="450" class="img-fluid">
            <div>
                <p1>Carport uden skur</p1>
            </div>
        </div>
        <br>
        <div class="picture">
            <img src="images/carporteksempel4.jpeg" width="450" class="img-fluid">
            <div>
                <p1>Carport uden skur</p1>
            </div>
        </div>



    </jsp:body>

</t:pagetemplate>