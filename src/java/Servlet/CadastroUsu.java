package Servlet;

import DAO.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Criptografia;
import Model.Usuario;

@WebServlet(name = "testa_Usu", urlPatterns = {"/testa_Usu"})
public class CadastroUsu extends HttpServlet {

    Usuario usuario = new Usuario();
    UsuarioDAO usuarioDao = new UsuarioDAO();
    Criptografia cripta = new Criptografia();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date dataNasc = null;
    DecimalFormat df = new DecimalFormat("#,###,##0.00");
    String mensagem = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String nome = request.getParameter("nome");
            usuario.setNomeUsu(nome);

            String sexo = request.getParameter("sexo");
            usuario.setSexoUsu(sexo);

            String data = request.getParameter("data");

            try {
                dataNasc = new Date(sdf.parse(data).getTime());
            } catch (ParseException ex) {
                Logger.getLogger(CadastroUsu.class.getName()).log(Level.SEVERE, null, ex);
            }
            usuario.setDataNascimento(dataNasc);

            String login = request.getParameter("login");
            usuario.setLogin(login);




            String salario = request.getParameter("salario");

            try {
                salario = Long.toString((Long) df.parse(salario));

            } catch (ParseException e) {
                e.printStackTrace();
            }
            double salarioN = Double.parseDouble(salario);
            usuario.setSalarioUSu(salarioN);

            String email = request.getParameter("email");
            usuario.setEmail(email);


            String senha = request.getParameter("senha");
            String senha2 = request.getParameter("conf_senha");


            if (senha.equals(senha2)) {
                String encrypt = cripta.encripta(senha);
                usuario.setSenhaUsu(encrypt);
                usuarioDao.inserir(usuario);
                mensagem = " Usuario cadastrado com sucesso";
                request.setAttribute("mensagem", mensagem);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                mensagem = "Senhas Diferentes digite novamente!";
                request.setAttribute("mensagem", mensagem);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }






        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (BadPaddingException ex) {
            Logger.getLogger(CadastroUsu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(CadastroUsu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(CadastroUsu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CadastroUsu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CadastroUsu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(CadastroUsu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (BadPaddingException ex) {
            Logger.getLogger(CadastroUsu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(CadastroUsu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(CadastroUsu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CadastroUsu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CadastroUsu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(CadastroUsu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
