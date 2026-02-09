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

public class SignupFragment extends Fragment {
    private EditText username, password;
    private TextView errorMsg;
    private Button signupButton;

    public SignupFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        init(view);
        setButtonsEnabled(true);
        signupButton.setOnClickListener(v -> attemptSignup());
        return view;
    }

    private void init(View view)
    {
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        signupButton = view.findViewById(R.id.signupButton);
        errorMsg = view.findViewById(R.id.errorMsg);
    }

    private void setButtonsEnabled(boolean enabled)
    {
        signupButton.setEnabled(enabled);
        ((LoginOrSignup) requireActivity()).enableTabSwitch(enabled);
    }
    private void attemptSignup()
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
            if (!exists)
            {
                User user = new User(username.getText().toString(), password.getText().toString());
                Firebase.setUser(user);
                Intent intent = new Intent(requireActivity(), Notes.class);
                intent.putExtra("USERNAME_KEY", username.getText().toString());
                startActivity(intent);
            }
            else
            {
                errorMsg.setText("User already exists");
                setButtonsEnabled(true);
            }
        });
    }
}
