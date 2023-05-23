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
                    <table class="admintable">
                        <thead class="thead-dark">
                        <tr>
                            <th>Ordre ID</th>
                            <th>Bruger ID</th>
                            <th>Carport Bredde</th>
                            <th>Carport Længde</th>
                            <th>Carport Højde</th>
                            <th>Pris</th>
                            <th>Betalt</th>
                            <th>Status</th>
                            <th>Status</th>
                            <th>Stykliste</th>
                        </tr>
                        </thead>
                        <c:forEach var="emne" items="${requestScope.orderList}" varStatus="varStatus">
                            <tr>
                                <td>${emne.idorder}</td>
                                <td>${emne.iduser}</td>
                                <td>${emne.carportwidth}cm</td>
                                <td>${emne.carportlength}cm</td>
                                <td>${emne.carportheight}cm</td>
                                <td>${emne.price}kr</td>
                                <td class="ispaid${emne.paid}"></td>
                                <td class="order${emne.status}">${emne.status}</td>
                                <form action="orderstatusservlet" method="post">
                                    <td><select id ="status" name ="status">
                                        <option disabled selected>Status</option>
                                        <option value="completed">Gennemført</option>
                                        <option value="processing">Behandles</option>
                                        <option value="cancelled">Annulleret</option>
                                        </select>
                                        <button type="submit" class="btn">Opdater</button>
                                        <input type="hidden" id="idorder" name="idorder" value=${emne.idorder}>
                                    </td>
                                </form>
                                <form action="partslist" method="post">
                                    <input hidden value ="${emne.idorder}" name ="idorder">
                                    <th class="balance"><button class ="btn">Stykliste</button></th>
                                </form>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </jsp:body>
</t:pagetemplate>