<%@ include file="jspf/taglib.jspf" %>
<html>
<head>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">
    <title>ADMIN</title>
</head>
<body>

<%@ include file="jspf/navigation_block.jspf" %>
<div align="right">
    <form action="/" method="get">
        <button type="submit" class="button">My shop</button>
    </form>
</div>

<div align="left">
    <sf:form enctype="multipart/form-data" modelAttribute="productUploader" action="/addProduct" method="post">
        <h3>New product:</h3>

        <br/>
        <label for="titleArea">Product title:</label><br/>
        <textarea id = "titleArea" cols="100" rows="2" name="title"></textarea>
        <br/>

        <br/>
        <label for="descriptionArea">Description:</label><br/>
        <textarea id = "descriptionArea" cols="100" rows="2" name="description"></textarea>

        <br/>

        <br/>
        <label for="priceInput">Price:</label><br/>
        <input id="priceInput" type="number" size="4" name="price" min="1" max="100000" step="1" value="0">

        <br/>

        <br/>
        <label for="photoInput">Photo:</label>
        <input id="photoInput" type="file" name="photo"/>

        <br/><br/>
        <button type="submit" class="button">Upload</button>

    </sf:form>

</div>
<br/>
<br/>
<br/>
<table width="90%" align="center">
    <thead>
    <tr>
        <td width="10%"></td>
        <td width="10%"></td>
        <td width="15%"></td>
        <td width="55%"></td>
        <td width="10%"></td>
    </tr>
    </thead>
    <c:forEach items="${reports}" var="report">
        <tr>
            <td>${report.login}</td>
            <td>${report.email}</td>
            <td>${report.roles}</td>
            <td>${report.orders}</td>
            <td>
                <sf:form action="/addInBlackList" method="post">
                    <input type="hidden" name="userId" value="${report.userId}"/>
                    <button type="submit" class="button">Ban</button>
                </sf:form>

                <sf:form action="/removeFromBlackList" method="post">
                    <input type="hidden" name="userId" value="${report.userId}"/>
                    <button type="submit" class="button">Restore</button>
                </sf:form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>