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
    <div class="profile">
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
                            <th>Status</th>
                            <th>Carport Bredde</th>
                            <th>Carport Længde</th>
                        </tr>
                        </thead>
                        <c:forEach var="emne" items="${requestScope.userOrderList}" varStatus="varStatus">
                            <c:if test="${sessionScope.user.userid == emne.iduser }">
                            <tr>
                                <td>${emne.idorder}</td>
                                <td>${emne.status}</td>
                                <td>${emne.carportwidth}cm</td>
                                <td>${emne.carportlength}cm</td>
                                <form action="stykliste" method="post">
                                    <input hidden value ="${emne.iduser}" name ="userid">
                                    <th class="balance"><button class ="btn">Stykliste</button></th>
                                </form>
                            </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                 </div>
    </div>
    </jsp:body>
</t:pagetemplate>