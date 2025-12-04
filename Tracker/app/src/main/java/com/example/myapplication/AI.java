package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class AI extends AppCompatActivity implements TextToSpeech.OnInitListener {

    TextView AIMessage;
    Button AIButton, TTSButton, backToNotesButton;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ai);
        init();
        setButtonsEnabled(true);
        code();
    }
    @Override
    public void onInit(int status)
    {
        if (status == TextToSpeech.SUCCESS)
        {
            int result = tts.setLanguage(Locale.US);
            if (result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED) {
                TTSButton.setEnabled(true);
            }
        }
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        if (tts != null) {
            tts.stop();
        }
    }
    @Override
    protected void onDestroy()
    {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
    private void setButtonsEnabled(boolean enabled) {
        AIButton.setEnabled(enabled);
        backToNotesButton.setEnabled(enabled);
    }
    private void init()
    {
        AIMessage = findViewById(R.id.AIMessage);
        AIButton = findViewById(R.id.AIButton);
        TTSButton = findViewById(R.id.TTSButton);
        backToNotesButton = findViewById(R.id.backToNotesButton);
    }
    private void code()
    {
        Intent prevIntent = getIntent();
        String username = prevIntent.getStringExtra("USERNAME_KEY");
        TTSButton.setEnabled(false);
        tts = new TextToSpeech(this,this);

        AIButton.setOnClickListener(v -> {
            setButtonsEnabled(false);
            TTSButton.setEnabled(false);
            AIMessage.setText("Loading...");
            GeminiManager.getInstance().sendTextPrompt("give me around 30 - 50 words of advice for time management, be creative.", new GeminiCallback() {
                @Override
                public void onSuccess(String result) {
                    runOnUiThread(() -> {
                        AIMessage.setText(result.trim());
                        TTSButton.setEnabled(true);
                        setButtonsEnabled(true);
                    });
                }
                @Override
                public void onFailure(Throwable error) {
                    runOnUiThread(() -> {
                        AIMessage.setText("Error: " + error.getMessage());
                        TTSButton.setEnabled(true);
                        setButtonsEnabled(true);
                    });
                }
            });

        });
        TTSButton.setOnClickListener(v -> {
            String speech = AIMessage.getText().toString();
            if (!speech.isEmpty())
            {
                Bundle params = new Bundle();
                params.putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, 0.4f);
                tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
        backToNotesButton.setOnClickListener(v -> {
            setButtonsEnabled(false);
            TTSButton.setEnabled(false);
            Intent newIntent = new Intent(AI.this, Notes.class);
            newIntent.putExtra("USERNAME_KEY", username);
            startActivity(newIntent);
        });
    }
}