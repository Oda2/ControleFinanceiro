/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.ParcelasViewDAO;
import Model.ParcelaView;
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
public class ParcelaHomeEdit extends HttpServlet {

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
            String idMovimentacaostr = request.getParameter("idMov");
            String idParcelaStr = request.getParameter("idParc");

            int idMovimentacao = 0;
            idMovimentacao = Integer.parseInt(idMovimentacaostr);

            int idParcela = 0;
            idParcela = Integer.parseInt(idParcelaStr);

            ParcelaView parcView = new ParcelaView();
            ParcelasViewDAO parcDAO = new ParcelasViewDAO();

            parcView = parcDAO.listarParcelaEsp(idMovimentacao, idParcela);
            HttpSession session = request.getSession();

            if (parcView.getQtde() > 0) {
                session.setAttribute("idMovimentoEdit", idMovimentacao);
                session.setAttribute("idParcelaEdit", idParcela);
                response.sendRedirect("editar_parcelas.jsp");
            } else {
                session.setAttribute("idMovimento", null);
                session.setAttribute("idParcelaEdit", null);
                response.sendRedirect("movimentacao.jsp");
            }

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
