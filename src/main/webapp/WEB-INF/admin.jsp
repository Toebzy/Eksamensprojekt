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

        <p>User Info</p>
        <div id ="tablesection" class="container">
            <div class="row">
                <div class="col">
                    <table class="table" style="width:100%">
                        <thead class="thead-dark">
                        <tr>
                            <th style="width:70%">ID</th>
                        </tr>
                        </thead>

                        <c:forEach var="emne" items="${requestScope.userid}" varStatus="varStatus">
                            <tr style="height:50px ">
                                <td>${emne}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="col">
                    <table class="table">
                        <thead class="thead-dark">
                        <tr>
                            <th style="width:70%">Email</th>
                        </tr>
                        </thead>

                        <c:forEach var="emne" items="${requestScope.email}" varStatus="varStatus">
                            <tr style="height:50px">
                                <td>${emne}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="col">
                    <table class="table">
                        <thead class="thead-dark">
                        <tr>
                            <th style="width:70%">Password</th>
                        </tr>
                        </thead>

                        <c:forEach var="emne" items="${requestScope.password}" varStatus="varStatus">
                            <tr style="height:50px">
                                <td>${emne}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="col">
                    <table class="table">
                        <thead class="thead-dark">
                        <tr>
                            <th style="width:70%">Name</th>
                        </tr>
                        </thead>

                        <c:forEach var="emne" items="${requestScope.name}" varStatus="varStatus">
                            <tr style="height:50px">
                                <td>${emne}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="col">
                    <table class="table">
                        <thead class="thead-dark">
                        <tr>
                            <th style="width:70%">Balance</th>
                        </tr>
                        </thead>

                        <c:forEach var="emne" items="${requestScope.balance}" varStatus="varStatus">
                            <form action="changeBalance" method="post">
                                <tr style="height:50px">
                                    <th> <input type="number" id="balance" name="balance" min="0" value =${emne}></th>
                                    <input hidden value ="${requestScope.userid}" name ="userid">
                                    <input hidden value = "${varStatus.index}" name = "varStatus">
                                    <th> <button class ="btn"> Opdater </button></th>
                                </tr>
                            </form>
                        </c:forEach>
                    </table>
                </div>
                <div class="col">
                    <table class="table" style="width:100%">
                        <thead class="thead-dark">
                        <tr>
                            <th style="width:70%">Zipcode</th>
                        </tr>
                        </thead>

                        <c:forEach var="emne" items="${requestScope.zipcode}" varStatus="varStatus">
                            <tr style="height:50px ">
                                <td>${emne}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="col">
                    <table class="table" style="width:100%">
                        <thead class="thead-dark">
                        <tr>
                            <th style="width:70%">Address</th>
                        </tr>
                        </thead>

                        <c:forEach var="emne" items="${requestScope.address}" varStatus="varStatus">
                            <tr style="height:50px ">
                                <td>${emne}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="col">
                    <table class="table" style="width:100%">
                        <thead class="thead-dark">
                        <tr>
                            <th style="width:70%">Phonenumber</th>
                        </tr>
                        </thead>

                        <c:forEach var="emne" items="${requestScope.phonenumber}" varStatus="varStatus">
                            <tr style="height:50px ">
                                <td>${emne}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        <form action="seordreliste" method="post">
            <button class="btn">Se ordreliste</button>
        </form>
    </jsp:body>

</t:pagetemplate>