package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Movimentacao;
import Model.Parcelas;
import Util.Conecta;

public class MovimentacaoDAO {

    Parcelas parcelas = new Parcelas();

    public void execute(Movimentacao movimentacao, String idMov, int idUsu, int qtdParc, double valorEnt) {
        String sql = "EXECUTE dbo.Registro_Movimento @Data_Movimentacao = ? , "
                + "@ID_Forma_Movimentacao = ?, "
                + "@Descricao = ? ,"
                + "@ID_Usuario = ? ,"
                + "@Qtde_Parcela = ?,"
                + "@Valor_Parcela_Entrada = ?,"
                + "@Valor_Total =?,"
                + "@Parcela_Entrada = ?";
        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setDate(1, movimentacao.getDataMov());
            stmt.setString(2, idMov);
            stmt.setString(3, movimentacao.getDescricao());
            stmt.setInt(4, idUsu);
            stmt.setInt(5, qtdParc);
            stmt.setDouble(6, valorEnt);
            stmt.setDouble(7, movimentacao.getValotTotal());
            stmt.setString(8, "S");

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void inserir(Movimentacao movimentacao) {
        String sql = "INSERT INTO dbo.Movimentacao ( Data_Movimentacao,"
                + "                                  Valor_total, "
                + "                                  Descricao) "
                + "   VALUES (?,?,?)";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDate(1, movimentacao.getDataMov());
            stmt.setDouble(2, movimentacao.getValotTotal());
            stmt.setString(3, movimentacao.getDescricao());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void alterar(Movimentacao movimentacao, String idFormaMovimentacao) {
        String sql = "UPDATE dbo.Movimentacao "
                + "   SET Valor_Total = ?,"
                + "   Descricao = ?, "
                + "   ID_Forma_Movimentacao = ?, "
                + "   Data_Movimentacao = ? "
                + "   WHERE ID_Movimentacao = ?";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setDouble(1, movimentacao.getValotTotal());
            stmt.setString(2, movimentacao.getDescricao());
            stmt.setString(3, idFormaMovimentacao);
            stmt.setDate(4, movimentacao.getDataMov());
            stmt.setInt(5, movimentacao.getIdMov());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idMov) {
        String sql = "DELETE FROM dbo.Movimentacao "
                + "   WHERE ID_Movimentacao = ?";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idMov);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Movimentacao> listarTodos() {
        ArrayList<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
        String sql = "SELECT * FROM dbo.Movimentacao";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Movimentacao movimentacao = new Movimentacao();

                movimentacao.setDataMov(rs.getDate("Data_Movimentacao"));
                movimentacao.setDescricao(rs.getString("Descricao"));
                movimentacao.setValorTotal(rs.getDouble("Valor_Total"));
                movimentacao.setIdMov(rs.getInt("ID_Movimentacao"));

                movimentacoes.add(movimentacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movimentacoes;
    }

}
