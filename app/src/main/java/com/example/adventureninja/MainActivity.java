package com.example.adventureninja;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

     private FrameLayout frameLayoutContainer;  // Deklariere die Variable
    private Spieler spieler;
    private Timer timer;
    private TimerTask task;
    private ValueAnimator animator;
    private boolean isAnimating = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Finde den Buttons und Img
        Button linksBtn = findViewById(R.id.Links_Btn);
        Button rechtsBtn = findViewById(R.id.Rechts_Btn);

        frameLayoutContainer = findViewById(R.id.frameLayoutContainer);// Initialisieren FrameLayoutContainer, ohne dass die App abstürzt
        spieler = new Spieler(this); //Spieler Instanzieren
        frameLayoutContainer.addView(spieler.getframeLayoutSpieler()); //Spieler in Container einfügen mit frameLayoutSpieler

        //linksBtn //ACTION_DOWN, ACTION_UP
        linksBtn.setOnTouchListener((view, event) -> onTouchListener(view, event, -15));
        rechtsBtn.setOnTouchListener((view, event) -> onTouchListener(view, event, 15));


        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                // Diese Methode wird alle 1000 ms (1 Sekunde) aufgerufen
                runOnUiThread(new Runnable() { // Da UI-Updates auf dem UI-Thread erfolgen müssen
                    @Override
                    public void run() {
                        spieler.updatePosition(0, 1);
                    }
                });

//                ImageView groud = findViewById(R.id.GroudImg);
//                int groudY = groud.getTop();
//                if (spieler.getPosy() + spieler.frameLayoutSpieler.getHeight() >= groudY) {
//                    // Stoppe den Timer
//                    timer.cancel();
//
//                    // Setze die Position des Spielers genau auf den Boden
//                    spieler.setPosy(groudY - spieler.frameLayoutSpieler.getHeight());
//                }
            }
        };

        // Starte den Timer sofort und wiederhole alle 10ms
        timer.schedule(task, 0, 10);

    }


    public void animation(int sposx){
        // Initialisiere den ValueAnimator für die Bewegung
        animator = ValueAnimator.ofFloat(0, sposx);
        animator.setDuration(50); // Dauer in Millisekunden
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(animation -> {
            if (isAnimating) {
                float deltaX = (float) animation.getAnimatedValue();
                spieler.updatePosition((int) deltaX, 0);  // Bewege den Spieler
            }
        });
    }

    private boolean onTouchListener(View v, MotionEvent event, int sposx) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!isAnimating) {
                    isAnimating = true;
                    animation(sposx);
                    animator.start();  // Starte die Animation
                }
                return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (isAnimating) {
                    isAnimating = false;
                    animator.cancel();  // Stoppe die Animation
                }
                return true;
        }
        return false;
    }

    //region Landscape Mode
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
    //endregion
}