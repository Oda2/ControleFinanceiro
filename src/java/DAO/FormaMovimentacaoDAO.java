package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.FormaMovimentacao;
import Util.Conecta;

public class FormaMovimentacaoDAO {

    public void inserir(FormaMovimentacao formaMovi) {
        String sql = "INSERT INTO dbo.Forma_Movimentacao( Dercricao,"
                + "                                   Tipo_Pagamento)"
                + "   VALUES (?,?)";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, formaMovi.getDescricao());
            stmt.setString(2, formaMovi.getTipoPagamento());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void alterar(FormaMovimentacao formaMov) {
        String sql = "UPDATE dbo.Forma_Movimentacao "
                + "   SET Descricao = ? , "
                + "WHERE ID_Forma_Movimentacao =?";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, formaMov.getDescricao());
            stmt.setString(2, formaMov.getIdFormaMovimentacao());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(String delIdFormMov) {
        String sql = "DELETE FROM dbo.Forma_Pagamento "
                + "   WHEREe ID_Forma_Movimentacao = ?";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, delIdFormMov);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<FormaMovimentacao> listarTodos() {
        ArrayList<FormaMovimentacao> formaMovs = new ArrayList<FormaMovimentacao>();
        String sql = "SELECT * FROM dbo.Forma_Movimentacao";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                FormaMovimentacao formaMov = new FormaMovimentacao();

                formaMov.setDescricao(rs.getString("Descricao"));
                formaMov.setTipoPagamento(rs.getString("Tipo_Pagamento"));
                formaMov.setIdFormaMovimentacao(rs.getString("ID_Forma_Movimentacao"));

                formaMovs.add(formaMov);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return formaMovs;
    }
}
