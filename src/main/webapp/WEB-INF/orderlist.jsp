<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<t:pagetemplate>
    <jsp:attribute name="header">
         Administrator Område
    </jsp:attribute>
    <jsp:attribute name="footer">
        Administrator
    </jsp:attribute>
    <jsp:body>
        <p class="center">Order List</p>
        <form class="adminbtn" action="adminservlet" method="post">
            <button class="btn">Se brugerliste</button>
        </form>
        <div id ="tablesection" class="container">
            <div class="row">
                <div class="col">
                    <table class="table">
                        <thead class="thead-dark">
                        <tr>
                            <th style="width:30px">OrderID</th>
                            <th style="width:20px">User ID</th>
                            <th style="width:20px">Status</th>
                            <th style="width:20px">Carport Width</th>
                            <th style="width:20px">Carport Height</th>
                            <th style="width:20px">Carport Length</th>
                        </tr>
                        </thead>
                        <c:forEach var="emne" items="${requestScope.orderList}" varStatus="varStatus">
                            <tr>
                                <td>${emne.idorder}</td>
                                <td>${emne.iduser}</td>
                                <td>${emne.status}</td>
                                <td>${emne.carportwidth}</td>
                                <td>${emne.carportheight}</td>
                                <td>${emne.carportlength}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </jsp:body>
</t:pagetemplate>
