/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.ParcelasDAO;
import DAO.ParcelasViewDAO;
import Model.ParcelaView;
import Model.Parcelas;
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
public class ParcelaHomeExl extends HttpServlet {

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
            String mensagem = "";

            HttpSession session = request.getSession();
            int idMovimentacao = 0;
            idMovimentacao = Integer.parseInt(idMovimentacaostr);

            int idParcela = 0;
            idParcela = Integer.parseInt(idParcelaStr);

            ParcelasDAO parcDAO = new ParcelasDAO();
            Parcelas parc = new Parcelas();

            parc = parcDAO.listarParcelas(idParcela, idMovimentacao);

            if (parc.getAtualizado().equals("S")) {
                mensagem = "A Parcela " + idParcelaStr + " já foi atualizada. Exclua a atualização da mesma, antes de continuar.";
                session.setAttribute("mensagemExclParc", mensagem);                
            } else if (parc.getDataPagamento() != null) {
                mensagem = "A Parcela " + idParcelaStr + " já está com a data do Pagamento atualizada. Exclua a atualização antes de continuar.";
                session.setAttribute("mensagemExclParc", mensagem);                
            } else {
                parcDAO.excluir(idMovimentacao, idParcela);
            }

            response.sendRedirect("movimentacao.jsp");
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
