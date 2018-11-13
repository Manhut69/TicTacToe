package com.example.clint.tictactoe;

import android.widget.Button;

import java.io.Serializable;

import static com.example.clint.tictactoe.GameState.DRAW;
import static com.example.clint.tictactoe.GameState.IN_PROGRESS;
import static com.example.clint.tictactoe.GameState.PLAYER_ONE;
import static com.example.clint.tictactoe.GameState.PLAYER_TWO;
import static com.example.clint.tictactoe.TileState.BLANK;
import static com.example.clint.tictactoe.TileState.CIRCLE;
import static com.example.clint.tictactoe.TileState.CROSS;
import static com.example.clint.tictactoe.TileState.INVALID;

public class Game implements Serializable {
    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    private Boolean playerOneTurn;
    private int movesPlayed;
    private Boolean gameOver;

    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
                for(int i = 0; i<BOARD_SIZE; i++)
                    for(int j=0; j<BOARD_SIZE; j++)
                        board[i][j] = BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    public TileState choose(int row, int column) {
        if (board[row][column] == BLANK) {
            if(playerOneTurn) {
                playerOneTurn = false;
                return CROSS;
            }
            playerOneTurn = true;
            return CIRCLE;
        }
        else
            return INVALID;

    }

    public TileState getState(int row, int column) {
        return(board[row][column]);
    }

    public void changestate(TileState state, int row, int column) {
        switch(state) {
            case CROSS:
                board[row][column] = CROSS;
                break;
            case CIRCLE:
                board[row][column] = CIRCLE;
                break;
        }
    }

    public GameState checkwon() {
        if(board[0][0] == board[0][1] && board[0][1] == board[0][2]){
            if (board[0][0] == CROSS){
                return PLAYER_ONE;
            }
            else if(board[0][0] == CIRCLE){
                return PLAYER_TWO;
            }
            return IN_PROGRESS;
        }
        else if(board[0][0] == board[1][0] && board[1][0] == board[2][0]){
            if (board[0][0] == CROSS){
                return PLAYER_ONE;
            }
            else if(board[0][0] == CIRCLE){
                return PLAYER_TWO;
            }
            return IN_PROGRESS;
        }
        else if(board[0][0] == board[1][1] && board[1][1] == board[2][2]){
            if (board[0][0] == CROSS){
                return PLAYER_ONE;
            }
            else if (board[0][0] == CIRCLE) {
                return PLAYER_TWO;
            }
            return IN_PROGRESS;
        }
        else if(board[1][0] == board[1][1] && board[1][1] == board[1][2]){
            if (board[1][0] == CROSS){
                return PLAYER_ONE;
            }
            else if(board[1][0] == CIRCLE){
                return PLAYER_TWO;
            }
            return IN_PROGRESS;
        }
        else if(board[2][0] == board[2][1] && board[2][1] == board[2][2]){
            if (board[2][0] == CROSS){
                return PLAYER_ONE;
            }
            else if(board[2][0] == CIRCLE){
                return PLAYER_TWO;
            }
            return IN_PROGRESS;
        }
        else if(board[0][1] == board[1][1] && board[1][1] == board[2][1]){
            if (board[0][1] == CROSS){
                return PLAYER_ONE;
            }
            else if(board[0][1] == CIRCLE){
                return PLAYER_TWO;
            }
            return IN_PROGRESS;
        }
        else if(board[0][2] == board[1][2] && board[1][2] == board[2][2]){
            if (board[0][2] == CROSS){
                return PLAYER_ONE;
            }
            else if(board[0][2] == CIRCLE){
                return PLAYER_TWO;
            }
            return IN_PROGRESS;
        }
        else if(board[0][2] == board[1][1] && board[1][1] == board[2][0]){
            if (board[0][2] == CROSS){
                return PLAYER_ONE;
            }
            else if(board[0][2] == CIRCLE){
                return PLAYER_TWO;
            }
            return IN_PROGRESS;
        }
        for(int i = 0; i<BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == BLANK) {
                    return IN_PROGRESS;
                }
            }
        }
        return DRAW;
    }
}
