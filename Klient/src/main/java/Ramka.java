import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.Scanner;

class Ramka extends JFrame {

    PanelGry panelGry;
    JTextField which_player;
    JButton pass;
    /**
     * Tworzenie Głownego okna programu
     */
    Ramka(int liczba_graczy, char id, Color markergracza) throws Exception {
        super("Chinskie Warcaby, Gracz " + id + " ");
        setBounds(200,200,640,560);
        addWindowListener(new MyWindowAdapter());
        setLayout(new BorderLayout());

        panelGry = new PanelGry(liczba_graczy);
        this.add(panelGry,BorderLayout.CENTER);

        JPanel up_panel = new JPanel();
        up_panel.setLayout(new GridLayout(1,2));
        this.add(up_panel, BorderLayout.PAGE_START);
        JPanel down_panel = new JPanel();
        up_panel.setLayout(new GridLayout(1,2));
        this.add(down_panel, BorderLayout.PAGE_END);

        which_player = new JTextField();
        which_player.setEditable(false);
        which_player.setText("Tura gracza: ");
        which_player.setHorizontalAlignment(JTextField.CENTER);
        which_player.setBackground(Color.WHITE);
        up_panel.add(which_player);

        JTextField your_color = new JTextField();
        your_color.setEditable(false);
        your_color.setText("Twój kolor to: ");
        your_color.setHorizontalAlignment(JTextField.CENTER);
        your_color.setBackground(markergracza);
        up_panel.add(your_color);

        ActionListener open_instruction = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                InstrukcjaRamka nowa_instrukcja = new InstrukcjaRamka();
                nowa_instrukcja.setVisible(true);
            }
        };

        JButton instrukcja = new JButton("Instrukcja");
        down_panel.add(instrukcja);
        instrukcja.addActionListener(open_instruction);

        pass = new JButton("PASS");
        down_panel.add(pass);

        setResizable(true);
    }
}

