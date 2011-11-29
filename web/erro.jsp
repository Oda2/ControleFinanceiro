<%-- 
    Document   : erro
    Created on : 01/11/2011, 20:28:57
    Author     : j211-11
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Controle Financeiro - Alterar Dados</title>
        <meta name="keywords" content="ocean blue, gallery page, template, free css templates, CSS, HTML" />
        <meta name="description" content="Ocean Blue Gallery - Free CSS Template provided by templatemo.com" />
        <link href="templatemo_style.css" rel="stylesheet" type="text/css" />
        <script language="javascript" type="text/javascript">
            function clearText(field)
            {
                if (field.defaultValue == field.value) field.value = '';
                else if (field.value == '') field.value = field.defaultValue;
            }
        </script>

        <link rel="stylesheet" type="text/css" href="css/jquery.lightbox-0.5.css" />    

        <!-- Arquivos utilizados pelo jQuery lightBox plugin -->
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery.lightbox-0.5.js"></script>
        <link rel="stylesheet" type="text/css" href="css/jquery.lightbox-0.5.css" media="screen" />
        <!-- / fim dos arquivos utilizados pelo jQuery lightBox plugin -->

        <!-- Ativando o jQuery lightBox plugin -->
        <script type="text/javascript">
            $(function() {
                $('#gallery a').lightBox();
            });
        </script>

    </head>
    <body class="subpage">

        <div id="templatemo_wrapper">
            <div id="templatemo_header">

                <div id="site_title"><h1><a href="login.jsp">Controle Financeiro</a></h1></div>

                <div class="cleaner"></div>
            </div>

            <div id="templatemo_menu">
                <ul>
                    <li><!--<a href="index.html">Home</a>--></li>
                    <li><!--<a href="movimentacao.html">Movimentação</a>--></li>
                    <li><!--<a href="alterar_usuario.html" class="current">Alterar Dados</a>--></li>
                    <li><!--<a href="contato.html">Contato</a>--></li>
                </ul>    	
                <div class="cleaner"></div>
            </div> <!-- end of templatemo_menu -->

            <div id="templatemo_middle_subpage">
                <h2>Desculpe, não rodamos no Internet Explorer.</h2>
            </div>

            <div id="templatemo_main">

                <div class="col_w900 col_w900_last" align="center">
                    <img src="images/final.png" />
                    <div class="cleaner"></div>
                </div>

                <div class="cleaner"></div>
            </div> <!-- end of main -->

        </div> <!-- end of wrapper -->

        <div id="templatemo_footer_wrapper">
            <div id="templatemo_footer">

                Copyright © 2011 <a href="index.html">Controle Financeiro</a> - 
                Desenvolvido por: Bruno, Guilherme, Luan e Renato.

            </div> <!-- end of footer wrapper -->
        </div> <!-- end of footer -->

    </body>
</html>
