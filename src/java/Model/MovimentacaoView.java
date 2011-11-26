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
public class MovimentacaoView extends Object implements Comparable, Serializable {

    private int idMovimentacao;
    private String descricaoMovimentacao;
    private double valorTotal;
    private String descricaoForma;
    private Date dataMovimentacao;
    private int parcela;
    private String idFormaMovimentacao;
    private int qtde;
    private double valorEntrada;

    public double getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(double valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public String getIdFormaMovimentacao() {
        return idFormaMovimentacao;
    }

    public void setIdFormaMovimentacao(String idFormaMovimentacao) {
        this.idFormaMovimentacao = idFormaMovimentacao;
    }
    
    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }    

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public String getDescricaoForma() {
        return descricaoForma;
    }

    public void setDescricaoForma(String descricaoForma) {
        this.descricaoForma = descricaoForma;
    }

    public String getDescricaoMovimentacao() {
        return descricaoMovimentacao;
    }

    public void setDescricaoMovimentacao(String descricaoMovimentacao) {
        this.descricaoMovimentacao = descricaoMovimentacao;
    }

    public int getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public int compareTo(Object object) {
        MovimentacaoView movimentacaoView = (MovimentacaoView) object;
        return new CompareToBuilder().append(this.descricaoMovimentacao, movimentacaoView.descricaoMovimentacao).append(this.idMovimentacao, movimentacaoView.idMovimentacao).append(this.descricaoForma, movimentacaoView.descricaoForma).append(this.dataMovimentacao, movimentacaoView.dataMovimentacao).append(this.valorTotal, movimentacaoView.valorTotal).toComparison();
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE) //
                .append("descricaoMovimentacao", this.descricaoMovimentacao) //$NON-NLS-1$
                .append("idMovimentacao", this.idMovimentacao) //$NON-NLS-1$
                .append("descricaoForma", this.descricaoForma) //$NON-NLS-1$
                .append("dataMovimentacao", this.dataMovimentacao) //$NON-NLS-1$
                .append("valorTotal", this.valorTotal) //$NON-NLS-1$
                .toString();
    }
}
