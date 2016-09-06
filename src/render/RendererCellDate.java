/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render;

import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author vuongluis
 */
public class RendererCellDate extends DefaultTableCellRenderer{
    public SimpleDateFormat sdf;
    public RendererCellDate(){
        sdf  = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    @Override
    protected void setValue(Object value) {
        super.setValue(sdf.format(value));
    }
    
}
