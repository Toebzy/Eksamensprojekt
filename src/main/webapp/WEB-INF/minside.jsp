<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<t:pagetemplate>
    <jsp:attribute name="header">
         Profil
    </jsp:attribute>
    <jsp:attribute name="footer">
        Min Side
    </jsp:attribute>
    <jsp:body>
    <div>
        <div class="userinfo">
            <p> <b>Navn: </b></p>
            <p class="userinfotext">${sessionScope.user.name}</p>
            <p> <b>Email: </b></p>
            <p class="userinfotext">${sessionScope.user.email}</p>
            <p> <b>Telefonnummer: </b></p>
            <p class="userinfotext">${sessionScope.user.phonenumber}</p>
            <p> <b>Adresse: </b></p>
            <p class="userinfotext">${sessionScope.user.address}</p>
            <p> <b>Postnummer: </b></p>
            <p class="userinfotext">${sessionScope.user.zipcode}</p>
            <p> <b>Saldo: </b></p>
            <p class="userinfotext">${sessionScope.user.balance}kr</p>
        </div>
                <div id ="tablesection" class="center">
                    <h5 class="center">Mine Ordre</h5>
                    <table class="table">
                        <thead class="thead-dark">
                        <tr>
                            <th>OrderID</th>
                            <th>Ordrestatus</th>
                            <th>Carport Bredde</th>
                            <th>Carport Længde</th>
                            <th>Pris</th>
                            <th>Status</th>
                            <th>Stykliste</th>
                        </tr>
                        </thead>
                        <c:forEach var="emne" items="${requestScope.userOrderList}" varStatus="varStatus">
                            <c:if test="${sessionScope.user.userid == emne.iduser }">
                            <tr>
                                <td>${emne.idorder}</td>
                                <td>${emne.status}</td>
                                <td>${emne.carportwidth}cm</td>
                                <td>${emne.carportlength}cm</td>
                                <td>${emne.price}kr</td>
                                <c:if test="${emne.paid == true}">
                                    <td>Betalt</td>
                                </c:if>
                                <c:if test="${emne.paid == false}">
                                    <form action="paycarport" method="post">
                                        <input hidden value ="${emne.price}" name ="price">
                                        <input hidden value ="${emne.idorder}" name ="idorder">
                                        <input hidden value ="${sessionScope.user.balance}" name ="balance">
                                        <input hidden value ="${sessionScope.user.userid}" name ="userid">
                                        <th> <button class="btn">Betal</button></th>
                                    </form>
                                </c:if>
                                <c:if test="${emne.paid == true}">
                                    <form action="partslist" method="post">
                                        <input hidden value ="${emne.idorder}" name ="idorder">
                                        <th class="balance"><button class ="btn">Stykliste</button></th>
                                    </form>
                                </c:if>
                            </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                 </div>
    </div>
    </jsp:body>
</t:pagetemplate>