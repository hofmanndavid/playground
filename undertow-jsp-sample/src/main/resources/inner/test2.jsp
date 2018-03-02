<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<BODY>
Hello, world. inner here
<%= System.getenv("PATH") %>
<br>
<a href="<c:url value="http://undertow.io"/>">Powered by undertow</a>
</BODY>
</HTML>
