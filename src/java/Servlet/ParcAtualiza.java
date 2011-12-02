/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.ParcelasDAO;
import Model.Parcelas;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Renato Oda
 */
public class ParcAtualiza extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, java.text.ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            String valorParc = "0,00";
            String dataVenc = "";
            String dataParc = "";
            String atualizado = "";
            String mensagemParc = "";
            String idMovimentacao_Str = "";
            String numeroParcela_str = "";

            double valorParcela = 0.00;
            Date dataVencimento = null;
            Date dataPagamento = null;

            int idMovimentacao;
            int numeroParcela;

            valorParc = request.getParameter("valor_parcela");
            dataVenc = request.getParameter("data_vencimento");
            dataParc = request.getParameter("data_pagamento");
            atualizado = request.getParameter("atualizado");
            idMovimentacao_Str = request.getParameter("idMovimentacao");
            numeroParcela_str = request.getParameter("numeroParcela");

            SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");
            DecimalFormat dfVT = new DecimalFormat("#,###,##0.00");

            ParcelasDAO parcDAO = new ParcelasDAO();
            Parcelas parc = new Parcelas();

            idMovimentacao = Integer.parseInt(idMovimentacao_Str);
            numeroParcela = Integer.parseInt(numeroParcela_str);

            parc.setIdMovimentacao(idMovimentacao);
            parc.setNumeroParcela(numeroParcela);

            try {
                dataVencimento = new Date(sdf3.parse(dataVenc).getTime());
            } catch (ParseException ex) {
                Logger.getLogger(movimentacaoUsu.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (dataParc != "") {
                  dataPagamento = new Date(sdf3.parse(dataParc).getTime());
                }
            } catch (ParseException ex) {
                Logger.getLogger(movimentacaoUsu.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {                
                valorParc = Long.toString((Long) dfVT.parse(valorParc));                                
            } catch (ParseException e) {
                e.printStackTrace();
            }
            valorParcela = Double.parseDouble(valorParc);

            parc.setDataVencimento(dataVencimento);
            parc.setDataPagamento(dataPagamento);
            parc.setAtualizado(atualizado);
            parc.setValorParcela(valorParcela);
            parcDAO.alterar(parc);


            response.sendRedirect("editar_parcelas.jsp");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ParcAtualiza.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(ParcAtualiza.class.getName()).log(Level.SEVERE, null, ex);
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
