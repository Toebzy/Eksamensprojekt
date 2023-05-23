<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<t:pagetemplate>
    <jsp:attribute name="header">
         <c:if test="${sessionScope.user.role==1}">
            Administrator Område
                </c:if>
        <c:if test="${sessionScope.user.role==0}">
            Stykliste
                </c:if>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <c:if test="${sessionScope.user.role==1}">
            Administrator Område
                </c:if>
        <c:if test="${sessionScope.user.role==0}">
            Stykliste
                </c:if>
    </jsp:attribute>
    <jsp:body>
        <c:if test="${sessionScope.user.role==1}">
            <p class="center">Order List</p>
            <form class="adminbtn" action="orderlist" method="post">
                <button class="btn">Se ordreliste</button>
            </form>
        </c:if>
        <div id ="tablesection" class="container">
            <div class="row">
                <div class="col">
                    <table class="admintable">
                        <thead class="thead-dark">
                        <tr>
                            <th>Variant</th>
                            <th>Beskrivelse</th>
                            <th>Længde</th>
                            <th>Mængde</th>
                        </tr>
                        </thead>
                        <c:forEach var="emne" items="${requestScope.partslist}" varStatus="varStatus">
                            <tr>
                                <td>${emne.mvariant}</td>
                                <td>${emne.description}</td>
                                <td>${emne.length}cm</td>
                                <td>${emne.amount}stk</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </jsp:body>
</t:pagetemplate>
