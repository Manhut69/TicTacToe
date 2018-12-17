package com.example.clint.tictactoe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.clint.tictactoe.GameState.IN_PROGRESS;
import static com.example.clint.tictactoe.TileState.CIRCLE;
import static com.example.clint.tictactoe.TileState.CROSS;

public class MainActivity extends AppCompatActivity {

    Game game;
    List<Integer> buttons;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textBox = findViewById(R.id.textView);
        textBox.setText("Turn: Player 1");
        buttons = new ArrayList<>(Arrays.asList(R.id.topleft, R.id.topmid, R.id.topright, R.id.midleft, R.id.midmid, R.id.midright, R.id.botleft, R.id.botmid, R.id.botright));
        if(savedInstanceState!= null) {
            game = (Game) savedInstanceState.getSerializable("game");
            for(int row = 0; row < 3; row++) {
                for(int column = 0; column < 3; column++) {
                    ImageButton btn = findViewById(buttons.get(row * 3 + column));
                    switch (game.getState(row, column)) {
                        case BLANK:
                            btn.setImageResource(android.R.color.darker_gray);
                            break;
                        case CROSS:
                            btn.setImageResource(R.drawable.naamloos);
                            break;
                        case CIRCLE:
                            btn.setImageResource(R.drawable.surprised);
                            break;
                    }
                }
            }
        }
        else {
            game = new Game();
            for(int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {
                    ImageButton btn = findViewById(buttons.get(row * 3 + column));
                    btn.setImageResource(android.R.color.darker_gray);
                    btn.setBackgroundResource(0);
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("game", game);
            }

    @SuppressLint("SetTextI18n")
    public void titleClicked(View view) {
        Log.d("Gamestate", game.checkWon().toString());
        int id = view.getId();
        int row = -1;
        int column = -1;
        if (game.checkWon() == IN_PROGRESS) {
            switch (id) {
                case (R.id.topleft):
                    row = 0;
                    column = 0;
                    break;
                case (R.id.topmid):
                    row = 0;
                    column = 1;
                    break;
                case (R.id.topright):
                    row = 0;
                    column = 2;
                    break;
                case (R.id.midleft):
                    row = 1;
                    column = 0;
                    break;
                case (R.id.midmid):
                    row = 1;
                    column = 1;
                    break;
                case (R.id.midright):
                    row = 1;
                    column = 2;
                    break;
                case (R.id.botleft):
                    row = 2;
                    column = 0;
                    break;
                case (R.id.botmid):
                    row = 2;
                    column = 1;
                    break;
                case (R.id.botright):
                    row = 2;
                    column = 2;
                    break;
            }

            ImageButton btn = findViewById(id);
            TextView textBox = findViewById(R.id.textView);
            switch (game.choose(row, column)) {
                case CROSS:
                    game.changeState(CROSS, row, column);
                    btn.setImageResource(R.drawable.naamloos);
                    textBox.setText("Turn: Player 2");
                    break;
                case CIRCLE:
                    game.changeState(CIRCLE, row, column);
                    btn.setImageResource(R.drawable.surprised);
                    textBox.setText("Turn: Player 1");
                    break;
                case INVALID:
                    Log.d("Move", "Invalid");
                    break;
            }
        }

        if (game.checkWon() != IN_PROGRESS) {
            TextView textBox = findViewById(R.id.textView);
            switch (game.checkWon()) {
                case PLAYER_ONE:
                    Log.d("Win", "Player 1");
                    textBox.setText("Player 1 wins!");
                    break;
                case PLAYER_TWO:
                    Log.d("Win", "Player 2");
                    textBox.setText("Player 2 wins!");
                    break;
                case DRAW:
                    Log.d("Draw", "Sadface");
                    textBox.setText("Draw! :(");
                    break;
            }
        }
    }

    public void resetClicked(View view) {
        List<Integer> buttons = new ArrayList<>(Arrays.asList(R.id.topleft, R.id.topmid, R.id.topright, R.id.midleft, R.id.midmid, R.id.midright, R.id.botleft, R.id.botmid, R.id.botright));
        game = new Game();
        for (int i = 0; i < 9; i++) {
            ImageButton btn = findViewById(buttons.get(i));
            btn.setImageResource(android.R.color.darker_gray);
        }
    }


}