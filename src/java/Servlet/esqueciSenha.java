package Servlet;

import DAO.UsuarioDAO;
import Model.Criptografia;
import Model.Usuario;
import Util.EsqueciSenha;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class esqueciSenha extends HttpServlet {

    Usuario usuario = new Usuario();
    UsuarioDAO usuarioDao = new UsuarioDAO();
    Criptografia cripta = new Criptografia();
    String mensagem = "";
    EsqueciSenha dados = new EsqueciSenha();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            Usuario user = new Usuario();

            String email = request.getParameter("esqueciSenha");

            if (request.getParameter("esqueciSenha") != null) {
                user = usuarioDao.recuperaSenha(email);

                if (user.getEmail() != null) {
                    String msg = "Em instantes você recebera uma E-mail com sua senha.";
                    request.setAttribute("mensagem2", msg);
                    dados.Mail(email);
                } else {
                    String msg = "Não foi possível encontrar o e-mail informado.";
                    request.setAttribute("mensagem2", msg);
                }
            } else {
                String msg = "Informe um e-mail válido.";
                request.setAttribute("mensagem2", msg);
            }

            request.getRequestDispatcher("login.jsp").forward(request, response);


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
            Logger.getLogger(esqueciSenha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(esqueciSenha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(esqueciSenha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(esqueciSenha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(esqueciSenha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(esqueciSenha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (BadPaddingException ex) {
            Logger.getLogger(esqueciSenha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(esqueciSenha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(esqueciSenha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(esqueciSenha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(esqueciSenha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(esqueciSenha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
