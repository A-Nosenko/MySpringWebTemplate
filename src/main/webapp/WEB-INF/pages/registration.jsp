<%@ include file="jspf/taglib.jspf" %>
<html>
<head>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">
    <title>REGISTRATION</title>
</head>

<body>

<%@ include file="jspf/navigation_block.jspf" %>
<div align="right">
    <form action="/" method="get">
        <button type="submit" class="button"><font face="Courier">My shop</font></button>
    </form>
</div>

<div align="center">
    <sf:form modelAttribute="user" method="post">
        <table>
            <tr>
                <td><br/>*Login:<br/></td>
                <td><br/><sf:input path="login"/></td>
                <td><br/><span class="error"><sf:errors path="login"/></span></td>
            </tr>

            <tr>
                <td><br/>*Password:<br/></td>
                <td><br/><sf:password path="password"/></td>
                <td><br/><span class="error"><sf:errors path="password"/></span></td>
            </tr>

            <tr>
                <td><br/>*Repeated password:<br/></td>
                <td><br/><sf:password path="repeatedPassword"/></td>
                <td><br/><span class="error"><sf:errors
                        path="repeatedPassword"/></span></td>
            </tr>

            <tr>
                <td><br/>Email:<br/></td>
                <td><br/><sf:input path="email"/></td>
                <td><br/><span class="error"><sf:errors path="email"/></span></td>
            </tr>

            <tr>
                <td colspan="3"><br/>
                    <input id="remember_me" name="remember-me" type="checkbox"/>
                    <label for="remember_me">Remember me</label><br/>
                    <br/>
                    <button value="submit" class="button"> Confirm</button>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <br/> * Is required
                </td>
            </tr>

        </table>
    </sf:form>
</div>
</body>
</html>