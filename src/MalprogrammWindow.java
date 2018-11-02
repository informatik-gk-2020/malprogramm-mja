import basis.*;

public class MalprogrammWindow {
    Fenster window;
    Leinwand test;
    Maus mouse;
    Tool currentTool;
    WahlBox wahlBoxLila;
    WahlBox wahlBoxBlau;
    WahlBox wahlBoxGruen;
    WahlBox wahlBoxGelb;
    WahlBox wahlBoxOrange;
    WahlBox wahlBoxRot;
    WahlBox wahlBoxWeiss;
    WahlBox wahlBoxSchwarz;
    WahlBoxGruppe wahlBoxGruppe;
    Knopf buttonPen;
    Knopf buttonEraser;

    public MalprogrammWindow() {
        window = new Fenster(900, 900);
        mouse = new Maus();

        currentTool = new PenTool();

        wahlBoxLila = new WahlBox("Lila", 5, 70, 90, 20);
        wahlBoxBlau = new WahlBox("Blau", 5, 90, 90, 20);
        wahlBoxGruen = new WahlBox("Grün", 5, 110, 90, 20);
        wahlBoxGelb = new WahlBox("Gelb", 5, 130, 90, 20);
        wahlBoxOrange = new WahlBox("Orange", 5, 150, 90, 20);
        wahlBoxRot = new WahlBox("Rot", 5, 170, 90, 20);
        wahlBoxWeiss = new WahlBox("Weiss", 5, 190, 90, 20);
        wahlBoxSchwarz = new WahlBox("Schwarz", 5, 210, 90, 20);
        wahlBoxGruppe = new WahlBoxGruppe();
        wahlBoxGruppe.fuegeEin(wahlBoxLila);
        wahlBoxGruppe.fuegeEin(wahlBoxBlau);
        wahlBoxGruppe.fuegeEin(wahlBoxGruen);
        wahlBoxGruppe.fuegeEin(wahlBoxGelb);
        wahlBoxGruppe.fuegeEin(wahlBoxOrange);
        wahlBoxGruppe.fuegeEin(wahlBoxRot);
        wahlBoxGruppe.fuegeEin(wahlBoxWeiss);
        wahlBoxGruppe.fuegeEin(wahlBoxSchwarz);


        buttonPen = new Knopf("Draw", 22, 10, 50, 20);
        buttonEraser = new Knopf("Erase", 22, 40, 50, 20);

        test = new Leinwand(0, 0, 95, 235);
        test.setzeHintergrundFarbe(Farbe.rgb(240, 240, 240));
    }

    public void run() {
        while (window.istSichtbar()) {
            Hilfe.kurzePause();

            currentTool.setMousePressed(mouse.istGedrueckt());
            currentTool.setMousePosition(mouse.hPosition(), mouse.vPosition());
            checkwahlBoxGruppe();
            checkToolButtons();
            size();
        }
    }

    public void checkwahlBoxGruppe() {
        if (wahlBoxLila.istGewaehlt()) {
            currentTool.setColor(Farbe.MAGENTA);
        } else if (wahlBoxBlau.istGewaehlt()) {
            currentTool.setColor(Farbe.BLAU);
        } else if (wahlBoxGruen.istGewaehlt()) {
            currentTool.setColor(Farbe.GRUEN);
        } else if (wahlBoxGelb.istGewaehlt()) {
            currentTool.setColor(Farbe.GELB);
        } else if (wahlBoxOrange.istGewaehlt()) {
            currentTool.setColor(Farbe.ORANGE);
        } else if (wahlBoxRot.istGewaehlt()) {
            currentTool.setColor(Farbe.ROT);
        } else if (wahlBoxWeiss.istGewaehlt()) {
            currentTool.setColor(Farbe.WEISS);
        } else if (wahlBoxSchwarz.istGewaehlt()) {
            currentTool.setColor(Farbe.SCHWARZ);
        }
    }

    public void checkToolButtons() {
        if (buttonPen.wurdeGedrueckt()) {
            currentTool = new PenTool();
        } else if (buttonEraser.wurdeGedrueckt()) {
            currentTool = new EraserTool();
        }
    }

    public void size() {
        int xStart = mouse.hPosition();
        while (mouse.istRechtsGedrueckt()) {
            int xCurrent = mouse.hPosition();
            int size = xCurrent - xStart;
            if (size >= 0) {
                currentTool.setSize(size);
            }
        }
    }
}
