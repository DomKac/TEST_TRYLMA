import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.io.PrintWriter;
import java.net.Socket;

public class Klient {

    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    public Ramka frame;
    public boolean tura = false;


/////////////////////////////////////////////////////
    public ActionListener wyb_pionek = new ActionListener() {

        public boolean wybrano_piona = true; // pomaga określić czy trzeba wybrać pionka czy ruszyć pionka
        // true -> kliknięcie pola_planszy wybiera pionka którego chcemy ruszyć
        // false -> kilknięcie pola_planszy stawia wcześniej wybranego piona na wybrane miejsce

        Color kolor_piona;
        int currentX;
        int currentY;
        int previousX;
        int previousY;
        final Enigma enigma = new Enigma();


        @Override
        public void actionPerformed(ActionEvent e) {

            String coordinates = ((JComponent) e.getSource()).getName();
            System.out.println(coordinates);
            currentX = frame.panelGry.get_current_X(coordinates);
            currentY = frame.panelGry.get_current_Y(coordinates);


            if(wybrano_piona){


                if(frame.panelGry.pola_planszy[currentX][currentY].getBackground() != Color.WHITE){

                    kolor_piona = frame.panelGry.pola_planszy[currentX][currentY].getBackground();
                    frame.panelGry.check_ALL(currentX, currentY);
                    previousX = currentX;
                    previousY = currentY;

                    wybrano_piona = false;

                    System.out.println();
                }
                else{
                    System.out.println("Wybierz kolorowe pole ");

                }
            }
            else{
                if(frame.panelGry.pola_planszy[currentX][currentY].getBackground() == Color.GRAY) {
                    System.out.println("Teraz nalezy wybrac gdzie sie ruszyc");
                    frame.panelGry.clear_grey();

                    if(tura){
                        out.println("MOVE" + previousX + "," + previousY + "," + currentX + "," + currentY + "," + enigma.koduj_kolor(kolor_piona));
                        tura = false;
                    }


                    if(frame.panelGry.check_ENDGAME()){
                        System.out.println("KONIEC!");
                        System.exit(0);
                    }
                    wybrano_piona = true;
                }
                else if (previousX == currentX && previousY == currentY){
                    frame.panelGry.clear_grey();
                    wybrano_piona = true;
                }
                else{
                    System.out.println("zle pole debilu");
                }
            }
        }
    };


    //////////////////////////////////////////
    public Klient(String serverAddress) throws Exception {

        socket = new Socket(serverAddress, 58901);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);

    }

    public void play() throws Exception {
        try {
            var response = in.nextLine();
            var num = response.charAt(9);
            Enigma enigma2 = new Enigma();
            var ilosc = Character.getNumericValue(response.charAt(0));
            System.out.println("Witaj graczu o numerze: " + num + " " + enigma2.idgracza(num, ilosc));
            System.out.println("ilość graczy wynosi: " + ilosc);

            frame = new Ramka(ilosc, num, enigma2.kolorgracza(num, ilosc));
            frame.panelGry.dodaj_wlasciwosci_guzikom(wyb_pionek);
            frame.setVisible(true);

            //if(ilosc == 4){
            //    frame.panelGry.niegrywki[1][1].setBackground(Color.ORANGE);
            //}
            //else{
            //    frame.panelGry.niegrywki[1][1].setBackground(Color.PINK);
            //}



            while (in.hasNextLine()) {
                response = in.nextLine();
                System.out.println("startpetli");

                if(response.startsWith("MESSAGE")){
                    System.out.println(response);
                    if(response.charAt(15) == num){
                        tura = true;
                    }
                }

                else if(response.startsWith("TURN")){
                    System.out.println("teraz jest tura gracza o numerze: " + response.charAt(4));
                    if(response.charAt(4) == num){
                        tura = true;
                    }

                }
                else if(response.startsWith("MOVE")){ //serwer wysłał wiadomość o ruchu jakiegoś gracza
                    System.out.println(response); //musimy skopiować ten ruch u nas
                    //frame.niegrywki[1][1].setBackground(enigma2.przekazture(num, ilosc));
                    enigma2.koloruj(response, frame);

                }

            }
            out.println("QUIT");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("koniec");
            //socket.close();
            //frame.dispose();
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Pass the server IP as the sole command line argument");
            return;
        }
        Klient client = new Klient(args[0]);
        client.play();
    }
}
