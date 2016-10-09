package br.com.nomadweb.mathgamechapter2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    int correctAnswer;
    Button buttonObjectChoice1;
    Button buttonObjectChoice2;
    Button buttonObjectChoice3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Here we initialize all our variables
        int partA = 9;
        int partB = 9;
        correctAnswer = partA * partB;
        int wrongAnswer1 = correctAnswer - partA;
        int wrongAnswer2 = correctAnswer + partA;

        /*
        Here we get a working object based on either the button or TextView class and base as
        well as link our new objects directly to the appropriate UI elements that we created
        previously
        */
        TextView textObjectPartA = (TextView)findViewById(R.id.textPartA);
        TextView textObjectPartB = (TextView)findViewById(R.id.textPartB);
        buttonObjectChoice1 = (Button)findViewById(R.id.buttonChoice1);
        buttonObjectChoice2 = (Button)findViewById(R.id.buttonChoice2);
        buttonObjectChoice3 = (Button)findViewById(R.id.buttonChoice3);

        /*
        Now we use the setText method of the class on our objects to show our variable values on
        the UI elements.
        */
        textObjectPartA.setText(Integer.toString(partA));
        textObjectPartB.setText(Integer.toString(partB));

        //wich button receive wich answer
        buttonObjectChoice1.setText(Integer.toString(correctAnswer));
        buttonObjectChoice2.setText(Integer.toString(wrongAnswer1));
        buttonObjectChoice3.setText(Integer.toString(wrongAnswer2));

        buttonObjectChoice1.setOnClickListener(this);
        buttonObjectChoice2.setOnClickListener(this);
        buttonObjectChoice3.setOnClickListener(this);
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

        // is the right answer?
        if(answerGiven == correctAnswer){
            Toast.makeText(getApplicationContext(), "Well done!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Sorry that's wrong!", Toast.LENGTH_LONG).show();
        }

    }
}

