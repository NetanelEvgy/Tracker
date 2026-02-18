package com.example.myapplication;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.ai.client.generativeai.Chat;
import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.type.Part;
import com.google.ai.client.generativeai.type.RequestOptions;
import com.google.ai.client.generativeai.type.TextPart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

public class GeminiChatManager
{
    private static GeminiChatManager instance;
    private GenerativeModel gemini;
    private Chat chat;
    private final String TAG = "GeminiChatManager";

    private void startChat()
    {
        chat = gemini.startChat(Collections.emptyList());
    }
    private GeminiChatManager(String systemPrompt)
    {
        List<Part> parts = new ArrayList<Part>();
        parts.add(new TextPart(systemPrompt));
        gemini = new GenerativeModel(
                "gemini-2.5-flash",
                BuildConfig.Gemini_API_Key,
                null,
                null,
                new RequestOptions(),
                null,
                null,
                new Content(parts)
        );
        startChat();
    }
    public static GeminiChatManager getInstance(String systemPrompt)
    {
        if (instance == null)
        {
            instance = new GeminiChatManager(systemPrompt);
        }
        return instance;
    }
    public void sendChatMessage(String prompt, GeminiCallback callback)
    {
        chat.sendMessage(prompt, new Continuation<GenerateContentResponse>()
        {
            @NonNull
            @Override
            public CoroutineContext getContext()
            {
                return EmptyCoroutineContext.INSTANCE;
            }
            @Override
            public void resumeWith(@NonNull Object result) {
                if (result instanceof Result.Failure) {
                    Log.i(TAG, "Error: " + ((Result.Failure) result).exception.getMessage());
                    callback.onFailure(((Result.Failure) result).exception);
                }
                else
                {
                    Log.i(TAG, "Success: " + ((GenerateContentResponse) result).getText());callback.onSuccess(((GenerateContentResponse) result).getText());
                }
            }
        });
    }
}