/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Biblioteca {

    public String dataStr(Date data) {
        try {
            SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");
            return sdf3.format(data);
        } catch (Exception ex) {
            new RuntimeException("Erro na data");
            return "";
        }
    }
}
