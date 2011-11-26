<%-- 
    Document   : contato
    Created on : 01/11/2011, 20:47:05
    Author     : j211-11
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Controle Financeiro - Contato</title>
        <meta name="keywords" content="ocean blue template, contact page, free css templates, CSS, HTML" />
        <meta name="description" content="Ocean Blue Contacts - Free CSS Template provided by templatemo.com" />
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
                $('#map a').lightBox();
            });
        </script>

    </head>
    <body class="subpage">

        <div id="templatemo_wrapper">
            <div id="templatemo_header">

                <div id="site_title"><h1><a href="http://www.templatemo.com">Ocean Blue</a> <span>Controlando seu Dinheiro</span></h1></div>

                <div id="search_box">
                    <form action="#" method="get">
                        <input type="text" value="31/12/2011" name="q" size="10" id="searchfield" title="searchfield" onfocus="clearText(this)" onblur="clearText(this)" />
                        <input type="submit" name="Search" value="" id="searchbutton" title="Search" />
                    </form>
                </div>

                <div class="cleaner"></div>
            </div>

            <div id="templatemo_menu">
                <ul>
                    <li><a href="logado.jsp">Início</a></li>
                    <li><a href="movimentacao.jsp">Movimentação</a></li>
                    <li><a href="alterar.jsp">Alterar Dados</a></li>
                    <li><a href="contato.jsp" class="current">Contato</a></li>
                    <li><a href="sair.jsp">Sair</a></li>
                </ul>    	
                <div class="cleaner"></div>
            </div> <!-- end of templatemo_menu -->

            <div id="templatemo_middle_subpage">
                <h2>Contate-nos</h2>
                <p>Em caso de dúvida, entre em contato conosco.</p>
            </div>

            <div id="templatemo_main">

                <div class="col_w900 col_w900_last">

                    <div class="col_w420 float_l">

                        <h3>Contato</h3>

                        <div id="cp_contact_form">

                            <form method="post" name="contato" action="#">

                                <label for="author">Nome:</label> <input name="nome" type="text" class="input_field" id="nome" maxlength="60" />
                                <div class="cleaner_h10"></div>

                                <label for="email">Email:</label> <input name="email" type="text" class="input_field" id="email" maxlength="60" />
                                <div class="cleaner_h10"></div>

                                <label for="subject">Assunto:</label> <input name="assunto" type="text" class="input_field" id="assunto" maxlength="60" />
                                <div class="cleaner_h10"></div>

                                <label for="text">Mensagem:</label> <textarea id="text" name="msg" rows="0" cols="0" class="required"></textarea>
                                <div class="cleaner_h10"></div>

                                <input type="submit" class="submit_btn float_l" name="enviar" id="enviar" value="Enviar" />
                                <input type="reset" class="submit_btn float_r" name="limpar" id="limpar" value="Limpar" />

                            </form>

                        </div>

                    </div>

                    <div class="col_w420 float_r" id="map">

                        <h3>Integrantes</h3>

                        <a href="images/integrantes_big.jpg" title="Os Integrantes">
                            <img src="images/integrantes_thumb.jpg" alt="Os Integrantes" />
                        </a>

                        <div class="cleaner h30"></div>

                    </div>

                    <div class="cleaner"></div>
                </div>

                <div class="cleaner"></div>
            </div> <!-- end of main -->

        </div> <!-- end of wrapper -->

        <div id="templatemo_footer_wrapper">
            <div id="templatemo_footer">

                Copyright © 2011 <a href="index.php">Controle Financeiro</a> - 
                Desenvolvido por: Bruno, Guilherme, Luan e Renato.

            </div> <!-- end of footer wrapper -->
        </div> <!-- end of footer -->

    </body>
</html>
