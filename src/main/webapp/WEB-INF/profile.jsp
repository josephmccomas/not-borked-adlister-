<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
    <jsp:include page="partials/navbar.jsp" />

    <div class="container">
        <h1>Viewing your profile.</h1>
    </div>
    <%
        String userName = null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")) userName = cookie.getValue();
            }
        }
        if(userName == null) response.sendRedirect("/login");
    %>
    <div>
    <h3>Hi <%=userName %>, Login successful.</h3>
    <br>
    <form action="LogoutServlet" method="post">
        <input type="submit" value="Logout" >
    </form>
    </div>
</body>
</html>
