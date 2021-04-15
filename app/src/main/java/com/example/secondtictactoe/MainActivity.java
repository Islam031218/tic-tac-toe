package com.example.secondtictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winnerPosition = {{0,1,2}, {3,4,5,}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {1,4,8}, {2,4,6},};
    private boolean gameActive = true;
    TextView textView;
    Button restart;

    public void dropIn(View view) {
        ImageView imageView = (ImageView) view;


        int tag = Integer.parseInt(view.getTag().toString());
        if (gameState[tag] == 2 && gameActive) {
            gameState[tag] = activePlayer;

            if (activePlayer == 0) {
                imageView.setImageResource(R.drawable.ic_baseline_clear_24);
                imageView.animate().alpha(1f).setDuration(300);
                activePlayer = 1;
            } else {
                imageView.setImageResource(R.drawable.ic_baseline_panorama_fish_eye_24);
                imageView.animate().alpha(1f).setDuration(300);
                activePlayer = 0;
            }

        }
        checkWinner();
    }

    private void checkWinner() {
        for (int[] winnerPosition : winnerPosition) {
            String color = "";
            if (gameState[winnerPosition[0]] == gameState[winnerPosition[1]]
                    && gameState[winnerPosition[1]] == gameState[winnerPosition[2]]
                    && gameState[winnerPosition[0]] != 2) {
                if (activePlayer == 0){
                    color = "blue";
                }else if (activePlayer == 1){
                    color = "red";
                }
                gameActive = false;
                textView.setText("winner "+ color);


            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
        ConstraintLayout parent = findViewById(R.id.constraintLayout);
        restart = (Button) findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < parent.getChildCount(); i++) {
                    ImageView chip = (ImageView) parent.getChildAt(i);
                    Log.i("MyTag", String.valueOf(chip.getId()));
                    chip.setImageDrawable(null);
                    activePlayer = 0;
                    gameActive = true;
                    Arrays.fill(gameState,2);
                    textView.setText("");
                }
            }
        });



    }


}