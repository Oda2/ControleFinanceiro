package Servlet;

import DAO.UsuarioDAO;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
public class alterarUsu extends HttpServlet {

    Usuario usuario = new Usuario();
    UsuarioDAO usuarioDao = new UsuarioDAO();
    Criptografia cripta = new Criptografia();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date dataNasc = null;
    DecimalFormat df = new DecimalFormat("#,###,##0.00");
    String mensagem = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {

        PrintWriter out = response.getWriter();
        try {

            String id = request.getParameter("Aid");
            int n = Integer.parseInt(id);
            usuario.setId(n);

            String nome = request.getParameter("Anome");
            usuario.setNomeUsu(nome);

            String sexo = request.getParameter("Asexo");
            usuario.setSexoUsu(sexo);

            String data = request.getParameter("Adata");

            try {
                dataNasc = new Date(sdf.parse(data).getTime());
            } catch (ParseException ex) {
                Logger.getLogger(alterarUsu.class.getName()).log(Level.SEVERE, null, ex);
            }
            usuario.setDataNascimento(dataNasc);

            String login = request.getParameter("Alogin");
            usuario.setLogin(login);


            String salario = request.getParameter("Asalario");
            try {
                salario = Long.toString((Long) df.parse(salario));

            } catch (ParseException e) {
                e.printStackTrace();
            }
            double salarioN = Double.parseDouble(salario);
            usuario.setSalarioUSu(salarioN);

            String email = request.getParameter("Aemail");
            usuario.setEmail(email);


            String senha = request.getParameter("Asenha");
            String senha2 = request.getParameter("Aconf_senha");


            if (senha.equals(senha2)) {
                String encrypt = cripta.encripta(senha);
                usuario.setSenhaUsu(encrypt);
                usuarioDao.alterar(usuario);
                mensagem = " Usuario alterado com sucesso";
                request.setAttribute("Mensagem", mensagem);
                request.getRequestDispatcher("alterar.jsp").forward(request, response);
            } else {
                mensagem = "Senhas Diferentes digite novamente!";
                request.setAttribute("Mensagem", mensagem);
                request.getRequestDispatcher("alterar.jsp").forward(request, response);
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
            Logger.getLogger(alterarUsu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(alterarUsu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(alterarUsu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(alterarUsu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(alterarUsu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(alterarUsu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (BadPaddingException ex) {
            Logger.getLogger(alterarUsu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(alterarUsu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(alterarUsu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(alterarUsu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(alterarUsu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(alterarUsu.class.getName()).log(Level.SEVERE, null, ex);
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
