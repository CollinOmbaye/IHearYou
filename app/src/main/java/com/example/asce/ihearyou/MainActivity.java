package com.example.asce.ihearyou;

import android.content.Intent;
import android.graphics.Color;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    final String classname = "SAMMY";
    TextToSpeech textToSpeech;
    FloatingActionButton button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.floatingActionButton);
        button.setTranslationX(20);
        button.setTranslationY(20);
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
            if (i==TextToSpeech.SUCCESS)
            {
                Log.i(classname,"success");
            }
            else
            {

                String x;
                x= "sorry";
                Log.i(classname,x);
            }
            }

        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent speechintent = new Intent( RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                if(speechintent.resolveActivity(getPackageManager())!=null) {
                    startActivityForResult(speechintent,10);
                }
             else {
                Toast.makeText(getApplicationContext(), "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
            }


            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    changer(result.get(0));
                    Log.i("sammy", result.get(0));
                }
                break;
        }
    }


    public void changer(String colorset)
    {
        View view;
        switch (colorset)
        {
            case "red":
                 view = this.getWindow().getDecorView();
                view.setBackgroundColor(Color.parseColor(colorset));
                textToSpeech.speak("Here is the "+ colorset + "screen",TextToSpeech.QUEUE_ADD,null,"EE");

                break;

            case  "yellow":
                 view = this.getWindow().getDecorView();
                view.setBackgroundColor(Color.parseColor(colorset));
                textToSpeech.speak("Here is the "+ colorset + "screen",TextToSpeech.QUEUE_ADD,null,"EE");

                break;



        }

    }

}