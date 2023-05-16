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

        <div class="userinfo">
            <div class="col">
            <p class="center"><b>Kontaktinformation:</b></p>
            <p class ="center"><b>Navn: </b>${sessionScope.user.name}</p>
            <p class ="center"><b>Email: </b>${sessionScope.user.email}</p>
            <p class ="center"><b>Telefonnummer: </b>${sessionScope.user.phonenumber}</p>
            <p class ="center"><b>Adresse: </b>${sessionScope.user.address}</p>
            <p class ="center"><b>Postnummer: </b>${sessionScope.user.zipcode}</p>
            <p class ="center"><b>Saldo: </b>${sessionScope.user.balance}</p>
            </div>
        </div>
        <br>

        <h5 class="center">Dine Ordre</h5>
        <div id ="tablesection" class="container">
            <div class="row">
                <div class="col">
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
        </div>

    </jsp:body>
</t:pagetemplate>