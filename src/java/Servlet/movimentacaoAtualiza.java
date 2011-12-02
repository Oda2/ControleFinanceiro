/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.FormaMovimentacaoDAO;
import DAO.MovimentacaoDAO;
import DAO.MovimentacaoViewDAO;
import DAO.ParcelasDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.FormaMovimentacao;
import Model.Movimentacao;
import Model.Parcelas;
import Model.Usuario;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Renato Oda
 */
public class movimentacaoAtualiza extends HttpServlet {

    Parcelas parcelas = new Parcelas();
    ParcelasDAO parcelasDao = new ParcelasDAO();
    Movimentacao movimentacao = new Movimentacao();
    MovimentacaoDAO movimentacaoDao = new MovimentacaoDAO();
    FormaMovimentacao formaMovi = new FormaMovimentacao();
    FormaMovimentacaoDAO formaMoviDao = new FormaMovimentacaoDAO();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat dfVT = new DecimalFormat("#,###,##0.00");
    String mensagem = "";
    Usuario usuario = new Usuario();
    String mensagemAviso = "";
    double valorintegralFinal = 0.00;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String id = request.getParameter("id");
            int idUsu = Integer.parseInt(id);
            usuario.setId(idUsu);

            String idMovimentacao = request.getParameter("idMov");
            int idMovimentacaoInt = Integer.parseInt(idMovimentacao);
            movimentacao.setIdMov(idMovimentacaoInt);

            String data3 = request.getParameter("data_Mov");
            SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");
            Date dataMovimentacao = null;
            try {
                dataMovimentacao = new Date(sdf.parse(data3).getTime());
                movimentacao.setDataMov(dataMovimentacao);
            } catch (ParseException ex) {
                Logger.getLogger(movimentacaoUsu.class.getName()).log(Level.SEVERE, null, ex);
            }

            String idMov = request.getParameter("formaPagamento");
            formaMovi.setIdFormaMovimentacao(idMov);

            String valorIntegral = request.getParameter("valorIntegral");
            valorIntegral = valorIntegral.replace(".", "");
            valorIntegral = valorIntegral.replace(",", ".");
            valorintegralFinal = Double.parseDouble(valorIntegral);

            movimentacao.setValorTotal(valorintegralFinal);

            String qtdParcelas = request.getParameter("qtd");
            int qtdParc = Integer.parseInt(qtdParcelas);
            parcelas.setQtdeParcelas(qtdParc);


            String valorEntrada = request.getParameter("valorEntrada");
            try {
                valorEntrada = Long.toString((Long) dfVT.parse(valorEntrada));

            } catch (ParseException e) {
                e.printStackTrace();
            }


            String desc = request.getParameter("desc");
            movimentacao.setDescricao(desc);

            HttpSession session = request.getSession();

            MovimentacaoViewDAO movDao = new MovimentacaoViewDAO();

            movimentacaoDao.alterar(movimentacao, formaMovi.getIdFormaMovimentacao());

            mensagemAviso = movDao.recalculaMovimentacoes(idMovimentacaoInt, "N");
            session.setAttribute("mensagemRecalcula", mensagemAviso);

            mensagem = " cadastro alterado com Sucesso ";
            request.setAttribute("mensagem", mensagem);
            session.setAttribute("idMovimento", idMovimentacao);
            request.getRequestDispatcher("movimentacao.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(movimentacaoAtualiza.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(movimentacaoAtualiza.class.getName()).log(Level.SEVERE, null, ex);
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
