package org.ieselcaminas.pmdm.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button[][] buttons;
    public static final int NUM_ROWS=3;
    private GridLayout gl;
    private int player;
    private int win;
    private CharSequence play;
    private TextView tv;
    private boolean gameOver ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameOver=false;
        gl= findViewById(R.id.gridLayoutButtons);
        gl.setColumnCount(NUM_ROWS);
        gl.setRowCount(NUM_ROWS);
        tv =findViewById(R.id.textView);
        createButtons();
    }
    public void createButtons() {
        player=1;
        buttons= new Button[NUM_ROWS][NUM_ROWS];
        for (int i=0;i<NUM_ROWS;i++){
            for (int y=0;y<NUM_ROWS;y++){
                buttons[i][y]=new Button(getApplicationContext(), null, android.R.attr.buttonStyleSmall);
                gl.addView(buttons[i][y]);
                buttons[i][y].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (gameOver)return;
                        Button but= (Button)v;
                        if (but.getText()==""){
                            if (player==1){
                                tv.setText("Turn to Player 2 - O -");
                                but.setText("X");
                                testWin();
                                player=2;
                            } else if (player==2) {
                                tv.setText("Turn to Player 1 - X -");
                                but.setText("O");
                                testWin();
                                player = 1;
                            }
                        }
                    }
                });
            }
        }
    }
    private void testWin() {
        for (int i =0;i<NUM_ROWS;i++){
            win=0;
            for (int y=0;y<NUM_ROWS;y++){
                if (y==0){
                    play=buttons[i][y].getText();
                    win++;
                }else{
                    if (buttons[i][y].getText()==play && play!=""){
                        win++;
                    }
                }
                if (win==3){
                    tv.setText("Player " + player + " WIN");
                    gameOver=true;
                }
            }
        }
        for(int x=0;x<NUM_ROWS;x++){
            win=0;
            for (int z=0;z<NUM_ROWS;z++){
                if (z==0){
                    play=buttons[z][x].getText();
                    win++;
                }else{
                    if (buttons[z][x].getText()==play && play!=""){
                        win++;
                    }
                }
                if (win==3){
                    tv.setText("Player " + player + " WIN");
                    gameOver=true;
                }
            }
        }
        if (buttons[1][1].getText()!=""){
            play=buttons[1][1].getText();
            if (play==buttons[0][0].getText()&&play==buttons[2][2].getText()){
                tv.setText("Player " + player + " WIN");
                gameOver=true;
            }
            if (play==buttons[0][2].getText()&&play==buttons[2][0].getText()){
                tv.setText("Player " + player + " WIN");
                gameOver=true;
            }
        }
    }
}
