/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Displaytag;

import DAO.ParcelasViewDAO;
import Model.ParcelaView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Renato Oda
 */
public class ParcelaDisplayTag extends ArrayList {
    
    private static final long serialVersionUID = 899149338534L;
    
    public ParcelaDisplayTag(int idMovimento) {
        super();
        
        ParcelasViewDAO parcela = new ParcelasViewDAO();
        List<ParcelaView> parcelaV = parcela.listarParcela(idMovimento);
        
        for (ParcelaView Item : parcelaV) {
            ParcelaView parc = new ParcelaView();
            
            parc.setAtualizado(Item.getAtualizado());
            parc.setDataPagamento(Item.getDataPagamento());
            parc.setDataVencimento(Item.getDataPagamento());
            parc.setIdParcela(Item.getIdParcela());
            parc.setNumeroParcela(Item.getNumeroParcela());
            parc.setParcEntrada(Item.getParcEntrada());
            parc.setValorParcela(Item.getValorParcela());
            parc.setQtde(1);
            
            add(parc);
        }        
        
        Collections.sort(this);        
        
    }
    
}
