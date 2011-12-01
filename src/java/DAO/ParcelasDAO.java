package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Parcelas;
import Util.Conecta;

public class ParcelasDAO {

    public List<Parcelas> populaParcela(int idMov, int idParc) {
        ArrayList<Parcelas> parcela = new ArrayList<Parcelas>();
        Parcelas parcelas = null;
        String sql = "SELECT * FROM dbo.Parcelas "
                + "   WHERE ID_Movimentacao = ?  "
                + "     AND ID_Parcela      = ?  ";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, idMov);
            stmt.setInt(2, idParc);


            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                parcelas = new Parcelas();
                parcelas.setNumeroParcela(rs.getInt("Numero_Parcela"));
                parcelas.setValorParcela(rs.getDouble("Valor_Parcela"));
                parcela.add(parcelas);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parcela;
    }

    public void excluir(int idMov, int idParc) {
        String sql = " DELETE FROM dbo.Parcelas "
                + "    WHERE ID_Movimentacao = ?"
                + "      AND Numero_Parcela  = ? ";
        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idMov);
            stmt.setInt(2, idParc);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(Parcelas parcelas) {
        String sql = "UPDATE Parcelas "
                + "   SET Valor_Parcela   = ?, "
                + "       Data_Pagamento  = ?, "
                + "       Data_Vencimento = ? "
                + "   WHERE ID_Movimentacao = ? AND"
                + "         Numero_Parcela  = ? ";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setDouble(1, parcelas.getValorParcela());
            stmt.setDate(2, parcelas.getDataPagamento());
            stmt.setDate(3, parcelas.getDataVencimento());
            stmt.setInt(4, parcelas.getIdMovimentacao());
            stmt.setInt(5, parcelas.getNumeroParcela());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Parcelas listarDataVencimento(Date data) {
        Parcelas parcelas = new Parcelas();
        String sql = "Select * from Parcelas where Data_Vencimento = ?";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDate(1, data);

            ResultSet rs = stmt.executeQuery();

            rs.next();


            parcelas.setDataVencimento(rs.getDate("Data_Vencimento"));
            parcelas.setDataPagamento(rs.getDate("Data_Pagamento"));
            parcelas.setParcela_Entrada(rs.getString("Parcela_Entrada"));
            parcelas.setQtdeParcelas(rs.getInt("Numero_Parcela"));
            parcelas.setValorParcela(rs.getDouble("Valor_parcela"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parcelas;
    }

    public Parcelas listarDataPagamento(Date data) {
        Parcelas parcelas = new Parcelas();
        String sql = "Select * from Parcelas where Data_pagamento = ?";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDate(1, data);

            ResultSet rs = stmt.executeQuery();

            rs.next();


            parcelas.setDataVencimento(rs.getDate("Data_Vencimento"));
            parcelas.setDataPagamento(rs.getDate("Data_Pagamento"));
            parcelas.setParcela_Entrada(rs.getString("Parcela_Entrada"));
            parcelas.setQtdeParcelas(rs.getInt("Numero_Parcela"));
            parcelas.setValorParcela(rs.getDouble("Valor_parcela"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parcelas;
    }

    public Parcelas listarParcelaEntrada(Double parcelaEntrada) {
        Parcelas parcelas = new Parcelas();
        String sql = "Select * from Parcelas where Parcela_Entrada = ?";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, parcelaEntrada);

            ResultSet rs = stmt.executeQuery();

            rs.next();

            parcelas.setDataVencimento(rs.getDate("Data_Vencimento"));
            parcelas.setDataPagamento(rs.getDate("Data_Pagamento"));
            parcelas.setParcela_Entrada(rs.getString("Parcela_Entrada"));
            parcelas.setQtdeParcelas(rs.getInt("Numero_Parcela"));
            parcelas.setValorParcela(rs.getDouble("Valor_parcela"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parcelas;
    }

    public Parcelas listarQtdParcela(int numeroParcela) {
        Parcelas parcelas = new Parcelas();
        String sql = "Select * from Parcelas where Numero_Parcela = ?";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, numeroParcela);

            ResultSet rs = stmt.executeQuery();

            rs.next();

            parcelas.setDataVencimento(rs.getDate("Data_Vencimento"));
            parcelas.setDataPagamento(rs.getDate("Data_Pagamento"));
            parcelas.setParcela_Entrada(rs.getString("Parcela_Entrada"));
            parcelas.setQtdeParcelas(rs.getInt("Numero_Parcela"));
            parcelas.setValorParcela(rs.getDouble("Valor_parcela"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parcelas;
    }

    public Parcelas listarValorParcela(double valorParcela) {
        Parcelas parcelas = new Parcelas();
        String sql = "Select * from Parcelas where Valor_Parcela = ?";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, valorParcela);

            ResultSet rs = stmt.executeQuery();

            rs.next();


            parcelas.setDataVencimento(rs.getDate("Data_Vencimento"));
            parcelas.setDataPagamento(rs.getDate("Data_Pagamento"));
            parcelas.setParcela_Entrada(rs.getString("Parcela_Entrada"));
            parcelas.setQtdeParcelas(rs.getInt("Numero_Parcela"));
            parcelas.setValorParcela(rs.getDouble("Valor_parcela"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parcelas;
    }

    public Parcelas listarParcelas(int idParcela, int idMovimentacao) {
        Parcelas parcelas = new Parcelas();
        String sql = "SELECT * FROM dbo.Parcelas"
                + "   WHERE ID_Movimentacao = ?"
                + "     AND Numero_Parcela = ?";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idMovimentacao);
            stmt.setInt(2, idParcela);

            ResultSet rs = stmt.executeQuery();

            rs.next();

            parcelas.setDataVencimento(rs.getDate("Data_Vencimento"));
            parcelas.setDataPagamento(rs.getDate("Data_Pagamento"));
            parcelas.setAtualizado(rs.getString("Atualizado"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parcelas;
    }
}
