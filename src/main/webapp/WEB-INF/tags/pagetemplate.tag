<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<!DOCTYPE html>
<html lang="da">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><jsp:invoke fragment="header"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-custom">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">
                <img src="${pageContext.request.contextPath}/images/fog.png" width="62px;" class="img-fluid"/>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <c:if test="${sessionScope.user == null}">
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/login.jsp">Bestil Carport</a>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/bestilcarport.jsp">Bestil Carport</a>
                </c:if>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/carporteksempel.jsp">Carport eksempel</a>
            </div>
            <div class="navbar">
                <c:if test="${sessionScope.user == null}">
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/login.jsp">Login</a>
                </c:if>
                <c:if test="${sessionScope.user == null}">
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/createuser.jsp">Opret bruger</a>
                </c:if>
                <c:if test="${sessionScope.user.role == 0}">
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/minside">Min Side</a>
                </c:if>
                <c:if test="${sessionScope.user.role == 1}">
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/adminservlet">Admin</a>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/logout">Log out</a>
                </c:if>
                </div>
        </div>
    </nav>
</header>
<div class="bluebar"></div>
<div id="body" class="container mt-4" style="min-height: 400px;">
    <h1><jsp:invoke fragment="header"/></h1>
    <jsp:doBody/>
</div>
<div class="container mt-3">
    <hr/>
    <div class="row mt-4">
        <div class="col">
            <img src="${pageContext.request.contextPath}/images/fog.png" width="60px;" class="img-fluid"/><br/>
            Johannes Fog A/S<br/>
            Firskovvej 20<br/>
            2800 Lyngby<br/>
            CVR-nr. 12345678
        </div>
        <div class="col">
            <jsp:invoke fragment="footer"/><br/>
            <p>&copy; 2023 Johannes Fog A/S</p>
            <br/> Mail: kontakt@fog.dk<br/>
            Telefon: 12 34 56 78</p>
        </div>
        <div class="col">
            Åbningstider:<br/>
            Mandag-Fredag: 10-17:30<br/>
            Lørdag: 10-14:00<br/>
            Søndag: Lukket
        </div>
    </div>
</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>