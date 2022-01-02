/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.helpers;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JFrame;

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
}
