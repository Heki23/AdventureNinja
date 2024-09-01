package com.example.adventureninja;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class Spieler {

    protected int posx = 200;  // x-Position als int
    protected int posy = 200;  // y-Position als int
    protected FrameLayout frameLayoutSpieler;
    protected double gravity;
    private Context context;

    // Getter-Methode für posx
    public int getPosx() {
        return posx;
    }

    // Setter-Methode für posx
    public void setPosx(int posx) {
        this.posx = posx;
    }

    // Getter-Methode für posy
    public int getPosy() {
        return posy;
    }

    // Setter-Methode für posy
    public void setPosy(int posy) {
        this.posy = posy;
    }

    // Getter-Methode für gravity
    public double getGravity() {
        return gravity;
    }

    // Getter-Methode für frameLayoutSpieler
    public FrameLayout getframeLayoutSpieler() {
        return frameLayoutSpieler;
    }

    // Konstruktor
    public Spieler(Context context) {
        this.context = context;

        // FrameLayout und ImageView für den Spieler erstellen
        frameLayoutSpieler = new FrameLayout(context);
        ImageView spielerImage = new ImageView(context);
        spielerImage.setImageResource(R.drawable.player); // Bild des Spielers aus den Ressourcen

        // Layout-Parameter für das ImageView festlegen
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(200, 200);
        params.leftMargin = posx;
        params.topMargin = posy;
        spielerImage.setLayoutParams(params);

        // ImageView dem FrameLayout hinzufügen
        frameLayoutSpieler.addView(spielerImage);
    }

    // Methode zum Aktualisieren der Position des Spielers
    public void updatePosition(int deltaX, int deltaY) {
        posx += deltaX;
        posy += deltaY;

        // Layout-Parameter aktualisieren
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) frameLayoutSpieler.getLayoutParams();
        params.leftMargin = posx;
        params.topMargin = posy;

        // Aktualisierte Layout-Parameter auf das FrameLayout anwenden
        frameLayoutSpieler.setLayoutParams(params);
    }
}
