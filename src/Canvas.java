import basis.Fenster;
import basis.Leinwand;

public class Canvas {
    Leinwand canvas;

    public Canvas(Fenster fenster) {
        canvas = new Leinwand(100, 100, 700,700, fenster);
    }
}
