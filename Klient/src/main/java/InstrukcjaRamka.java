import javax.swing.*;
import java.awt.*;

class InstrukcjaRamka extends JFrame {
    InstrukcjaRamka(){
        super("Instrukcja");
        JTextField text = new JTextField();
        setBounds(200,200,640,560);
        text.setEditable(false);
        text.setBackground(Color.WHITE);
        this.add(text);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
