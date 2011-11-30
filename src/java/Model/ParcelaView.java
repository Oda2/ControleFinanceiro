/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 *
 * @author Renato Oda
 */
public class ParcelaView extends Object implements Comparable, Serializable {

    private int idParcela;
    private int numeroParcela;
    private double valorParcela;
    private String parcEntrada;
    private Date dataVencimento;
    private Date dataPagamento;
    private String atualizado;
    private int qtde;

    public int getQtde() {
        return qtde;
    }
    
    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public String getAtualizado() {
        return atualizado;
    }

    public void setAtualizado(String atualizado) {
        this.atualizado = atualizado;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public int getIdParcela() {
        return idParcela;
    }

    public void setIdParcela(int idParcela) {
        this.idParcela = idParcela;
    }

    public int getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(int numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public String getParcEntrada() {
        return parcEntrada;
    }

    public void setParcEntrada(String parcEntrada) {
        this.parcEntrada = parcEntrada;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    @Override
    public int compareTo(Object object) {
        ParcelaView parcView = (ParcelaView) object;
        return new CompareToBuilder().append(this.idParcela, parcView.idParcela)
                                     .append(this.atualizado, parcView.atualizado)
                                     .append(this.dataPagamento, parcView.dataPagamento)
                                     .append(this.dataVencimento, parcView.dataVencimento)
                                     .append(this.numeroParcela, parcView.numeroParcela)
                                     .append(this.parcEntrada, parcView.parcEntrada)
                                     .append(this.valorParcela, parcView.parcEntrada).toComparison();
    }

    
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE) //
                .append("idParcela", this.idParcela)
                .append("atualizado", this.atualizado)
                .append("dataPagamento", this.dataPagamento)
                .append("dataVencimento", this.dataVencimento)
                .append("numeroParcela", this.numeroParcela)
                .append("parcEntrada", this.parcEntrada)
                .append("valorParcela", this.valorParcela).toString();
    }
}
