<%@ include file="jspf/taglib.jspf" %>
<html>
<head>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">
    <title>ACCOUNT</title>
</head>
<body>

<%@ include file="jspf/navigation_block.jspf" %>
<div align="right">
    <form action="/" method="get">
        <button type="submit" class="button">My shop</button>
    </form>
</div>

<div align="right">
    <span class="error">${error}</span><br/>

    <h3> Balance: ${balance} $ </h3>
</div>
<br/>
<br/>

<h3>Orders: </h3>
<table width="90%" align="center">
    <thead>
    <tr>
        <td width="20%">Title</td>
        <td width="20%">Date</td>
        <td width="20%">Price</td>
        <td width="20%">Status</td>
        <td width="20%"></td>
    </tr>
    </thead>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>
                    ${order.title}
            </td>

            <td>
                <fmt:formatDate value="${order.orderDate}" pattern="dd-MM-yyyy HH:mm:ss"/>
            </td>

            <td>
                    ${order.price}
            </td>

            <td>
                <c:if test="${order.payDate == null}">
                    <sf:form action="/payOff" method="post">
                        <input type="hidden" name="userName" value="${pageContext.request.remoteUser}"/>
                        <input type="hidden" name="id" value="${order.id}"/>
                        <button type="submit" class="button">Pay</button>
                    </sf:form>
                </c:if>
                <c:if test="${order.payDate != null}">
                    <fmt:formatDate value="${order.payDate}" pattern="dd-MM-yyyy HH:mm:ss"/> </div>
                </c:if>
            </td>

            <td>
                <c:if test="${order.payDate == null}">
                    <sf:form action="/disclaim" method="post">
                        <input type="hidden" name="userName" value="${pageContext.request.remoteUser}"/>
                        <input type="hidden" name="id" value="${order.id}"/>
                        <button type="submit" class="button">Delete</button>
                    </sf:form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>