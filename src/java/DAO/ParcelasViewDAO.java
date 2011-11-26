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
                parcelas.setAtualizado(rs.getString(""));
                parcelas.setDataPagamento(rs.getDate(""));
                parcelas.setDataVencimento(rs.getDate(""));
                parcelas.setIdParcela(rs.getInt(""));
                parcelas.setNumeroParcela(rs.getInt(""));
                parcelas.setParcEntrada(rs.getString(""));
                parcelas.setValorParcela(rs.getDouble(""));

                parcelaList.add(parcelas);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }        
        
        return parcelaList;
    }
}
