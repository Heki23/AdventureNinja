package com.example.adventureninja;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // versteckt system bars
        hideSystemUI();

        // Finde den Buttons und Img
        Button LinksBtn = findViewById(R.id.Links_Btn);
        Button RechtsBtn = findViewById(R.id.Rechts_Btn);
        ImageView Player = findViewById(R.id.PlayerImg);

        //OnClickListener
        LinksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){ linksGehen();}
        });
        RechtsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rechtsGehen();
            }
        });
    }
    public void linksGehen(){
        Toast.makeText(this, "Links Button clicked!", Toast.LENGTH_SHORT).show();
    }
    public void rechtsGehen(){
        Toast.makeText(this, "Rechts Button clicked!", Toast.LENGTH_SHORT).show();
    }

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


}