/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.ParcelaView;
import Util.Conecta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Renato Oda
 */
public class ParcelasViewDAO {

    public List<ParcelaView> listarParcela(int idMovimento) {
        ArrayList<ParcelaView> parcelaList = new ArrayList<ParcelaView>();
        ParcelaView parcelas = null;

        String sql = "SELECT * "
                + "   FROM dbo.Parcelas "
                + "   WHERE Parcelas.Id_Movimentacao = ? ";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, idMovimento);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                parcelas = new ParcelaView();
                parcelas.setAtualizado(rs.getString("Atualizado"));
                parcelas.setDataPagamento(rs.getDate("Data_Pagamento"));
                parcelas.setDataVencimento(rs.getDate("Data_Vencimento"));
                parcelas.setIdParcela(rs.getInt("ID_Movimentacao"));
                parcelas.setNumeroParcela(rs.getInt("Numero_Parcela"));
                parcelas.setParcEntrada(rs.getString("Parcela_Entrada"));
                parcelas.setValorParcela(rs.getDouble("Valor_Parcela"));

                parcelaList.add(parcelas);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parcelaList;
    }

    public ParcelaView listarParcelaEsp(int idMovimento, int idParcela) {
        ParcelaView parcelaList = new ParcelaView();
        ParcelaView parcelas = null;
        int qtde = 0;

        String sql = "SELECT * "
                + "   FROM dbo.Parcelas "
                + "   WHERE ID_Movimentacao = ? AND"
                + "         Numero_Parcela = ? ";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, idMovimento);
            stmt.setInt(2, idParcela);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                qtde = qtde + 1;

                parcelas = new ParcelaView();
                parcelas.setAtualizado(rs.getString("Atualizado"));
                parcelas.setDataPagamento(rs.getDate("Data_Pagamento"));
                parcelas.setDataVencimento(rs.getDate("Data_Vencimento"));
                parcelas.setIdParcela(rs.getInt("ID_Movimentacao"));
                parcelas.setNumeroParcela(rs.getInt("Numero_Parcela"));
                parcelas.setParcEntrada(rs.getString("Parcela_Entrada"));
                parcelas.setValorParcela(rs.getDouble("Valor_Parcela"));
                parcelas.setQtde(qtde);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parcelas;
    }
}
