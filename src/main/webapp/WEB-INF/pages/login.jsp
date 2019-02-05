<%@ include file="jspf/taglib.jspf" %>
<html>
<head>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">
    <title>LOGIN</title>
</head>
<body>

<c:if test="${pageContext.request.remoteUser == null}">
<div align="right">
    <form action="/registration" method="get">
        <button type="submit" class="button">Registration</button>
    </form>
    </c:if></div>
<c:if test="${pageContext.request.remoteUser != null}">
    <h3><c:out value="${pageContext.request.remoteUser}"/></h3>
    <sf:form action="/logout" method="post">
        <button type="submit" class="button">Sign out</button>
    </sf:form>
</c:if>

<div align="right">
    <security:authorize access="hasRole('ROLE_ADMIN')">
        <sf:form action="/admin" method="get">
            <button type="submit" class="button">Admin page</button>
        </sf:form>
    </security:authorize>
</div>

<c:if test="${pageContext.request.remoteUser == null}">
    <div align="right">
        <form action="/" method="get">
            <button type="submit" class="button">My shop</button>
        </form>
    </div>
</c:if>

<div align="center">

    <c:if test="${pageContext.request.remoteUser != null}">
        <h1>You are welcome, <c:out value="${pageContext.request.remoteUser}"/> !</h1><br/>

        <form action="/" method="get">
            <button type="submit" class="button">My shop</button>
        </form>
    </c:if>
</div>

<div align="center">
    <sf:form action="/login" method="post">
        <span class="error">${error}</span><br/>
        <c:if test="${pageContext.request.remoteUser == null}">
            <input name="username" type="text" placeholder="Login" autofocus="true"/><br/><br/>
            <input name="password" type="password" placeholder="Password"/><br/><br/>
            <input id="remember_me" name="remember-me" type="checkbox"/>
            <label for="remember_me"> Remember me </label><br/><br/>
            <button type="submit" class="button"> Sign in</button>
        </c:if>
    </sf:form>
</div>
</body>
</html>
