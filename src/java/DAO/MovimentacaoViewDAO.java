/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.MovimentacaoView;
import Util.Conecta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Renato Oda
 */
public class MovimentacaoViewDAO {

    public List<MovimentacaoView> listarMovimentacao(int idUsuario) {
        ArrayList<MovimentacaoView> movimentacaoList = new ArrayList<MovimentacaoView>();
        MovimentacaoView movimentacao = null;

        String sql = "SELECT * "
                + "   FROM dbo.Movimentacao_Home "
                + "   WHERE Movimentacao_Home.ID_Usuario = ? "
                + "   ORDER BY Data_Movimentacao DESC ";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, idUsuario);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                movimentacao = new MovimentacaoView();
                movimentacao.setIdMovimentacao(rs.getInt("ID_Movimentacao"));
                movimentacao.setValorTotal(rs.getDouble("Valor_Total"));
                movimentacao.setDescricaoMovimentacao(rs.getString("Descricao_Movimento"));
                movimentacao.setDataMovimentacao(rs.getDate("Data_Movimentacao"));
                movimentacao.setDescricaoForma(rs.getString("Descricao_Forma"));
                movimentacao.setParcela(rs.getInt("Numero_Parcela"));
                movimentacao.setIdFormaMovimentacao(rs.getString("ID_Forma_Movimentacao"));
                movimentacao.setValorEntrada(rs.getDouble("Valor_Parc_Entrada"));

                movimentacaoList.add(movimentacao);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movimentacaoList;
    }

    public MovimentacaoView listarMov(int idMovimentacao) {

        MovimentacaoView movimentacoes = new MovimentacaoView();
        String sql = "SELECT * FROM dbo.Movimentacao_Home"
                + "   WHERE ID_Movimentacao = ?";

        try {
            int qtde = 0;

            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idMovimentacao);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                qtde = qtde + 1;

                movimentacoes.setQtde(qtde);
                movimentacoes.setDataMovimentacao(rs.getDate("Data_Movimentacao"));
                movimentacoes.setDescricaoForma(rs.getString("Descricao_Forma"));
                movimentacoes.setDescricaoMovimentacao(rs.getString("Descricao_Movimento"));
                movimentacoes.setIdFormaMovimentacao(rs.getString("ID_Forma_Movimentacao"));
                movimentacoes.setParcela(rs.getInt("Numero_Parcela"));
                movimentacoes.setValorTotal(rs.getDouble("Valor_Total"));
                movimentacoes.setIdMovimentacao(rs.getInt("ID_Movimentacao"));
                movimentacoes.setValorEntrada(rs.getDouble("Valor_Parc_Entrada"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movimentacoes;
    }

    public MovimentacaoView saldoUsuario(int idUsuario) {

        MovimentacaoView movimentacoes = new MovimentacaoView();
        String sql = "SELECT * FROM dbo.Saldo_Usuario_Final"
                + "   WHERE ID_Usuario = ?";

        try {
            int qtde = 0;
            double valor = 0.00;

            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idUsuario);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                qtde = qtde + 1;

                movimentacoes.setQtde(qtde);
                movimentacoes.setValorTotal(rs.getDouble("Valor_Total"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movimentacoes;
    }

    public String recalculaMovimentacoes(int idMovimentacao, String parc) {

        MovimentacaoView movimentacoes = new MovimentacaoView();
        String sql = "EXEC dbo.Recalculo_Movimento @ID_Movimento = ? ,"
                + "                                @Parcela      = ?";

        String mensagem = "";

        try {
            int qtde = 0;

            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, idMovimentacao);
            stmt.setString(2, parc);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                qtde = qtde + 1;

                mensagem = rs.getString("Mensagem");
                movimentacoes.setQtde(qtde);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (movimentacoes.getQtde() == 0) {
            mensagem = "";
        }

        return mensagem;
    }
}
