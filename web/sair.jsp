<%-- 
    Document   : sair
    Created on : 09/11/2011, 20:28:26
    Author     : j211-11
--%>
<%
    request.getSession().invalidate();
    response.sendRedirect("login.jsp");
%>   


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Controle Financeiro - Sair</title>
    </head>
    <body>

    </body>
</html>
