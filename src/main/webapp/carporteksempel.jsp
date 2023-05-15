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
        <div class="row">
            <div class="col image">
                <img src="images/carporteksempel1.webp">
                <div>
                    <p1>Carport med skur</p1>
                </div>
            </div>
            <br>
            <div class="col image">
                <img src="images/carporteksemepl2.png">
                <div>
                    <p1>Carport med skur</p1>
                </div>
            </div>
            <br>
        </div>
        <div class="row">
            <div class="col image">
                <img src="images/carporteksempel4.jpeg">
                <div>
                    <p1>Carport uden skur</p1>
                </div>
            </div>
            <br>
            <div class="col image">
                <img src="images/carporteksempel5.JPG">
                <div>
                    <p1>Skur indefra</p1>
                </div>
            </div>
            <br>
        </div>



    </jsp:body>

</t:pagetemplate>