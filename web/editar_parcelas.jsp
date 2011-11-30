<%@page import="Displaytag.*" %>  
<%@page import="Model.Usuario" %>
<%@page import="DAO.UsuarioDAO" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<html>
    <head> 

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <title>Controle Financeiro - Pagína Inicial</title>

        <meta name="keywords" content="ocean blue, gallery page, template, free css templates, CSS, HTML" />
        <meta name="description" content="Ocean Blue Gallery - Free CSS Template provided by templatemo.com" />

        <link href="templatemo_style.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="./css/print.css" type="text/css" media="print" />


        <script language="javascript" type="text/javascript">
            function clearText(field)
            {
                if (field.defaultValue == field.value) field.value = '';
                else if (field.value == '') field.value = field.defaultValue;
            }
        </script>

        <link rel="stylesheet" type="text/css" href="css/jquery.lightbox-0.5.css" />

        <!-- Arquivos utilizados pelo jQuery lightBox plugin -->
        <script language="JavaScript" src ="funcoes.js" charset="utf-8"/> 
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


    <%
        HttpSession sessao = request.getSession();
        Usuario DadoUSu = null;
        int idUsuario = 0;

        if (session.getAttribute("login") == null) {
            response.sendRedirect("login.jsp");
        } else {

            Usuario usuario = (Usuario) sessao.getAttribute("usuario");
            UsuarioDAO usuariodao = new UsuarioDAO();
            DadoUSu = usuariodao.populaUsuario(usuario.getId());

            idUsuario = usuario.getId();
        }

        Movimentacaodisplay mov = new Movimentacaodisplay(idUsuario);
        request.setAttribute("home", mov);

    %>


    <body class="subpage">

        <div id="templatemo_wrapper">
            <div id="templatemo_header">

                <div id="site_title"><h1><a href="login.jsp">Controle Financeiro</a></h1></div>

                <div class="cleaner"></div>
            </div>

            <div id="templatemo_menu">
                <ul>
                    <li><a href="logado_exemplo.jsp" class="current">Início</a></li>
                    <li><a href="movimentacao.jsp">Movimentação</a></li>
                    <li><a href="alterar.jsp">Alterar Dados</a></li>
                    <li><a href="sair.jsp">Sair</a></li>
                </ul>    	
                <div class="cleaner"></div>
            </div> <!-- end of templatemo_menu -->

            <div id="templatemo_middle_subpage">
                <h2>Movimentação</h2>
            </div>

            <div id="templatemo_main">

                <div class="col_w900 col_w900_last" align="center">                
                    <table cellpadding="7" border="1" width="700px">
                        <tr>
                            <td align="center"><b>Valor da parcela</b></td>
                            <td align="center"><b>Data de vencimento</b></td>
                            <td align="center"><b>Data de pagamento</b></td>
                            <td align="center"><b>Atualizado</b></td>
                        </tr>
                        <!-- Esse tr é randômico e vem a partir de um laço em JAVA -->
                        <tr>
                            <td align="center"><input type="text" name="valor_parcela" /></td>
                            <td align="center"><input type="text" name="data_vencimento" /></td>
                            <td align="center"><input type="text" name="data_pagamento" /></td>
                            <td align="center"><input type="radio" id="S" name="atualizado" value="S" /> Sim <input type="radio" id="N" name="atualizado" value="N" /> Não </td>
                        </tr>
                    </table>
                   
                    <div class="cleaner"></div>
                </div>

                <div class="cleaner"></div>
            </div> <!-- end of main -->

        </div> <!--  final -->

        <div id="templatemo_footer_wrapper">
            <div id="templatemo_footer">

                Copyright © 2011 <a href="logado_exemplo.jsp">Controle Financeiro</a> - 
                Desenvolvido por: Bruno, Guilherme, Luan e Renato.

            </div> <!-- end of footer wrapper -->
        </div> <!-- end of footer -->
    </body>
</html>