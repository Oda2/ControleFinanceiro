<%-- 
    Document   : alterar
    Created on : 01/11/2011, 20:37:30
    Author     : j211-11
--%>

<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="Model.Usuario"%>
<%@page import="DAO.UsuarioDAO"%>

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
    <body class="subpage">

        <%
            HttpSession sessao = request.getSession();

            Usuario DadoUSu = null;
            String salario = null;
            String dataMov = null;

            if (sessao.getAttribute("login") != null) {
                Usuario usuario = (Usuario) sessao.getAttribute("usuario");
                UsuarioDAO usuariodao = new UsuarioDAO();

                DadoUSu = usuariodao.populaUsuario(usuario.getId());
                DecimalFormat df = new DecimalFormat("#,###,##0.00");
                salario = df.format(DadoUSu.getSalarioUsu());

                try {
                    SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");
                    dataMov = sdf3.format(DadoUSu.getDataNascimento());
                } catch (Exception ex) {
                    new RuntimeException("Erro na data");
                }

            } else {
                response.sendRedirect("login.jsp");
            }
            
            sessao.removeAttribute("idMovimento");

        %>


        <div id="templatemo_wrapper">
            <div id="templatemo_header">

                <div id="site_title"><h1><a href="login.jsp">Controle Financeiro</a></h1></div>



                <div class="cleaner"></div>
            </div>

            <div id="templatemo_menu">
                <ul>
                    <li><a href="logado_exemplo.jsp">Início</a></li>
                    <li><a href="movimentacao.jsp">Movimentação</a></li>
                    <li><a href="alterar.jsp" class="current">Alterar Dados</a></li>                   
                    <li><a href="sair.jsp">Sair</a></li>
                </ul>    	
                <div class="cleaner"></div>
            </div> <!-- end of templatemo_menu -->

            <div id="templatemo_middle_subpage">
                <h2>Altere seus dados como desejar.</h2>
            </div>

            <div id="templatemo_main">

                <div class="col_w900 col_w900_last" align="center">
                    <form action ="alterarUsu" method="post"> 
                        <table border="0">
                            <tr>

                                <td><input type="hidden" name="Aid" value="<%=DadoUSu.getId()%>" size="20"></td>
                            </tr>

                            <tr>
                                <td align="right">Nome: </td>
                                <td><input type="text" name="Anome" value="<%=DadoUSu.getNomeUsu()%>" size="20"></td>
                            </tr>

                            <tr>
                                <td align="right">Sexo: </td>
                                <td>
                                    <%  if (DadoUSu.getSexoUsu().equals("M")) {%>
                                    <input type="radio" name="Asexo" value="M" id="sexo_0" checked="checked" /> Masculino
                                    <input type="radio" name="Asexo" value="F" id="sexo_1" /> Feminino
                                    <%} else {%>
                                    <input type="radio" name="Asexo" value="M" id="sexo_0"  /> Masculino
                                    <input type="radio" name="Asexo" value="F" id="sexo_1"checked="checked" /> Feminino
                                    <% }%>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">Data de Nascimento: </td>
                                <td><input type="text" name="Adata" size="20" value="<%=dataMov%>" maxlength="10" onblur="javascript:validarData(this);" onkeypress="javascript:return SoAceitaNumero(event);" onkeydown="javascript:FormataData(this, event); BotaoDefaultForm(ctl00_ConteudoPagina_btnSolicita);"></td>
                            </tr>
                            <tr>
                                <td align="right">Login: </td>
                                <td><input type="text" name="Alogin" value="<%=DadoUSu.getLogin()%>" size="20"></td>
                            </tr>
                            <tr>
                                <td align="right">Senha: </td>
                                <td><input type="password" name="Asenha" value="<%=DadoUSu.getSenhaUsu()%>" size="20"></td>
                            </tr>
                            <tr>
                                <td align="right">Confirme a senha: </td>
                                <td><input type="password" name="Aconf_senha" value="<%=DadoUSu.getSenhaUsu()%>" size="20"></td>
                            </tr>
                            <tr>
                                <td align="right">Salário:</td>
                                <td><input type="text" name="Asalario" value="<%= salario%>"  value="0,00"  size="20" onkeypress="reais(this,event)" onkeydown="backspace(this,event)"></td>
                            </tr>
                            <tr>  
                                <td align="right">Email: </td>
                                <td><input type="text" name="Aemail" value="<%=DadoUSu.getEmail()%>" size="20"></td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center"><br /><input name="env" type="submit" value="Alterar" /></td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center"><br /> <%=(request.getAttribute("Mensagem") == null ? "" : request.getAttribute("Mensagem"))%></td>
                            </tr>
                        </table>
                    </form>     
                    <div class="cleaner"></div>
                </div>

                <div class="cleaner"></div>
            </div> <!-- end of main -->

        </div> <!-- end of wrapper -->

        <div id="templatemo_footer_wrapper">
            <div id="templatemo_footer">

                Copyright © 2011 <a href="login.jsp">Controle Financeiro</a> - 
                Desenvolvido por: Bruno, Guilherme, Luan e Renato.

            </div> <!-- end of footer wrapper -->
        </div> <!-- end of footer -->

    </body>
</html>