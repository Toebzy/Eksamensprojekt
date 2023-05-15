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
        <p style="text-align: center;"> User Info</p>
            <form style="margin: auto; text-align: center; width: 150px; padding-bottom:20px" action="orderlist" method="post">
                <button class="btn" style="">Se ordreliste</button>
            </form>
        <div id ="tablesection" class="container">
            <div class="row">
                <div class="col">
                    <table class="table" style="width:80%">
                        <thead class="thead-dark">
                        <tr>
                            <th style="width:30px">ID</th>
                            <th style="width:20px">Email</th>
                            <th style="width:20px">Name</th>
                            <th style="width:20px">Zipcode</th>
                            <th style="width:20px">Address</th>
                            <th style="width:20px">Phonenumber</th>
                            <th style="width:20px">Balance</th>
                        </tr>
                        </thead>
                        <c:forEach var="emne" items="${requestScope.userList}" varStatus="varStatus">
                            <tr style="height:50px ">
                                <td>${emne.userid}</td>
                                <td>${emne.email}</td>
                                <td>${emne.name}</td>
                                <td>${emne.zipcode}</td>
                                <td>${emne.address}</td>
                                <td>${emne.phonenumber}</td>
                                <form action="balanceChange" method="post">
                                    <td> <input style="width: 80px; text-align:center"  type="number" id="balance" name="balance" min="0" value ="${emne.balance}"></td>
                                    <input hidden value ="${emne.userid}" name ="userid">
                                    <th style="width: 30px"> <button class ="btn"> Opdater </button></th>
                                </form>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </jsp:body>
</t:pagetemplate>