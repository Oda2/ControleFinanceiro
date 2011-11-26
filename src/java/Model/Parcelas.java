package Model;

import java.sql.Date;

public class Parcelas {

    private int qtdeParcelas;
    private double valorParcela;
    private double parcelaEntrada;
    private Date dataVencimento;
    private Date dataPagamento;
    private int numeroParcela;
    private int idMovimentacao;

    public int getIdMovimetacao() {
        return idMovimentacao;
    }

    public void setIdMovimetacao(int numeroParcela) {
        this.idMovimentacao = idMovimentacao;
    }

    public int getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(int numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public int getQtdeParcelas() {
        return qtdeParcelas;
    }

    public void setQtdeParcelas(int qtdeParcelas) {
        this.qtdeParcelas = qtdeParcelas;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public double getParcelaEntrada() {
        return parcelaEntrada;
    }

    public void setParcela_Entrada(double parcelaEntrada) {
        this.parcelaEntrada = parcelaEntrada;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
