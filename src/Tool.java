import basis.*;

import java.awt.*;

public class Tool {
    Stift pen;

    /*
    Übergibt die Position der Maus an die Tools
     */
    public void setMousePosition(int x, int y){

    }

    /*
    Übergibt, ob die Maustaste gedrückt wird an die Tools
     */
    public void setMousePressed(boolean mousePressed){

    }

    /*
    Setzt die Farbe des Stiftes
     */
    public void setColor(Color color) {
        pen.setzeFarbe(color);
    }

    /*
    Setzt die Dicke des Stiftes
     */
    public void setSize(int size) {
        pen.setzeLinienBreite(size);
    }

    /*
    Übergibt den Stift/pen
     */
    public Stift getPen() {
        return pen;
    }
}
