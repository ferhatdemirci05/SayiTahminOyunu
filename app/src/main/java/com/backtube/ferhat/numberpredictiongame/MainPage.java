package com.backtube.ferhat.numberpredictiongame;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MainPage extends AppCompatActivity {

    Button btnRefresh, btnCheckIt,btnNextGame;
    ImageView heart1, heart2, heart3, heart4, heart5;
    TextView txtScore, txtPoint, txtMessage;
    LinearLayout linearHeart;
    EditText text;
    int count = 0;
    int _try = 5;
    int point = 10;
    int score =0;
    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        count = r.nextInt(100);
        GetFindView();

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = r.nextInt(100);
                _try=5;
                score = 0;
                point=10;
                CanRenew();
                txtMessage.setText("");
                text.setText("");
                btnNextGame.setVisibility(View.INVISIBLE);
                txtPoint.setText(getResources().getString(R.string.txtPoint)+ point);
                txtScore.setText(getResources().getString(R.string.txtScore)+score);
                btnCheckIt.setEnabled(true);
            }
        });

        btnNextGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = r.nextInt(100);
                _try=5;
                CanRenew();
                point=10;
                txtMessage.setText("");
                txtPoint.setText(getResources().getString(R.string.txtPoint)+ point);
                text.setText("");
                btnNextGame.setVisibility(View.INVISIBLE);
            }
        });

        btnCheckIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = text.getText().toString();
                if (inputText == "") {
                    txtMessage.setText(R.string.txtMessageEmpty);
                    return;
                }

                int inputCount = Integer.parseInt(text.getText().toString());
                if (inputCount < count) {
                    txtMessage.setText(R.string.txtMessageUp);
                    IncorrectAnswer();
                } else if (inputCount > count) {
                    txtMessage.setText(R.string.txtMessageDown);
                    IncorrectAnswer();
                } else {
                    score+=point;
                    btnNextGame.setVisibility(View.VISIBLE);
                    txtMessage.setText(getResources().getString(R.string.txtMessageWin)+ point);
                }
            }
        });
    }

    protected void IncorrectAnswer() {
        CanDrop();
        _try--;
        point -= 2;
        if (_try == 0) {
            txtMessage.setText(getResources().getString(R.string.txtMessageLose) + count );
            btnCheckIt.setEnabled(false);
            point = 0;
        }
        txtPoint.setText(getResources().getString(R.string.txtPoint) + point);

    }

    protected void  CanRenew(){
        int heartCount = linearHeart.getChildCount();
        for (int i =0; i<heartCount;i++){
            ImageView v = (ImageView)linearHeart.getChildAt(i);
                v.setImageResource(R.drawable.baseline_favorite_24);
        }
    }

    protected void CanDrop() {

        int heartCount = linearHeart.getChildCount();
        for (int i =0; i<heartCount;i++){
            ImageView v = (ImageView)linearHeart.getChildAt(i);
            if (i + 2>_try){
                v.setImageResource(R.drawable.baseline_favorite_border_24);
            }
        }

        /*switch (_try) {
            case 5:
                heart5.setImageResource(R.drawable.baseline_favorite_border_24);
                break;
            case 4:
                heart4.setImageResource(R.drawable.baseline_favorite_border_24);
                break;
            case 3:
                heart3.setImageResource(R.drawable.baseline_favorite_border_24);
                break;
            case 2:
                heart2.setImageResource(R.drawable.baseline_favorite_border_24);
                break;
            case 1:
                heart1.setImageResource(R.drawable.baseline_favorite_border_24);
                break;
        }
        */
    }

    protected void GetFindView() {
        //Button
        btnRefresh = findViewById(R.id.btnRefresh);
        btnNextGame = findViewById(R.id.btnNextGame);
        btnCheckIt = findViewById(R.id.btncheckIt);


        //ImageView
        heart1 = findViewById(R.id.heart1);
        heart5 = findViewById(R.id.heart5);
        heart2 = findViewById(R.id.heart2);
        heart4 = findViewById(R.id.heart4);
        heart3 = findViewById(R.id.heart3);

        //TextView
        txtScore = findViewById(R.id.txtScore);
        txtMessage = findViewById(R.id.txtMessage);
        txtPoint = findViewById(R.id.txtPoint);

        //EditText
        text = findViewById(R.id.text);

        //LinearLayout
        linearHeart = findViewById(R.id.linearHearts);
    }
}
