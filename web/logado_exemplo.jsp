<jsp:root version="1.2" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:display="urn:jsptld:http://displaytag.sf.net">        

    <jsp:text>
        <![CDATA[<html>]]>
        </jsp:text>

        <jsp:directive.page contentType="text/html; charset=UTF-8" />
        <jsp:directive.page import="Displaytag.*" />  
        <jsp:directive.page import="Model.Usuario" />
        <jsp:directive.page import="DAO.UsuarioDAO" />    

        <jsp:text>
            <![CDATA[<head>]]>
            </jsp:text> 

            <jsp:text>
                <![CDATA[<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />]]>
            </jsp:text>

            <jsp:text>
                <![CDATA[<title>Controle Financeiro - Pagína Inicial</title>]]>
            </jsp:text>

            <jsp:text>
                <![CDATA[<meta name="keywords" content="ocean blue, gallery page, template, free css templates, CSS, HTML" />]]>
            </jsp:text>

            <jsp:text>
                <![CDATA[<meta name="description" content="Ocean Blue Gallery - Free CSS Template provided by templatemo.com" />]]>
            </jsp:text>

            <jsp:text>
                <![CDATA[<link href="templatemo_style.css" rel="stylesheet" type="text/css" />]]>
            </jsp:text>


            <script language="javascript" type="text/javascript">
                function clearText(field)
                {
                    if (field.defaultValue == field.value) field.value = '';
                    else if (field.value == '') field.value = field.defaultValue;
                }
            </script>

            <jsp:text>
                <![CDATA[<link rel="stylesheet" type="text/css" href="css/jquery.lightbox-0.5.css" />]]>
            </jsp:text>

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

            <jsp:text>
                <![CDATA[</head>]]>
            </jsp:text> 


        <jsp:scriptlet>
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

        </jsp:scriptlet>    


        <jsp:text>
            <![CDATA[<body class="subpage">]]>
            </jsp:text>

            <div id="templatemo_wrapper">



                <div id="templatemo_header">


                    <div id="site_title">
                        <h1>
                            <a href="index.html">
                                Controle Financeiro
                            </a> 
                            <span>
                                Controlando seu Dinheiro
                            </span>
                        </h1>
                    </div>

                    <div class="cleaner"></div>


                </div>


                <div id="templatemo_menu">
                    <ul>
                        <li><a href="logado_exemplo.jsp" class="current">Início</a></li>
                        <li><a href="movimentacao.jsp">Movimentação</a></li>
                        <li><a href="alterar.jsp">Alterar Dados</a></li>
                        <li><a href="contato.jsp">Contato</a></li>
                        <li><a href="sair.jsp">Sair</a></li>
                    </ul>    	
                    <div class="cleaner"></div>
                </div> <!-- end of templatemo_menu -->

                <div id="templatemo_middle_subpage">
                    <h2>Movimentação</h2>
                </div>

                <div id="templatemo_main">

                    <div class="col_w900 col_w900_last" align="center">                

                        <jsp:scriptlet>
                            if (mov.size() > 0) {
                        </jsp:scriptlet>      

                        <display:table name="home" export="false" sort="list" pagesize="8">
                            <display:column property="idMovimentacao" title="Codigo (Editar)" group="2" sortable="false" headerClass="sortable" url="/MovimentacaoHome" paramId="id" />
                            <display:column property="descricaoMovimentacao" title="Descricao" group="1" sortable="False" headerClass="sortable" />                            
                            <display:column property="valorTotal" title="Valor (Total)"/>
                            <display:column property="descricaoForma" title="Descricao Forma" />
                            <display:column property="dataMovimentacao" title="Data Movimentacao" />                                                        
                        </display:table>

                        <jsp:scriptlet>                        } else {
                                out.println("Não há movimentação");
                            }
                        </jsp:scriptlet>

                        <div class="cleaner"></div>
                    </div>

                    <div class="cleaner"></div>
                </div> <!-- end of main -->

            </div> <!--  final -->

            <div id="templatemo_footer_wrapper">
                <div id="templatemo_footer">

                    Copyright © 2011 <a href="index.html">Controle Financeiro</a> - 
                    Desenvolvido por: Bruno, Guilherme, Luan e Renato.

                </div> <!-- end of footer wrapper -->
            </div> <!-- end of footer -->

            <jsp:text>
                <![CDATA[</body></html>]]>
            </jsp:text>

</jsp:root>
