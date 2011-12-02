<%@page import="Model.ParcelaView"%>
<%@page import="Displaytag.ParcelaDisplayTag"%>
<%@page import="DAO.MovimentacaoViewDAO"%>
<%@page import="Model.MovimentacaoView"%>
<%@page import="Model.Movimentacao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="DAO.UsuarioDAO"%>
<%@page import="Model.Usuario"%>
<%@page import="javax.mail.FetchProfile.Item"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.FormaMovimentacao"%>
<%@page import="DAO.FormaMovimentacaoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Controle Financeiro - Movimentações</title>
        <meta name="keywords" content="ocean blue, blog template, free css templates, CSS, HTML" />
        <meta name="description" content="Ocean Blue Blog - Free CSS Template provided by templatemo.com" />
        <link href="templatemo_style.css" rel="stylesheet" type="text/css" />

        <style type="text/css" media="all">
            @import url("css/maven-base.css"); 
            @import url("css/maven-theme.css"); 
            @import url("css/site.css"); 
            @import url("css/screen.css");
        </style>

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
        <script type="text/javascript" src="js/jquery.js"></script>
        <script language="JavaScript" src ="funcoes.js" charset="utf-8"/> 
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
        <%
            HttpSession sessao = request.getSession();

            Usuario DadoUSu = null;
            MovimentacaoView movimentacoes = null;
            String dataFormatada = "";
            DecimalFormat df = new DecimalFormat("#,###,##0.00");
            String valorTotal = "0.00";
            String valorEntrada = "0.00";
            ParcelaDisplayTag parcDisplay = null;
            String mensagemAviso = "";

            if (session.getAttribute("login") == null) {
                response.sendRedirect("login.jsp");

            } else {

                Usuario usuario = (Usuario) session.getAttribute("usuario");
                UsuarioDAO usuariodao = new UsuarioDAO();
                DadoUSu = usuariodao.populaUsuario(usuario.getId());

                if (sessao.getAttribute("idMovimento") == null) {
                    movimentacoes = new MovimentacaoView();
                    movimentacoes.setQtde(0);
                    parcDisplay = new ParcelaDisplayTag(0);
                } else {
                    MovimentacaoViewDAO movimentacaoView = new MovimentacaoViewDAO();

                    String idMovimentoStr = (String) sessao.getAttribute("idMovimento");

                    int idMovimento = Integer.parseInt(idMovimentoStr);

                    movimentacoes = movimentacaoView.listarMov(idMovimento);

                    parcDisplay = new ParcelaDisplayTag(idMovimento);
                    request.setAttribute("parcHome", parcDisplay);

                    try {
                        SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");
                        dataFormatada = sdf3.format(movimentacoes.getDataMovimentacao());
                    } catch (Exception ex) {
                        new RuntimeException("Erro na data");
                    }
                }

                if (sessao.getAttribute("mensagemExclParc") != null) {
                    mensagemAviso = (String) sessao.getAttribute("mensagemExclParc");
                }
            }
        %>

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
                <h2>Movimentações</h2>
            </div>

            <div id="templatemo_main">

                <div class="col_w900 col_w900_last">

                    <div class="col_w580 float_l">
                        <% String acaoPagina = "";
                            acaoPagina = "movimentacaoUsu";

                            if (movimentacoes.getQtde() > 0) {
                                acaoPagina = "movimentacaoAtualiza";
                            }

                        %>

                        <form  action="<%=acaoPagina%>" method="post">
                            <table border="0">   
                                <tr>
                                    <td><input type="hidden" name="id" value="<%=DadoUSu.getId()%>" size="20"></td>
                                        <%
                                            if (movimentacoes.getQtde() > 0) {
                                        %>                                    
                                    <td><input type="hidden" name="idMov" value="<%=movimentacoes.getIdMovimentacao()%>"/></td>
                                        <% }%>
                                </tr>
                                <tr>
                                    <td align="right">Data da Movimentação: </td>
                                    <td><input type="text" name="data_Mov"  size="20" maxlength="10" value="<%=dataFormatada%>" onblur="javascript:validarData(this);" onkeypress="javascript:return SoAceitaNumero(event);" onkeydown="javascript:FormataData(this, event); BotaoDefaultForm(ctl00_ConteudoPagina_btnSolicita);"/></td>
                                </tr>
                                <tr>
                                    <td align="right">Forma de Pagamento: </td>
                                    <td>

                                        <% String descricaoFormaPagamento = "";
                                            String idDescricaoFormaPagamento = "";

                                            if (movimentacoes.getQtde() > 0) {
                                                descricaoFormaPagamento = movimentacoes.getDescricaoForma();
                                                idDescricaoFormaPagamento = movimentacoes.getIdFormaMovimentacao();
                                            }%>

                                        <select name="formaPagamento" id="form_pag" onchange="show()">                                            
                                            <%
                                                FormaMovimentacaoDAO formaMovimentacaoDao = new FormaMovimentacaoDAO();
                                                List<FormaMovimentacao> formaMovimentacao = new ArrayList<FormaMovimentacao>();
                                                formaMovimentacao = formaMovimentacaoDao.listarTodos();

                                                for (FormaMovimentacao Item : formaMovimentacao) {

                                                    if (Item.getIdFormaMovimentacao().equals(idDescricaoFormaPagamento)) {
                                            %> <option value="<%=Item.getIdFormaMovimentacao()%>" SELECTED> <%=descricaoFormaPagamento%> </option> <%

                                            } else {
                                            %>                                           
                                            <option value="<%=Item.getIdFormaMovimentacao()%>"> <%= Item.getDescricao()%> </option>
                                            <%
                                                    }
                                                }
                                            %>                                            
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Valor integral: </td>
                                    <%
                                        if (movimentacoes.getQtde() > 0) {

                                            valorTotal = df.format(movimentacoes.getValorTotal());
                                            String[] part = valorTotal.split("[,]");
                                            valorTotal = part[0] + "." + part[1];
                                        }
                                    %>
                                    <td><input type="text" name="valorIntegral" value="<%=valorTotal%>"  size="20" onkeypress="reais(this,event)" onkeydown="backspace(this,event)"></td>
                                </tr>
                                <tr>
                                    <td align="right">Parcelas: </td>
                                    <% int parc = 0;

                                        if (movimentacoes.getQtde() > 0) {
                                            parc = movimentacoes.getParcela();
                                        }%>

                                    <td><input type="text" name="qtd" size="20" value="<%=parc%>" />
                                    </td>
                                </tr>

                                <tr>
                                    <td align="right">Valor entrada: </td>

                                    <%
                                        if (movimentacoes.getQtde() > 0) {
                                            valorEntrada = df.format(movimentacoes.getValorTotal());
                                            String[] part = valorEntrada.split("[,]");
                                            valorEntrada = part[0] + "." + part[1];
                                        }%>  

                                    <td><input type="text" name="valorEntrada" value="<%=valorEntrada%>"  size="20" onkeypress="reais(this,event)" onkeydown="backspace(this,event)"></td>
                                </tr>
                                <tr>
                                    <td align="right"><span style="vertical-align:top;">Descrição:</span> </td>

                                    <% String descricaoMovimento = "";

                                        if (movimentacoes.getQtde() > 0) {
                                            descricaoMovimento = movimentacoes.getDescricaoMovimentacao();
                                        }%>

                                    <td>
                                        <textarea name="desc" cols="40" rows="10"><%=descricaoMovimento%></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center"><input name="mov" type="submit" value="Movimentar"  /></td>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center"><br /> <%=(request.getAttribute("mensagem") == null ? "" : request.getAttribute("mensagem"))%></td>
                                </tr>
                            </table>                                
                    </div>                               

                    <div class="col_w280 float_r">

                        <h2>Cotação Atualizada</h2>

                        <div class  

                             ="lbe_box">
                            <p><img src="http://www.debit.com.br/indicadores.php" title="Correção monetária e cálculos trabalhistas"></p>
                            <div class  

                                 ="cleaner"></div>
                        </div>

                        <div class="cleaner h30"></div>

                    </div>

                    <div class="cleaner"></div>

                    <div align="center">
                        <% if (parcDisplay.size() > 0) {%>
                        <display:table name="parcHome" export="false" sort="list" pagesize="10" id="tabela" >
                            <display:column property="idParcela" title="ID Parcela" />
                            <display:column property="numeroParcela" title="Número da Parcela" />
                            <display:column property="valorParcela" title="Valor da Parcela" format="R$ {0,number,###,##0.00}" />
                            <display:column property="dataVencimento" title="Data Vencimento" format="{0,date,dd/MM/yyyy}" />
                            <display:column property="dataPagamento" title="Data do Pagamento" format="{0,date,dd/MM/yyyy}"/>                                

                            <display:column title="Editar" media="html" sortProperty="idParcela" >
                                <a href="ParcelaHomeEdit?idMov=${tabela.idParcela}&idParc=${tabela.numeroParcela}"> <img src="images/icones/edit.png"/> </a>
                                </display:column>

                            <display:column title="Editar" media="html" sortProperty="idParcela" >
                                <a href="ParcelaHomeExl?idMov=${tabela.idParcela}&idParc=${tabela.numeroParcela}"> <img src="images/icones/remove.png" /> </a>
                                </display:column>

                        </display:table>  
                        <%                                } else {
                                out.println("Não há parcelas");
                            }
                            if (mensagemAviso != "") {
                                out.println(mensagemAviso);

                                sessao.removeAttribute("mensagemExclParc");
                            }%> 
                    </div>
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