package com.example.clint.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.clint.tictactoe.GameState.IN_PROGRESS;
import static com.example.clint.tictactoe.TileState.CIRCLE;
import static com.example.clint.tictactoe.TileState.CROSS;

public class MainActivity extends AppCompatActivity {

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!= null) {
            game = (Game) savedInstanceState.getSerializable("game");
            List<Integer> buttons = new ArrayList<>(Arrays.asList(R.id.topleft, R.id.topmid, R.id.topright, R.id.midleft, R.id.midmid, R.id.midright, R.id.botleft, R.id.botmid, R.id.botright));
            for(int row = 0; row < 3; row++) {
                for(int column = 0; column < 3; column++) {
                    Button button = (Button) findViewById(buttons.get(row * 3 + column));
                    switch (game.getState(row, column)) {
                        case BLANK:
                            button.setText("");
                            break;
                        case CROSS:
                            button.setText("X");
                            break;
                        case CIRCLE:
                            button.setText("O");
                            break;
                    }
                }
            }
        }
        else {
            game = new Game();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("game", game);
            }

    public void titleClicked(View view) {
        Log.d("Gamestate", game.checkwon().toString());
        switch (game.checkwon()) {
            case IN_PROGRESS:
                int id = view.getId();
                int row = -1;
                int column = -1;
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

                Button btn = findViewById(id);
                switch (game.choose(row, column)) {
                    case CROSS:
                        game.changestate(CROSS, row, column);
                        btn.setText("X");
                        break;
                    case CIRCLE:
                        game.changestate(CIRCLE, row, column);
                        btn.setText("O");
                        break;
                    case INVALID:
                        Log.d("Move", "Invalid");
                        break;
                }
                break;
            case PLAYER_ONE:
                Log.d("Win", "Player 1");
                break;
            case PLAYER_TWO:
                Log.d("Win", "Player 2");
                break;
            case DRAW:
                Log.d("Draw", "Sadface");
                break;
        }
    }

    public void resetClicked(View view) {
        List<Integer> buttons = new ArrayList<>(Arrays.asList(R.id.topleft, R.id.topmid, R.id.topright, R.id.midleft, R.id.midmid, R.id.midright, R.id.botleft, R.id.botmid, R.id.botright));
        game = new Game();
        for (int i = 0; i < 9; i++) {
            Button button = (Button) findViewById(buttons.get(i));
            button.setText("");
        }
    }

}