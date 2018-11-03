import basis.*;

public class MalprogrammWindow {
    Fenster window;
    Leinwand toolsLeinwand;
    Leinwand erklaerungDickeLeinwand;
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
    Knopf buttonDeleteEverything;
    BeschriftungsFeld erklaerungDicke;

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


        buttonPen = new Knopf("Draw", 9, 10, 76, 20);
        buttonEraser = new Knopf("Erase", 9, 40, 76, 20);
        buttonDeleteEverything = new Knopf("Delete", 9, 240, 76, 20);

        toolsLeinwand = new Leinwand(0, 0, 95, 265);
        toolsLeinwand.setzeHintergrundFarbe(Farbe.rgb(240, 240, 240));

        erklaerungDicke = new BeschriftungsFeld("Um die Dicke der Werkzeuge zu ändern einfach die rechte Maustaste drücken und horizontal bewegen.", 5, 755, 900, 15);
        erklaerungDickeLeinwand = new Leinwand(0, 750, 655, 50);
        erklaerungDickeLeinwand.setzeHintergrundFarbe(Farbe.rgb(240, 240, 240));
    }

    public void run() {
        while (window.istSichtbar()) {
            Hilfe.kurzePause();

            currentTool.setMousePressed(mouse.istGedrueckt());
            currentTool.setMousePosition(mouse.hPosition(), mouse.vPosition());
            checkToolButtons();
            size();

            // Wenn eine andere Farbe gewählt wurde, wird diese auf das Tool übertragen
            if(wahlBoxGruppe.wurdeGeaendert()) {
                checkwahlBoxGruppe();
            }
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
            checkwahlBoxGruppe(); // die Farbe muss vom Tool übernommen werden
        } else if (buttonEraser.wurdeGedrueckt()) {
            currentTool = new EraserTool();
        } else if (buttonDeleteEverything.wurdeGedrueckt()) {
            window.loescheAlles();
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
