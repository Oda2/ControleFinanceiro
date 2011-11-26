
function BotaoDefaultForm(btn) {

    // process only the Enter key
    if (event.keyCode == 13) {
        // cancel the default submit
        event.returnValue = false;
        event.cancel = true;
        // submit the form by programmatically clicking the specified button
        btn.click();
    }
}

function SoAceitaNumero(objEvent)
{
    var iKeyCode, strKey;
    var reValidChars = /\d/;
    if (isIE())
    {
        iKeyCode = objEvent.keyCode;
    }
    else
    {
        iKeyCode = objEvent.which;
    }
    if ((iKeyCode == 8) ||
        (iKeyCode == 0)) { // Trata as teclas delete, backspace e setas
        return true;
    }
    strKey = String.fromCharCode(iKeyCode);
    if (!reValidChars.test(strKey))
    {
        return false;
    } else {
        return true;
    }
} 
function FormataData(campo,teclapres)
{
    var tecla = teclapres.keyCode;
    vr = campo.value;
    vr = vr.replace( ".", "" );
    vr = vr.replace( "/", "" );
    vr = vr.replace( "/", "" );
    tam = vr.length + 1;
    if ( tecla != 9 && tecla != 8 )
    {
        if ( tam > 2 && tam < 5 )
        {
            campo.value = vr.substr( 0, tam - 2 ) + '/' + vr.substr( tam - 2, tam );
        }
        if ( tam >= 5 && tam <= 10 )
        {
            campo.value = vr.substr( 0, 2 ) + '/' + vr.substr( 2, 2 ) + '/' + vr.substr( 4, 4 );
        }
    }
} 
function validarData(campo)
{
    if (campo.value != "")
    {
        var expReg = /^(([0-2]\d|[3][0-1])\/([0]\d|[1][0-2])\/[1-2][0-9]\d{2})$/;
        var msgErro = 'Data inválida.';
        if ((campo.value.match(expReg)) && (campo.value!=''))
        {
            var dia = campo.value.substring(0,2);
            var mes = campo.value.substring(3,5);
            var ano = campo.value.substring(6,10);
            if ((mes==4 || mes==6 || mes==9 || mes==11) && (dia > 30))
            {
                alert("Dia incorreto !!! O mês especificado contém no máximo 30 dias.");
                campo.value = "";
                return false;
            }
            else
            {
                if ((ano%4!=0 && mes==2) && (dia>28))
                {
                    alert("Data incorreta!! O mês especificado contém no máximo 28 dias.");
                    campo.value = "";
                    return false;
                }
                else
                {
                    if ((ano%4==0 && mes==2) && (dia>29))
                    {
                        alert("Data incorreta!! O mês especificado contém no máximo 29 dias.");
                        campo.value = "";
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }
            }
        }
        else
        {
            alert(msgErro);
            campo.value = "";
            return false;
        }
    }
} 
      
documentall = document.all;  
function formatamoney(c) {  
    var t = this;
    if(c == undefined) c = 2;        
    var p, d = (t=t.split("."))[1].substr(0, c);  
    for(p = (t=t[0]).length; (p-=3) >= 1;) {  
        t = t.substr(0,p) + "." + t.substr(p);  
    }  
    return t+","+d+Array(c+1-d.length).join(0);  
}  
  
String.prototype.formatCurrency=formatamoney  
  
function demaskvalue(valor, currency){  
    /* 
* Se currency é false, retorna o valor sem apenas com os números. Se é true, os dois últimos caracteres são considerados as  
* casas decimais 
*/  
    var val2 = '';  
    var strCheck = '0123456789';  
    var len = valor.length;  
    if (len== 0){  
        return 0.00;  
    }  
  
    if (currency ==true){     
        /* Elimina os zeros à esquerda  
      * a variável  <i> passa a ser a localização do primeiro caractere após os zeros e  
      * val2 contém os caracteres (descontando os zeros à esquerda) 
      */  
        
        for(var i = 0; i < len; i++)  
            if ((valor.charAt(i) != '0') && (valor.charAt(i) != ',')) break;  
        
        for(; i < len; i++){  
            if (strCheck.indexOf(valor.charAt(i))!=-1) val2+= valor.charAt(i);  
        }  
  
        if(val2.length==0) return "0.00";  
        if (val2.length==1)return "0.0" + val2;  
        if (val2.length==2)return "0." + val2;  
        
        var parte1 = val2.substring(0,val2.length-2);  
        var parte2 = val2.substring(val2.length-2);  
        var returnvalue = parte1 + "." + parte2;  
        return returnvalue;  
        
    }  
    else{  
        /* currency é false: retornamos os valores COM os zeros à esquerda,  
         * sem considerar os últimos 2 algarismos como casas decimais  
         */  
        val3 ="";  
        for(var k=0; k < len; k++){  
            if (strCheck.indexOf(valor.charAt(k))!=-1) val3+= valor.charAt(k);  
        }           
        return val3;  
    }  
}  
  
function reais(obj,event){  
  
    var whichCode = (window.Event) ? event.which : event.keyCode;  
    /* 
Executa a formatação após o backspace nos navegadores !document.all 
*/  
    if (whichCode == 8 && !documentall) {     
        /* 
Previne a ação padrão nos navegadores 
*/  
        if (event.preventDefault){ //standart browsers  
            event.preventDefault();  
        }else{ // internet explorer  
            event.returnValue = false;  
        }  
        var valor = obj.value;  
        var x = valor.substring(0,valor.length-1);  
        obj.value= demaskvalue(x,true).formatCurrency();  
        return false;  
    }  
    /* 
Executa o Formata Reais e faz o format currency novamente após o backspace 
*/  
    FormataReais(obj,'.',',',event);  
} // end reais  
  
  
function backspace(obj,event){  
    /* 
Essa função basicamente altera o  backspace nos input com máscara reais para os navegadores IE e opera. 
O IE não detecta o keycode 8 no evento keypress, por isso, tratamos no keydown. 
Como o opera suporta o infame document.all, tratamos dele na mesma parte do código. 
*/  
  
    var whichCode = (window.Event) ? event.which : event.keyCode;  
    if (whichCode == 8 && documentall) {     
        var valor = obj.value;  
        var x = valor.substring(0,valor.length-1);  
        var y = demaskvalue(x,true).formatCurrency();  
  
        obj.value =""; //necessário para o opera  
        obj.value += y;  
     
        if (event.preventDefault){ //standart browsers  
            event.preventDefault();  
        }else{ // internet explorer  
            event.returnValue = false;  
        }  
        return false;  
  
    }// end if        
}// end backspace  
  
function FormataReais(fld, milSep, decSep, e) {  
    var sep = 0;  
    var key = '';  
    var i = j = 0;  
    var len = len2 = 0;  
    var strCheck = '0123456789';  
    var aux = aux2 = '';  
    var whichCode = (window.Event) ? e.which : e.keyCode;  
  
    //if (whichCode == 8 ) return true; //backspace - estamos tratando disso em outra função no keydown  
    if (whichCode == 0 ) return true;  
    if (whichCode == 9 ) return true; //tecla tab  
    if (whichCode == 13) return true; //tecla enter  
    if (whichCode == 16) return true; //shift internet explorer  
    if (whichCode == 17) return true; //control no internet explorer  
    if (whichCode == 27 ) return true; //tecla esc  
    if (whichCode == 34 ) return true; //tecla end  
    if (whichCode == 35 ) return true;//tecla end  
    if (whichCode == 36 ) return true; //tecla home  
  
    /* 
O trecho abaixo previne a ação padrão nos navegadores. Não estamos inserindo o caractere normalmente, mas via script 
*/  
  
    if (e.preventDefault){ //standart browsers  
        e.preventDefault()  
    }else{ // internet explorer  
        e.returnValue = false  
    }  
  
    var key = String.fromCharCode(whichCode);  // Valor para o código da Chave  
    if (strCheck.indexOf(key) == -1) return false;  // Chave inválida  
  
    /* 
Concatenamos ao value o keycode de key, se esse for um número 
*/  
    fld.value += key;  
  
    var len = fld.value.length;  
    var bodeaux = demaskvalue(fld.value,true).formatCurrency();  
    fld.value=bodeaux;  
  
    /* 
Essa parte da função tão somente move o cursor para o final no opera. Atualmente não existe como movê-lo no konqueror. 
*/  
    if (fld.createTextRange) {  
        var range = fld.createTextRange();  
        range.collapse(false);  
        range.select();  
    }  
    else if (fld.setSelectionRange) {  
        fld.focus();  
        var length = fld.value.length;  
        fld.setSelectionRange(length, length);  
    }  
    return false;  
  
}  



