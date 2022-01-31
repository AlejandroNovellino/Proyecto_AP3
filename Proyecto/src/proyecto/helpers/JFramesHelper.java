/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.helpers;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Enumeration;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Alejandro
 */
public class JFramesHelper {
    public static void setMessage(javax.swing.JPanel panel, javax.swing.JLabel label, boolean visibility, String messageToDisplay) {
        label.setText(messageToDisplay);
        panel.setVisible(visibility);
    }
    
    public static void makeFrameFullSize(JFrame aFrame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        aFrame.setSize(screenSize.width, screenSize.height);
    }
    
    public static void setModalSize(JDialog modal) {
        Dimension SCREEN_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
        int dialogWidth = SCREEN_DIMENSION.width / 4; 
        int dialogHeight = SCREEN_DIMENSION.height / 4; 
        int dialogX = SCREEN_DIMENSION.width / 2 - dialogWidth / 2; 
        int dialogY = SCREEN_DIMENSION.height / 2 - dialogHeight / 2;
        
        modal.setBounds(dialogX, dialogY, dialogWidth, dialogHeight);
        modal.pack();
    }
    
    public static int swingConstantToCenter() {
        return SwingConstants.CENTER;
    }
    
    public static void alignTextJTableCetner(JTable table, int placeToAlign) {
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(placeToAlign);
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(0);
        for(Enumeration<TableColumn> col = table.getColumnModel().getColumns(); col.hasMoreElements();) {
            col.nextElement().setCellRenderer(dtcr);
        }   
    }
    
    public static void setJFrameIcon(JFrame frame) {
        try {
            String filePath = "src\\proyecto\\views\\icons\\icons8-tableau-software-24.png";
            Image icon = Toolkit.getDefaultToolkit().getImage(filePath);  
            frame.setIconImage(icon);  
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void setJDialogIcon(JDialog dialog) {
        try {
            String filePath = "src\\proyecto\\views\\icons\\icons8-tableau-software-24.png";
            Image icon = Toolkit.getDefaultToolkit().getImage(filePath);  
            dialog.setIconImage(icon);  
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
