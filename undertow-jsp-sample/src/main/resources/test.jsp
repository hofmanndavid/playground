<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<BODY>
Hello, world.
<%= System.getenv("PATH") %>

<% String something = "hi again";  %>
<br>
<a href="<c:url value="http://undertow.io"/>">Powered by undertow</a>
<p>Aqui va un EL: ${something}, and it worked</p>
</BODY>
</HTML>
