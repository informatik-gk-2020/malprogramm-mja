import basis.*;

public class MalprogrammWindow {
    Fenster window;
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


        buttonPen = new Knopf("Draw", 10, 10, 50, 20);
        buttonEraser = new Knopf("Erase", 10, 40, 50, 20);
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
        if (wahlBoxLila.wurdeGeaendert()) {
            currentTool.setColor(Farbe.MAGENTA);
        } else if (wahlBoxBlau.wurdeGeaendert()) {
            currentTool.setColor(Farbe.BLAU);
        } else if (wahlBoxGruen.wurdeGeaendert()) {
            currentTool.setColor(Farbe.GRUEN);
        } else if (wahlBoxGelb.wurdeGeaendert()) {
            currentTool.setColor(Farbe.GELB);
        } else if (wahlBoxOrange.wurdeGeaendert()) {
            currentTool.setColor(Farbe.ORANGE);
        } else if (wahlBoxRot.wurdeGeaendert()) {
            currentTool.setColor(Farbe.ROT);
        } else if (wahlBoxWeiss.wurdeGeaendert()) {
            currentTool.setColor(Farbe.WEISS);
        } else if (wahlBoxSchwarz.wurdeGeaendert()) {
            currentTool.setColor(Farbe.SCHWARZ);
        }
    }

    public void checkToolButtons() {
        if (buttonPen.wurdeGedrueckt()) {
            currentTool = new PenTool();
        } else if (buttonEraser.wurdeGedrueckt()) {
            currentTool = new EraserTool();
            wahlBoxSchwarz.setzeZustand(true); //Ohne würde, wenn man wieder zum Stift zurückwechselt die Wahlbox auf einer anderen Farbe sein, der Stift ist aber schwarz (Will ich noch schöner machen)
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
