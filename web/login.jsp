<%-- 
    Document   : login
    Created on : 31/10/2011, 20:04:48
    Author     : j211-11
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Controle Financeiro - Home</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link href="templatemo_style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src ="funcoes.js" charset="utf-8"/> 
    <script language="javascript" type="text/javascript">
        function clearText(field)
        {
            if (field.defaultValue == field.value) field.value = '';
            else if (field.value == '') field.value = field.defaultValue;
        }
    </script>


    <link rel="stylesheet" href="css/nivo-slider.css" type="text/css" media="screen" />
    <script src="colorbox/jquery.min.js" type="text/javascript"></script>
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
    <link media="screen" rel="stylesheet" href="colorbox/colorbox.css" />
    <script src="colorbox/jquery.min.js">
    <script src="colorbox/jquery.colorbox.js" type="text/javascript"/>
    <script>
        $(document).ready(function(){
            $(".inline").colorbox({inline:true, width:"50%"});
        });
    </script>


</head>
<body class="homepage">

    <div id="templatemo_wrapper">
        <div id="templatemo_header">

            <div id="site_title"><h1><a href="http://www.templatemo.com">Controle Financeiro</a> <span>Controlando seu Dinheiro</span></h1></div>

            <div id="search_box">
                <form action="#" method="get">
                    <input type="text" value="31/12/2011" name="q" size="10" id="searchfield" title="searchfield" onFocus="clearText(this)" onBlur="clearText(this)" />
                    <input type="submit" name="Search" value="" id="searchbutton" title="Search" />
                </form>
            </div>

            <div class="cleaner"></div>
        </div>

        <div id="templatemo_menu">
            <ul>


            </ul>    	
            <div class="cleaner"></div>
        </div> <!-- end of templatemo_menu -->

        <div id="templatemo_middle">

            <div id="intro">
                <h2>Sobre nós</h2>
                <p>Somos um grupo de estudantes que não tivemos tempo para fazer uma aplicação muito complexa em java. Mas demos o melhor de nós.</p>
            </div>

            <div id="slider">
                <a href="#"><img src="images/slideshow/01.jpg" alt="" title="Real sofre enorme desvalorização no exterior." /></a>
                <a href="#"><img src="images/slideshow/02.jpg" alt="" title="Dólar tem forte alta em relação ao preço do barril de petróleo." /></a>
                <a href="#"><img src="images/slideshow/03.jpg" alt="" title="Petróleo não será mais o combustível padrão." /></a>
                <a href="#"><img src="images/slideshow/04.jpg" alt="" title="China fará viagem espacial tripulada em 2012." /></a>
                <a href="#"><img src="images/slideshow/05.jpg" alt="" title="Empresa Gol tem queda nos preços das passagens." /></a>
            </div>
        </div>

        <div id="templatemo_main">
            <div align="center">

                <form action="testa_Usu" method="post">
                    <table border="0">
                        <tr>
                            <td align="right">Login: </td> 
                            <td><input type="text" name="login" size="20"></td>
                        </tr>
                        <tr>
                            <td align="right">Senha: </td>
                            <td><input type="password" name="senha" size="20" /></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                <br /><input name="logar" type="submit" value="Logar" />
                            </td>
                        </tr>    
                        <tr>
                            <td colspan="2" align="center"><br /> <%=(request.getAttribute("mensagem") == null ? "" : request.getAttribute("mensagem"))%></td>
                        </tr>                    
                        <tr>

                            <td align="center" colspan="2">
                                <br /><br />Não tem cadastro? <a class="inline" href="#escondido">Cadastrar-se</a><br /><br />
                            </td>
                        </tr> 
                    </table>
                    <div style='display:none'>
                        <div id='escondido' align="center" style='padding:10px; background:#fff;'>
                            <table>
                                <form action="Servlet/CadastroUsu" method="post">
                                    <tr>
                                        <td align="right">Nome: </td>
                                        <td><input type="text" name="nome" size="20"></td>
                                    </tr>
                                    <tr>
                                        <td align="right">Sexo: </td>
                                        <td>
                                            <input type="radio" name="sexo" value="M" id="sexo_0" checked="checked" /> Masculino
                                            <input type="radio" name="sexo" value="F" id="sexo_1" /> Feminino
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">Data de Nascimento: </td>
                                        <td><input type="text" name="data"  size="20" maxlength="10" onblur="javascript:validarData(this);" onkeypress="javascript:return SoAceitaNumero(event);" onkeydown="javascript:FormataData(this, event); BotaoDefaultForm(ctl00_ConteudoPagina_btnSolicita);"></td>
                                    </tr>

                                    <tr>
                                        <td align="right"> Login: </td>
                                        <td><input type="text" name="login"  size="20"></td>
                                    </tr>
                                    <tr>
                                        <td align="right">Senha:</td>
                                        <td><input type="password" name="senha"  size="20"></td>
                                    </tr>
                                    <tr>
                                        <td align="right">Confirme a senha:</td>
                                        <td><input type="password" name="conf_senha"  size="20"></td>                               
                                    </tr>
                                    <tr>
                                        <td align="right">Salário:</td>
                                        <td><input type="text" name="salario" value="0,00"  size="20" onkeypress="reais(this,event)" onkeydown="backspace(this,event)" ></td>
                                    </tr>
                                    <tr>
                                        <td align="right">Email:</td>
                                        <td><input type="text" name="email"  size="20"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" align="center"><br /><input name="gravar" type="submit" value="Cadastrar" /></td>
                                    </tr>
                                </form>
                            </table> 
                        </div>
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
