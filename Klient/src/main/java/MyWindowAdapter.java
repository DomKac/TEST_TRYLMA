import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class MyWindowAdapter extends WindowAdapter {

    /** * Metoda umozliwiajaca zamykanie okna "Przyciskiem X" */
    public void windowClosing(WindowEvent e) { System.exit(0); }
}
