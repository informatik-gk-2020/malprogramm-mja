import basis.*;

import java.awt.*;

public class MalprogrammWindow {
    boolean bildZumNachmalenAufWindow = false; //Beim drücken des "buttonBildZumNachmalen" wird der Wert geändert

    Fenster window;
    Leinwand toolsCanvas;
    Leinwand erklaerungDickeCanvas;
    Leinwand bildZumNachmalenCanvas;
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
    Knopf buttonBildZumNachmalen;
    Knopf buttonDeleteEverything;
    BeschriftungsFeld erklaerungDicke;

    /**
     * Die Vorschauleinwand
     */
    Leinwand previewCanvas;

    /**
     * Ein Stift, der auf der Vorschauleinwand zeichnet
     */
    Stift previewPen;

    public MalprogrammWindow() {
        window = new Fenster(900, 900);

        currentTool = new PenTool(); //Das Tool wird konstruiert (Standarttool: Pen)

        //Erstelle die Wahl Boxen für die Farben
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

        //Erstelle die Knöpfe für die Tools und andere Funktionen
        buttonPen = new Knopf("Draw", 9, 10, 76, 20);
        buttonEraser = new Knopf("Erase", 9, 40, 76, 20);
        buttonBildZumNachmalen = new Knopf("Bild", 9, 240, 76, 20);
        buttonDeleteEverything = new Knopf("Delete", 9, 270, 76, 20);

        // Erstelle die Vorschauleinwand
        previewCanvas = new Leinwand(window);
        previewCanvas.setzePosition(0, 0);
        previewCanvas.setzeTransparenz(true);
        previewCanvas.setzeHintergrundFarbe(new Color(0, 0, 0, 0));
        resizeCanvas();
        previewPen = new Stift(previewCanvas);

        //Erstelle Leinwand für das Bild zum nachmalen und ermögliche dem pen darauf zu malen
        bildZumNachmalenCanvas = new Leinwand(200, 100, 500, 500);
        currentTool.getPen().maleAuf(bildZumNachmalenCanvas);
        currentTool.getPen().maleAuf(window);

        // Erstelle die Maus (hier die Vorschauleinwand als Oberfläche, da sie an oberster Stelle ist)
        mouse = new Maus(previewCanvas);

        // Hintergrund für die Steuerelemente
        toolsCanvas = new Leinwand(0, 0, 95, 295);
        toolsCanvas.setzeHintergrundFarbe(Farbe.rgb(240, 240, 240));

        // Erklärung zur Einstellung der Dicke
        erklaerungDicke = new BeschriftungsFeld("Um die Dicke der Werkzeuge zu ändern einfach die rechte Maustaste drücken und bewegen.", 5, 755, 900, 15);
        erklaerungDickeCanvas = new Leinwand(0, 750, 655, 50);
        erklaerungDickeCanvas.setzeHintergrundFarbe(Farbe.rgb(240, 240, 240));
    }

    public void run() {
        while (window.istSichtbar()) {
            Hilfe.kurzePause(); //Damit die Schleife nicht zu schnell durchläuft

            currentTool.setMousePressed(mouse.istGedrueckt());
            currentTool.setMousePosition(mouse.hPosition(), mouse.vPosition());
            checkToolButtons();
            size();

            // Wenn eine andere Farbe gewählt wurde, wird diese auf das Tool übertragen
            if (wahlBoxGruppe.wurdeGeaendert()) {
                checkwahlBoxGruppe();
            }

            // Größe Leinwand anpassen, wenn die Größe des Fensters geändert wurde
            window.setzeKomponentenLauscher(new KomponentenLauscher() {
                /** Wird aufgerufen, wenn die Göße des Fensters geändert wurde */
                @Override
                public void bearbeiteKomponentenVeraenderung(Komponente komponente) {
                    resizeCanvas();
                }
            });
        }
    }

    /**
     * Passt die Größe der Leinwand an das Fenster an
     */
    private void resizeCanvas() {
        previewCanvas.setzeGroesse(window.breite(), window.hoehe());
    }

    /*
    Überprüft die Wahl Boxen und ändert dann die Farbe des Stiftes
     */
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

    /*
    Überprüft die Knöpfe für die Tools und andere Funktionen
     */
    public void checkToolButtons() {
        if (buttonPen.wurdeGedrueckt()) {
            currentTool = new PenTool();
            checkwahlBoxGruppe(); // die Farbe muss vom Tool übernommen werden
        } else if (buttonEraser.wurdeGedrueckt()) {
            currentTool = new EraserTool();
        } else if (buttonBildZumNachmalen.wurdeGedrueckt()) {
            if (bildZumNachmalenAufWindow) {
                bildZumNachmalenCanvas.loescheAlles();
            } else {
                bildZumNachmalenCanvas.ladeBild("BildZumNachmalen.PNG", true);
            }
            currentTool.getPen().maleAuf(bildZumNachmalenCanvas);
            currentTool.getPen().maleAuf(window);
            bildZumNachmalenAufWindow = !bildZumNachmalenAufWindow;
        } else if (buttonDeleteEverything.wurdeGedrueckt()) {
            window.loescheAlles();
        }
    }

    public void size() {
        if (!mouse.istRechtsGedrueckt()) {
            return; // wenn die rechte Maustaste nicht gedrückt ist, kann die Methode ignoriert werden
        }

        // Die Ausgangsposition
        int xStart = mouse.hPosition();
        int yStart = mouse.vPosition();

        while (mouse.istRechtsGedrueckt()) {
            // Die aktuelle Position
            int x = mouse.hPosition();
            int y = mouse.vPosition();

            // Die Entfernung zum Ausgangspunkt ist die neue Größe
            int size = (int) Math.hypot(x - xStart, y - yStart);
            if (size > 0) {
                currentTool.setSize(size);

                // Zeichne eine Vorschau der Größe und eine Linie zum Ausgangspunkt
                previewCanvas.loescheAlles();
                previewPen.rechteck(x - size / 2, y - size / 2, size, size);
                previewPen.linie(xStart, yStart, x, y);
            }
        }

        // entferne die Vorschau
        previewCanvas.loescheAlles();
    }
}
