package Model;

import java.sql.Date;

public class Usuario {

    private String nomeUsu;
    private String sexoUsu;
    private Date dataNascimento;
    private double salarioUsu;
    private String senhaUsu;
    private String login;
    private String email;
    private int ID;

    public int getId() {
        return ID;
    }

    public void setId(int Id) {
        this.ID = Id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenhaUsu() {
        return senhaUsu;
    }

    public void setSenhaUsu(String senhaUsu) {
        this.senhaUsu = senhaUsu;
    }

    public String getNomeUsu() {
        return nomeUsu;
    }

    public void setNomeUsu(String nomeUsu) {
        this.nomeUsu = nomeUsu;
    }

    public String getSexoUsu() {
        return sexoUsu;
    }

    public void setSexoUsu(String sexoUsu) {
        this.sexoUsu = sexoUsu;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Double getSalarioUsu() {
        return salarioUsu;
    }

    public void setSalarioUSu(double salarioUsu) {
        this.salarioUsu = salarioUsu;
    }
}
