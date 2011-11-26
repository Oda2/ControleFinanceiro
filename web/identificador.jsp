<%-- 
    Document   : identificador
    Created on : 01/11/2011, 20:27:24
    Author     : j211-11
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Controle Financeiro - Verificação de Web Browser</title>
    <meta name="keywords" content="ocean blue, gallery page, template, free css templates, CSS, HTML" />
    <meta name="description" content="Ocean Blue Gallery - Free CSS Template provided by templatemo.com" />

</head>
<body>
    <script language="JavaScript">
        if (navigator.appName == 'Microsoft Internet Explorer'){   
            alert("Porque você usa essa bosta??");  
            window.location = "erro.jsp";  
        }  
        if (navigator.appName == "Netscape"){
            alert("Parabéns, você usa o melhor navegador Web!");  
            window.location="login.jsp";  
        } 
    </script>    
</body>
</html>
