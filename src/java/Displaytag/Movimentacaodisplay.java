/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Displaytag;

import java.util.ArrayList;
import Displaytag.Movimentacaodisplay;
import DAO.MovimentacaoViewDAO;
import Model.MovimentacaoView;

/**
 *
 * @author Renato Oda
 */
import java.util.Collections;
import java.util.List;

public class Movimentacaodisplay extends ArrayList {
    
    private static final long serialVersionUID = 899149338534L;
    
    public Movimentacaodisplay(int idUsuario) {
        super();
        
        MovimentacaoViewDAO movimentacao = new MovimentacaoViewDAO();
        List<MovimentacaoView> movimentacaoV = movimentacao.listarMovimentacao(idUsuario);
        
        for (MovimentacaoView Item : movimentacaoV) {
            MovimentacaoView mov = new MovimentacaoView();
            
            mov.setIdMovimentacao(Item.getIdMovimentacao());            
            mov.setDescricaoMovimentacao(Item.getDescricaoMovimentacao());            
            mov.setDescricaoForma(Item.getDescricaoForma());
            mov.setDataMovimentacao(Item.getDataMovimentacao());
            mov.setValorTotal(Item.getValorTotal());
            
            add(mov);
        }        
        
        Collections.sort(this);        
        
    }
}
