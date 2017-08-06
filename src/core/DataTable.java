
package core;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;


public class DataTable implements TableCellRenderer {

    DefaultTableCellRenderer renderer,centerRenderer;

    public DataTable(JTable table) 
    {
        renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        //renderer.setBackground(Color.YELLOW);
        //renderer.setFont(new Font("Times New Roman", Font.BOLD, 22));
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.LEFT );
       // centerRenderer.setFont(new Font("Times New Roman", Font.BOLD, 22));
        table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        if(table.getColumnCount()==2)
        {
            table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        }
        
       
    }

    @Override
    public Component getTableCellRendererComponent(
        JTable table, Object value, boolean isSelected,
        boolean hasFocus, int row, int col) {
        return renderer.getTableCellRendererComponent(
            table, value, isSelected, hasFocus, row, col);
    }
    
   
    
    public static void makeMyFilesTable(JTable table)
    {
        
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new DataTable(table));
     
        table.setRowHeight(24);
        table.getColumnModel().getColumn(0).setMinWidth(300);
        table.getColumnModel().getColumn(1).setMinWidth(500);
      
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }
    
    public static void makeManageFileTable(JTable table)
    {
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new DataTable(table));
        table.setRowHeight(24);
        table.getColumnModel().getColumn(0).setMinWidth(250);
        table.getColumnModel().getColumn(1).setMinWidth(400);
        table.getColumnModel().getColumn(2).setMinWidth(150);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }
    
    
    
}
