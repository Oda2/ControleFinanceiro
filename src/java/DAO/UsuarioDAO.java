package DAO;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import Model.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Criptografia;
import Util.Conecta;

public class UsuarioDAO {

    Criptografia cripta = new Criptografia();
    
      public Usuario recuperaSenha(String email) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {

        Usuario usuario = null;
        String sql = "SELECT * FROM dbo.Usuarios "
                + "   WHERE email = ?";             

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);         

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(); 
                usuario.setNomeUsu("Nome");
               usuario.setSenhaUsu(cripta.decripta(rs.getString("Senha")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return usuario;
    }

    public Usuario verificaUsuario(String login, String senha) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {

        Usuario usuario = null;
        String sql = "SELECT * FROM dbo.Usuarios "
                + "   WHERE Usuario_login = ? AND "
                + "         senha = ?";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, cripta.encripta(senha));

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setLogin(rs.getString("Usuario_Login"));
                usuario.setId(rs.getInt("ID_Usuario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return usuario;
    }

    public Usuario populaUsuario(int id) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {

        Usuario usuario = null;


        String sql = "SELECT * FROM dbo.Usuarios "
                + "   WHERE ID_Usuario = ?";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setNomeUsu(rs.getString("Nome"));
                usuario.setSexoUsu(rs.getString("Sexo"));
                usuario.setSalarioUSu(rs.getDouble("Salario"));
                usuario.setDataNascimento(rs.getDate("Data_Nascimento"));
                usuario.setLogin(rs.getString("usuario_Login"));
                usuario.setSenhaUsu(cripta.decripta(rs.getString("Senha")));
                usuario.setEmail(rs.getString("Email"));
                usuario.setId(rs.getInt("ID_Usuario"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO dbo.Usuarios ( Nome, "
                + "                              Sexo,  "
                + "                              Salario, "
                + "                              Data_Nascimento,"
                + "                              Usuario_Login, "
                + "                              Senha,"
                + "                              Email) "
                + "   VALUES (?,?,?,?,?,?,?)";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getNomeUsu());
            stmt.setString(2, usuario.getSexoUsu());
            stmt.setDouble(3, usuario.getSalarioUsu());
            stmt.setDate(4, usuario.getDataNascimento());
            stmt.setString(5, usuario.getLogin());
            stmt.setString(6, usuario.getSenhaUsu());
            stmt.setString(7, usuario.getEmail());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void alterar(Usuario usuario) {
        String sql = "UPDATE dbo.Usuarios "
                + "   SET Sexo = ? , "
                + "       Salario = ?, "
                + "       Data_Nascimento = ?,"
                + "       Usuario_Login = ?, "
                + "       Senha = ?, "
                + "       Email=? "
                + "   WHERE ID_Usuario =?";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, usuario.getSexoUsu());
            stmt.setDouble(2, usuario.getSalarioUsu());
            stmt.setDate(3, usuario.getDataNascimento());
            stmt.setString(4, usuario.getLogin());
            stmt.setString(5, usuario.getSenhaUsu());
            stmt.setString(6, usuario.getEmail());
            stmt.setInt(7, usuario.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idUsu) {
        String sql = "DELETE FROM dbo.Usuarios "
                + "   WHERE ID_Usuario = ?";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idUsu);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> listarTodos() throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        String sql = "SELECT * FROM dbo.Usuarios";

        try {
            Conecta conn = Conecta.getInstance();
            Connection conexao = conn.getConnection();
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setNomeUsu(rs.getString("Nome"));
                usuario.setSexoUsu(rs.getString("Sexo"));
                usuario.setSalarioUSu(rs.getDouble("Salario"));
                usuario.setDataNascimento(rs.getDate("Data_Nascimento"));
                usuario.setLogin(rs.getString("Usuario_Login"));
                usuario.setSenhaUsu(cripta.decripta(rs.getString("Senha")));
                usuario.setEmail(rs.getString("Email"));
                usuario.setId(rs.getInt("ID_Usuario"));

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}
