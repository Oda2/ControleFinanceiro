/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.MovimentacaoDAO;
import DAO.MovimentacaoViewDAO;
import Model.Movimentacao;
import Model.MovimentacaoView;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Renato Oda
 */
public class MovimentacaoHomeDel extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // retorno o "ID da Movimentação"
            String idMovimentacaostr = request.getParameter("id");
            String mensagem = "";
            HttpSession session = request.getSession();

            //Passo ele para inteiro
            int idMovimentacao = 0;
            idMovimentacao = Integer.parseInt(idMovimentacaostr);

            MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();
            Movimentacao mov = new Movimentacao();
            mov = movimentacaoDAO.listarParcMov(idMovimentacao);

            if (mov.getIdMov() > 0) {
                mensagem = "Existem parcelas a serem excluidas. Exclusão da movimentação cancelada.";
                session.setAttribute("mensagemMovExcl", mensagem);
            } else {
                movimentacaoDAO.excluir(idMovimentacao);                
            }
            response.sendRedirect("logado_exemplo.jsp");

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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
