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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Renato Oda
 */
public class MovimentacaoHome extends HttpServlet {

    private MovimentacaoView MovimentacaoView;

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

            //Passo ele para inteiro
            int idMovimentacao = 0;
            idMovimentacao = Integer.parseInt(idMovimentacaostr);

            //Crio o objeto que vai receber os dados ref. a movimentação
            MovimentacaoView movimentacao = new MovimentacaoView();
            MovimentacaoViewDAO movimentacaoDAO = new MovimentacaoViewDAO();

            movimentacao = (MovimentacaoView) movimentacaoDAO.listarMov(idMovimentacao);

            HttpSession session = request.getSession();               
            
            if (movimentacao.getQtde() > 0) {
                session.setAttribute("idMovimento", idMovimentacaostr);
            } else {
                request.setAttribute("idMovimento", null);
            }
                                              
            response.sendRedirect("movimentacao.jsp");
            //request.getRequestDispatcher("movimentacao.jsp").forward(request, response);

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
}
