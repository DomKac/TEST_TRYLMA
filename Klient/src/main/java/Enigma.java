import java.awt.*;

public class Enigma {

    public String koduj_kolor(Color kolor){

        if(kolor == Color.BLUE){
            return "B";
        }
        else if(kolor == Color.RED){
            return "R";
        }
        else if(kolor == Color.ORANGE){
            return "O";
        }
        else if(kolor == Color.YELLOW){
            return "Y";
        }
        else if(kolor == Color.PINK){
            return "P";
        }
        else {
            return "G";
        }
    }

    public Color odkoduj_kolor(String kolor){

        if(kolor.equals("B")){
            return Color.BLUE;
        }
        else if(kolor.equals("R")){
            return Color.RED;
        }
        else if(kolor.equals("O")){
            return Color.ORANGE;
        }
        else if(kolor.equals("Y")){
            return Color.YELLOW;
        }
        else if(kolor.equals("P")){
            return Color.PINK;
        }
        else {
            return Color.GREEN;
        }
    }

    public void koloruj(String response, Ramka frame){

        // MOVEXX,XX,YY,XX,YY,K,A
        String x1 = "";
        String y1 = "";
        String x2 = "";
        String y2 = "";
        String kolor = "";
        int n=4;

        while(response.charAt(n)!=','){
            x1 = x1 + response.charAt(n);
            n++;
        }
        n++;

        while(response.charAt(n)!=','){
            y1 = y1 + response.charAt(n);
            n++;
        }
        n++;

        while(response.charAt(n)!=','){
            x2 = x2 + response.charAt(n);
            n++;
        }
        n++;

        while(response.charAt(n)!=','){
            y2 = y2 + response.charAt(n);
            n++;
        }
        n++;

        kolor = kolor + response.charAt(n);

        frame.panelGry.messMoveSer(Integer.parseInt(x1),Integer.parseInt(y1),Integer.parseInt(x2),Integer.parseInt(y2),odkoduj_kolor(kolor));
    }


    public Color kolorgracza(char numerek, int ilosc){
        switch (ilosc){
            case 2: {
                if(numerek == '1'){
                    return Color.PINK;
                }
                else{
                    return Color.BLUE;
                }
            }
            case 3: {
                if(numerek == '1'){
                    return Color.PINK;
                }
                else if (numerek == '2'){
                    return Color.YELLOW;
                }
                else{
                    return Color.RED;
                }
            }
            case 4: {
                if(numerek == '1'){
                    return Color.ORANGE;
                }
                else if (numerek == '2'){
                    return Color.YELLOW;
                }
                else if (numerek == '3'){
                    return Color.RED;
                }
                else {
                    return Color.GREEN;
                }
            }
            case 6: {
                if(numerek == '1'){
                    return Color.PINK;
                }
                else if (numerek == '2'){
                    return Color.ORANGE;
                }
                else if (numerek == '3'){
                    return Color.YELLOW;
                }
                else if (numerek == '4'){
                    return Color.BLUE;
                }
                else if (numerek == '5'){
                    return Color.RED;
                }
                else {
                    return Color.GREEN;
                }
            }
            default:
                return Color.BLACK;
        }
    }

    public Color przekazture(char numerek, int ilosc){
        switch (ilosc){
            case 2: {
                if(numerek == '1'){
                    return Color.BLUE;
                }
                else{
                    return Color.PINK;
                }
            }
            case 3: {
                if(numerek == '1'){
                    return Color.RED;
                }
                else if (numerek == '2'){
                    return Color.PINK;
                }
                else{
                    return Color.YELLOW;
                }
            }
            case 4: {
                if(numerek == '1'){
                    return Color.GREEN;
                }
                else if (numerek == '2'){
                    return Color.ORANGE;
                }
                else if (numerek == '3'){
                    return Color.YELLOW;
                }
                else {
                    return Color.RED;
                }
            }
            case 6: {
                if(numerek == '1'){
                    return Color.GREEN;
                }
                else if (numerek == '2'){
                    return Color.PINK;
                }
                else if (numerek == '3'){
                    return Color.ORANGE;
                }
                else if (numerek == '4'){
                    return Color.YELLOW;
                }
                else if (numerek == '5'){
                    return Color.BLUE;
                }
                else {
                    return Color.RED;
                }
            }
            default:
                return Color.BLACK;
        }
    }

    public char idgracza(char numerek, int ilosc){
        switch (ilosc){
            case 2: {
                if(numerek == '1'){
                    return 'P';
                }
                else{
                    return 'B';
                }
            }
            case 3: {
                if(numerek == '1'){
                    return 'P';
                }
                else if (numerek == '2'){
                    return 'Y';
                }
                else{
                    return 'R';
                }
            }
            case 4: {
                if(numerek == '1'){
                    return 'O';
                }
                else if (numerek == '2'){
                    return 'Y';
                }
                else if (numerek == '3'){
                    return 'R';
                }
                else {
                    return 'G';
                }
            }
            case 6: {
                if(numerek == '1'){
                    return 'P';
                }
                else if (numerek == '2'){
                    return 'O';
                }
                else if (numerek == '3'){
                    return 'Y';
                }
                else if (numerek == '4'){
                    return 'B';
                }
                else if (numerek == '5'){
                    return 'R';
                }
                else {
                    return 'G';
                }
            }
            default:
                return 'x';
        }
    }


}

