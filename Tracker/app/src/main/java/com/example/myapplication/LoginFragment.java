package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {
    private EditText username, password;
    private TextView errorMsg;
    private Button loginButton;

    public LoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        init(view);
        setButtonsEnabled(true);
        loginButton.setOnClickListener(v -> attemptLogin());
        return view;
    }

    private void init(View view)
    {
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        loginButton = view.findViewById(R.id.loginButton);
        errorMsg = view.findViewById(R.id.errorMsg);
    }
    private void setButtonsEnabled(boolean enabled)
    {
        loginButton.setEnabled(enabled);
        ((LoginOrSignup) requireActivity()).enableTabSwitch(enabled);
    }

    private void attemptLogin()
    {
        setButtonsEnabled(false);
        if (username.getText().toString().isEmpty())
        {
            errorMsg.setText("Username cannot be empty");
            setButtonsEnabled(true);
            return;
        }
        if (password.getText().toString().isEmpty())
        {
            errorMsg.setText("Password cannot be empty");
            setButtonsEnabled(true);
            return;
        }

        Firebase.doesUserExist(username.getText().toString(), exists ->
        {
            if (exists)
            {
                Firebase.doesPasswordMatch(username.getText().toString(), password.getText().toString(), passwordsMatch ->
                {
                    if (passwordsMatch)
                    {
                        Intent intent = new Intent(requireActivity(), Notes.class);
                        intent.putExtra("USERNAME_KEY", username.getText().toString());
                        startActivity(intent);
                    }
                    else
                    {
                        errorMsg.setText("Incorrect Password");
                        setButtonsEnabled(true);
                    }
                });
            }
            else
            {
                errorMsg.setText("User does not exist");
                setButtonsEnabled(true);
            }
        });
    }
}