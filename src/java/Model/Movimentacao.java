package Model;

import java.sql.Date;

public class Movimentacao {

    private int idMov;
    private Date dataMov;
    private String descricao;
    private double valorTotal;

    public double getValotTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getIdMov() {
        return idMov;
    }

    public void setIdMov(int idMov) {
        this.idMov = idMov;
    }

    public Date getDataMov() {
        return dataMov;
    }

    public void setDataMov(Date dataMov) {
        this.dataMov = dataMov;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
