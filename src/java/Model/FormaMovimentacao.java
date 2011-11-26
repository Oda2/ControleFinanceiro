package Model;

public class FormaMovimentacao {

    private String descricao;
    private String tipoPagamento;
    private String idFormaMovimetacao;

    public String getIdFormaMovimentacao() {
        return idFormaMovimetacao;
    }

    public void setIdFormaMovimentacao(String idFormaMovimentacao) {
        this.idFormaMovimetacao = idFormaMovimentacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
}
