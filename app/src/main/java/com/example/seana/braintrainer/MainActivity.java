package com.example.seana.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView sumTextView;
    Button buttonZero;
    Button buttonOne;
    Button buttonTwo;
    Button buttonThree;
    int correctAnswerLocation;
    TextView correctTextView;
    int amountofCorrectAnswers = 0;
    int amountofTries = 0;
    TextView toPostRighAndWrongAnswers;
    TextView timerText;
    Button playAgainButton;
    ConstraintLayout constraintLayoutTwo;
    String y;

    // now we create an arraylist to hput all the random numbers that we generated
    ArrayList<Integer> arrayList = new ArrayList<Integer>();

    // so the first thing we will do is handle the click for the initial button
    public void forClick(View view) {
        // first things first we will in turn set the button to invisible
        goButton.setVisibility(View.INVISIBLE);
        //and then we will set the constraint to visible
        constraintLayoutTwo.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));


    }

    public void playAgain(View view) {
        amountofCorrectAnswers = 0;
        amountofTries = 0;
        toPostRighAndWrongAnswers = (TextView) findViewById(R.id.rightAndWrongLogger);
        toPostRighAndWrongAnswers.setText(Integer.toString(amountofCorrectAnswers) + "/" + Integer.toString(amountofTries));
        correctTextView.setText("");

        timerText.setText("30S");
        boardWithNumbers();
        playAgainButton.setVisibility(View.INVISIBLE);

        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                timerText.setText(String.valueOf(l / 1000));
            }

            public void onFinish() {

                correctTextView.setText("You are out of time!");
                correctTextView.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);


            }
        }.start();

    }


    // so first we will create an individualised function to create the board with numbers
    public void boardWithNumbers() {
        // so the forst thing we will do is generate our random numbers so as to do the calculations upon
        Random random = new Random();
        int a = random.nextInt(20) + 1;
        int b = random.nextInt(20) + 1;

        sumTextView = (TextView) findViewById(R.id.sumTextView);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        // note that it is VERY IMPORTANT that the arraylist is empty prior to the loop otherwise the text will never change
        arrayList.clear();


        // first we will determine a location for the correct answer randomly
        correctAnswerLocation = random.nextInt(4);

        // now we will create a loop so as to place a bunch of different values in the arraylist
        for (int i = 0; i < 4; i++) {
            // so ohere we get the correct answer to be in whatever random index was generated
            if (i == correctAnswerLocation) {
                arrayList.add(a + b);
            } else {
                // first we will generate the randomnumber
                int randomIncorrectNumber = random.nextInt(40) + 1;

                // now we wil generate the wrong numbers which need to be different from each other and the correct answer
                while(randomIncorrectNumber == a+b || arrayList.contains(randomIncorrectNumber)) {
                    // so if any of these are the case we will have to generate a new number
                    randomIncorrectNumber = random.nextInt(40) + 1;
                }

                // so now we will add the randomIncorrectNumber to the arraylist
                arrayList.add(randomIncorrectNumber);

            }
        }

        buttonZero = (Button) findViewById(R.id.button0);
        buttonOne = (Button) findViewById(R.id.button1);
        buttonTwo = (Button) findViewById(R.id.button2);
        buttonThree = (Button) findViewById(R.id.button3);

        // so now we change the text of each button as per the array list
        buttonZero.setText(Integer.toString(arrayList.get(0)));
        buttonOne.setText(Integer.toString(arrayList.get(1)));
        buttonTwo.setText(Integer.toString(arrayList.get(2)));
        buttonThree.setText(Integer.toString(arrayList.get(3)));
    }




    // now we are going to want to create a clicker which determines whether the answer that we selected is right or wrong
    public void rightOrWrongClicker(View view) {
        // so first thig we will set the tag of the button clicked into a string variable
        String clickedTag = view.getTag().toString();

        // then we will signify that a try was registered
        amountofTries+=1;



        Log.i("What",clickedTag);
        Log.i("What",Integer.toString(correctAnswerLocation));

        // so if the tag matches the location of the correct answer which we established in the setting part we are correct
        if (clickedTag.equals(Integer.toString(correctAnswerLocation))) {
            Log.i("It","Happenned");
            correctTextView.setText("Correct!");
            correctTextView.setVisibility(View.VISIBLE);

            // now in this case we are going to want to add one to the correctanswers
            amountofCorrectAnswers+=1;

        } else {
            correctTextView.setText("Incorrect!");
            correctTextView.setVisibility(View.VISIBLE);

        }

        // now we will go about setting the amount of tries and right answers that we had!
        toPostRighAndWrongAnswers.setText(Integer.toString(amountofCorrectAnswers) + "/" + Integer.toString(amountofTries));

        // now at the end we are going to want to in turn call the board method
        // this is so that immediately after choosing an answer (through a click) we get new questions
        boardWithNumbers();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerText = (TextView) findViewById(R.id.timerTextView);
        correctTextView = (TextView) findViewById(R.id.correctNow);

        // so to get it everywhere we will set the goButton to be a variable on the Oncreate
        goButton = (Button) findViewById(R.id.button);

        constraintLayoutTwo = (ConstraintLayout) findViewById(R.id.gameLayout);

        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        toPostRighAndWrongAnswers = (TextView) findViewById(R.id.rightAndWrongLogger);





    }
    }

