<%-- 
    Document   : logado
    Created on : 01/11/2011, 20:36:12
    Author     : j211-11
--%>

<%@page import="DAO.UsuarioDAO"%>
<%@page import="Model.Usuario"%>
<%

    HttpSession sessao = request.getSession();
    Usuario DadoUSu = null;

    if (session.getAttribute("login") == null) {
        response.sendRedirect("login.jsp");
    } else {

        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        UsuarioDAO usuariodao = new UsuarioDAO();
        DadoUSu = usuariodao.populaUsuario(usuario.getId());
    }

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <title> Controle Financeiro - Home </title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link href="templatemo_style.css" rel="stylesheet" type="text/css" />
    <script language="javascript" type="text/javascript">
        function clearText(field)
        {
            if (field.defaultValue == field.value) field.value = '';
            else if (field.value == '') field.value = field.defaultValue;
        }
    </script>


    <link rel="stylesheet" href="css/nivo-slider.css" type="text/css" media="screen" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" type="text/javascript"></script>
    <script src="script/jquery.nivo.slider.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(window).load(function() {
            $('#slider').nivoSlider({
                effect:'random',
                slices:10,
                animSpeed:1000,
                pauseTime:10000,
                startSlide:0, //Set starting Slide (0 index)
                directionNav:false,
                directionNavHide:false, //Only show on hover
                controlNav:false, //1,2,3...
                controlNavThumbs:false, //Use thumbnails for Control Nav
                pauseOnHover:true, //Stop animation while hovering
                manualAdvance:false, //Force manual transitions
                captionOpacity:0.6, //Universal caption opacity
                beforeChange: function(){},
                afterChange: function(){},
                slideshowEnd: function(){} //Triggers after all slides have been shown
            });
        });
    </script>
</head>
<body class="homepage">

    <div id="templatemo_wrapper">
        <div id="templatemo_header">

            <div id="site_title"><h1><a href="http://www.templatemo.com">Controle Financeiro</a> <span>Controlando seu Dinheiro</span></h1></div>

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
                <li><a href="logado.jsp" class="current">Início</a></li>
                <li><a href="movimentacao.jsp">Movimentação</a></li>
                <li><a href="alterar.jsp">Alterar Dados</a></li>
                <li><a href="contato.jsp">Contato</a></li>
                <li><a href="sair.jsp">Sair</a></li>
            </ul>    	
            <div class="cleaner"></div>
        </div> <!-- end of templatemo_menu -->

        <div id="templatemo_middle">

            <div id="intro">
                <h2>Sobre nós</h2>
                <p>Somos um grupo de estudantes que não tivemos tempo para fazer uma aplicação muito complexa em java. Mas demos o melhor de nós.</p>
            </div>

            <div id="slider">
                <a href="#"><img src="images/slideshow/01.jpg" alt="" title="Lorem ipsum dolor sit amet." /></a>
                <a href="#"><img src="images/slideshow/02.jpg" alt="" title="Nullam ante leo, consectetur eget.." /></a>
                <a href="#"><img src="images/slideshow/03.jpg" alt="" title="Suspendisse sit amet enim elit." /></a>
                <a href="#"><img src="images/slideshow/04.jpg" alt="" title="Nulla faucibus luctus quam eget placerat." /></a>
                <a href="#"><img src="images/slideshow/05.jpg" alt="" title="Phasellus aliquet viverra posuere. " /></a>
            </div>
        </div>

        <div id="templatemo_main">
            <%
                out.print(session.getAttribute("login"));
            %>
            <div class="cleaner"></div>
        </div> <!-- end of main -->

    </div> <!-- end of wrapper -->

    <div id="templatemo_footer_wrapper">
        <div id="templatemo_footer">

            Copyright © 2011 <a href="logado_exemplo.jsp">Controle Financeiro</a> - 
            Desenvolvido por: Bruno, Guilherme, Luan e Renato.

        </div> <!-- end of footer wrapper -->
    </div> <!-- end of footer -->

</body>
</html>
