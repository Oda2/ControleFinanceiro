package Servlet;

import DAO.FormaMovimentacaoDAO;
import DAO.MovimentacaoDAO;
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

@WebServlet(name = "testa_Usu", urlPatterns = {"/testa_Usu"})
public class movimentacaoUsu extends HttpServlet {

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String id = request.getParameter("id");
            int idUsu = Integer.parseInt(id);
            usuario.setId(idUsu);

            String data3 = request.getParameter("data_Mov");
            SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");
            Date dataMov = null;
            try {
                dataMov = new Date(sdf.parse(data3).getTime());
            } catch (ParseException ex) {
                Logger.getLogger(movimentacaoUsu.class.getName()).log(Level.SEVERE, null, ex);
            }
            movimentacao.setDataMov(dataMov);

            String idMov = request.getParameter("formaPagamento");
            formaMovi.setIdFormaMovimentacao(idMov);


            String valorIntegral = request.getParameter("valorIntegral");

            try {
                valorIntegral = Long.toString((Long) dfVT.parse(valorIntegral));

            } catch (ParseException e) {
                e.printStackTrace();
            }
            double valorintegral = Double.parseDouble(valorIntegral);
            movimentacao.setValorTotal(valorintegral);

            String qtdParcelas = request.getParameter("qtd");
            int qtdParc = Integer.parseInt(qtdParcelas);
            parcelas.setQtdeParcelas(qtdParc);


            String valorEntrada = request.getParameter("valorEntrada");
            try {
                valorEntrada = Long.toString((Long) dfVT.parse(valorEntrada));

            } catch (ParseException e) {
                e.printStackTrace();
            }
            double valorEnt = Double.parseDouble(valorEntrada);
            parcelas.setParcela_Entrada(valorEnt);

            String desc = request.getParameter("desc");
            movimentacao.setDescricao(desc);

            movimentacaoDao.execute(movimentacao, idMov, idUsu, qtdParc, valorEnt);
            mensagem = " cadastro efetuado ";
            request.setAttribute("mensagem", mensagem);
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
