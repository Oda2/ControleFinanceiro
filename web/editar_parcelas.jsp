<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.ParcelaView"%>
<%@page import="DAO.ParcelasViewDAO"%>
<%@page import="DAO.ParcelasDAO"%>
<%@page import="Model.Parcelas"%>
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
        int idMovimentacao = 0;
        int idParcela = 0;
        String valorParcela = "0,00";
        String dataPagamento = "";
        String dataVencimento = "";
        String Atualizado = "N";

        SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat df = new DecimalFormat("#,###,##0.00");

        if (session.getAttribute("login") == null) {
            response.sendRedirect("login.jsp");
        } else {

            Usuario usuario = (Usuario) sessao.getAttribute("usuario");
            UsuarioDAO usuariodao = new UsuarioDAO();
            DadoUSu = usuariodao.populaUsuario(usuario.getId());

            idUsuario = usuario.getId();
        }

        if (session.getAttribute("idMovimentoEdit") != null) {
            idMovimentacao = (Integer) session.getAttribute("idMovimentoEdit");
        }

        if (idMovimentacao == 0) {
            response.sendRedirect("logado_exemplo.jsp");
        }

        if (session.getAttribute("idParcelaEdit") != null) {
            idParcela = (Integer) session.getAttribute("idParcelaEdit");
        }

        if (idParcela == 0) {
            response.sendRedirect("logado_exemplo.jsp");
        }

        ParcelaView parc = new ParcelaView();
        ParcelasViewDAO parcDao = new ParcelasViewDAO();

        parc = parcDao.listarParcelaEsp(idMovimentacao, idParcela);

        if (parc.getQtde() > 0) {
            valorParcela = df.format(parc.getValorParcela());

            if (parc.getDataPagamento() != null) {
                dataPagamento = sdf3.format(parc.getDataPagamento());
            }

            dataVencimento = sdf3.format(parc.getDataVencimento());
            Atualizado = parc.getAtualizado();
        }
    %>


    <body class="subpage">

        <div id="templatemo_wrapper">
            <div id="templatemo_header">

                <div id="site_title"><h1><a href="login.jsp">Controle Financeiro</a></h1></div>

                <div class="cleaner"></div>
            </div>

            <div id="templatemo_menu">
                <ul>
                    <li><a href="logado_exemplo.jsp">Início</a></li>
                    <li><a href="movimentacao.jsp" class="current">Movimentação</a></li>
                    <li><a href="alterar.jsp">Alterar Dados</a></li>
                    <li><a href="sair.jsp">Sair</a></li>
                </ul>    	
                <div class="cleaner"></div>
            </div> <!-- end of templatemo_menu -->

            <div id="templatemo_middle_subpage">
                <h2>Parcelas - Movimentação</h2>
            </div>

            <div id="templatemo_main">

                <div class="col_w900 col_w900_last" align="center"> 

                    <form name="editar_parcela" action="ParcAtualiza">
                        <table cellpadding="7" border="0" width="400px">
                            <td><input type="hidden" name="idMovimentacao" value="<%=idMovimentacao%>" size="20"></td>
                            <td><input type="hidden" name="numeroParcela" value="<%=idParcela%>" size="20"></td>

                            <tr>
                                <td align="right"><b>Valor da Parcela: </b></td>
                                <td align="left"><input type="text" name="valor_parcela" value="<%=valorParcela%>" /></td>
                            </tr>
                            <tr>
                                <td align="right"><b>Data de Vencimento: </b></td>
                                <td align="left"><input type="text" name="data_vencimento" value="<%=dataVencimento%>" /></td>
                            </tr>
                            <tr>
                                <td align="right"><b>Data de Pagamento: </b></td>
                                <td align="left"><input type="text" name="data_pagamento" value="<%=dataPagamento%>" /></td>
                            </tr>
                            <tr>
                                <td align="right"><b>Atualizado: </b></td>
                                <td align="left">
                                    <%  if (Atualizado.equals("S")) {%>
                                    <input type="radio" id="S" name="atualizado" value="S" checked /> Sim 
                                    <input type="radio" id="N" name="atualizado" value="N" /> Não 
                                    <%} else {%>
                                    <input type="radio" id="S" name="atualizado" value="S" /> Sim 
                                    <input type="radio" id="N" name="atualizado" value="N" checked /> Não 
                                </td>
                                <% }%>
                            </tr>
                            <tr>
                                <td colspan="2" align="center"><input name="atualizar_parcela" value="Atualizar" type="submit" /></td>
                            </tr>
                    </form>
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