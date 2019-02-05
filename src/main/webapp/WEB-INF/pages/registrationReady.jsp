<%@ include file="jspf/taglib.jspf" %>
<html>
    <head>
        <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">
        <title>READY</title>
    </head>
    <body>

    <%@ include file="jspf/navigation_block.jspf" %>
    <div align="right">
        <c:if test="${pageContext.request.remoteUser != null}">
            <sf:form action="/account" method="get">
                <input type="hidden" name="userName" value="${pageContext.request.remoteUser}"/>
                <button type="submit" class="button">My account</button>
            </sf:form>
        </c:if>
    </div>

    <div align="center">
        <h1>Hello, <security:authentication property="principal.username"/>!</h1>

        <form action="/" method="get">
            <button type="submit" class="button">My shop</button>
        </form>
    </div>
    </body>
</html>
