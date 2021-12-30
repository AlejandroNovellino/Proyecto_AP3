/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.helpers;

import java.awt.Dimension;
import java.awt.Toolkit;
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
}
