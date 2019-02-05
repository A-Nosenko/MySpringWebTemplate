<%@ include file="jspf/taglib.jspf" %>
<html>
    <head>
        <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">
        <title>WEB TEMPLATE</title>
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

    <h3>Our Products:</h3>

    <table width="90%" align="center">
        <thead>
        <tr>
            <td width="15%">
            </td>
            <td width="20%">
                <h3><p align="center">Name</p></h3>
            </td>
            <td width="40%">
                <h3><p align="center">Description</p></h3>
            </td>
            <td width="15%">
                <h3><p align="center">Price</p></h3>
            </td>
        </tr>
        </thead>
        <c:forEach items="${products}" var="product">
        <tr>
            <td><p align="center">
                <a href="/image/${product.id}" title="image" target="_blank">
                    <img alt="image" height="100" src="/image/${product.id}"/></a>
            </p></td>
            <td><p align="center">
                    ${product.title}
            </p></td>
            <td>
                    ${product.description}
            </td>
            <td><p align="center">
                    ${product.price}
            </p></td>

            <td>
                <security:authorize access="hasRole('ROLE_USER')">
                    <sf:form action="/buy" method="post">
                        <input type="hidden" name="userName" value="${pageContext.request.remoteUser}"/>
                        <input type="hidden" name="productId" value="${product.id}"/>
                        <button type="submit" class="button">Buy</button>
                    </sf:form>
                </security:authorize>

                <security:authorize access="hasRole('ROLE_ADMIN')">
                    <sf:form action="/removeProduct" method="post">
                        <input type="hidden" name="productId" value="${product.id}"/>
                        <button type="submit" class="button">Remove</button>
                    </sf:form>
                </security:authorize>
            </td>

            </c:forEach>
    </table>
    </body>
</html>