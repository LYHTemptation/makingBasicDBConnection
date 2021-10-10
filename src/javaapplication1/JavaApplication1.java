
package javaapplication1;

import javax.swing.JFrame;

public class JavaApplication1 {

    public static void main(String[] args) throws Exception {
        JFrame.setDefaultLookAndFeelDecorated(true);
        StockUi ui = new StockUi();
        ui.setVisible(true);
    }
    
}
