package br.com.nomadweb.mathgamechapter2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    int correctAnswer;
    int currentScore = 0;
    int currentLevel = 1;

    Button buttonObjectChoice1;
    Button buttonObjectChoice2;
    Button buttonObjectChoice3;

    TextView textObjectPartA;
    TextView textObjectPartB;
    TextView textObjectScore;
    TextView textObjectLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        /*
        Here we get a working object based on either the button or TextView class and base as
        well as link our new objects directly to the appropriate UI elements that we created
        previously
        */
        textObjectPartA = (TextView)findViewById(R.id.textPartA);
        textObjectPartB = (TextView)findViewById(R.id.textPartB);
        textObjectScore = (TextView)findViewById(R.id.textScore);
        textObjectLevel = (TextView)findViewById(R.id.textLevel);

        buttonObjectChoice1 = (Button)findViewById(R.id.buttonChoice1);
        buttonObjectChoice2 = (Button)findViewById(R.id.buttonChoice2);
        buttonObjectChoice3 = (Button)findViewById(R.id.buttonChoice3);

        buttonObjectChoice1.setOnClickListener(this);
        buttonObjectChoice2.setOnClickListener(this);
        buttonObjectChoice3.setOnClickListener(this);

        setQuestion();
    }

    @Override
    public void onClick(View view) {
        // declare a new int to be used in all the cases
        int answerGiven = 0;

        switch (view.getId()) {
            case R.id.buttonChoice1:
                answerGiven = Integer.parseInt(buttonObjectChoice1.getText().toString());
                break;
            case R.id.buttonChoice2:
                answerGiven = Integer.parseInt(buttonObjectChoice2.getText().toString());
                break;
            case R.id.buttonChoice3:
                answerGiven = Integer.parseInt(buttonObjectChoice3.getText().toString());
                break;
        }

        updateScoreAndLevel(answerGiven);

        setQuestion();
    }

    void setQuestion(){
        // generate the parts of the question
        int numberRange = currentLevel * 3;
        Random randInt = new Random();

        int partA = randInt.nextInt(numberRange);
        partA++;

        int partB = randInt.nextInt(numberRange);
        partB++;

        correctAnswer = partA * partB;
        int wrongAnswer1 = correctAnswer - partA;
        int wrongAnswer2 = correctAnswer + partA;

        textObjectPartA.setText(Integer.toString(partA));
        textObjectPartB.setText(Integer.toString(partB));

        // set multi choice buttons a number between 0 and 2
        int buttonLayout = randInt.nextInt(3);

        switch (buttonLayout){
            case 0:
                buttonObjectChoice1.setText(Integer.toString(correctAnswer));
                buttonObjectChoice2.setText(Integer.toString(wrongAnswer1));
                buttonObjectChoice3.setText(Integer.toString(wrongAnswer2));
                break;
            case 1:
                buttonObjectChoice2.setText(Integer.toString(correctAnswer));
                buttonObjectChoice3.setText(Integer.toString(wrongAnswer1));
                buttonObjectChoice1.setText(Integer.toString(wrongAnswer2));
                break;
            case 2:
                buttonObjectChoice3.setText(Integer.toString(correctAnswer));
                buttonObjectChoice1.setText(Integer.toString(wrongAnswer1));
                buttonObjectChoice2.setText(Integer.toString(wrongAnswer2));
                break;
        }
    }

    void updateScoreAndLevel(int answerGiven){

        if(isCorrect(answerGiven)){
            for(int i=1; i <= currentLevel; i++){
                currentScore = currentScore + i;
            }
            currentLevel++;
        } else {
            currentScore = 0;
            currentLevel = 1;
        }

        // Actually update the two TextViews
        textObjectScore.setText("Score: " + currentScore);
        textObjectLevel.setText("Level: " + currentLevel);
    }

    boolean isCorrect(int answerGiven){
        boolean correctTrueOrFalse;

        if(answerGiven == correctAnswer){
            Toast.makeText(getApplicationContext(), "Well done!", Toast.LENGTH_LONG).show();
            correctTrueOrFalse = true;
        } else {
            Toast.makeText(getApplicationContext(), "Sorry", Toast.LENGTH_LONG).show();
            correctTrueOrFalse = false;
        }

        return correctTrueOrFalse;
    }

}

