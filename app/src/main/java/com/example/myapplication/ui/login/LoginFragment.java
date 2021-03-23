package com.example.myapplication.ui.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.R;
import com.example.myapplication.databases.UsersDBHelper;

public class LoginFragment extends Fragment {

    Button button_login;

    private LoginViewModel loginViewModel;
    UsersDBHelper users = new UsersDBHelper(getContext());
    User user = new User(null,null,false,false);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = view.findViewById(R.id.username);
        final EditText passwordEditText = view.findViewById(R.id.password);
        final EditText nameEditText = view.findViewById(R.id.name);
        final Button loginButton = view.findViewById(R.id.login);
        final Button registerButton = view.findViewById(R.id.register);
        final ProgressBar loadingProgressBar = view.findViewById(R.id.loading);
        final CheckBox tutor = view.findViewById(R.id.Tutor);
        final CheckBox student = view.findViewById(R.id.StudentC);

        final Button studentButton = view.findViewById(R.id.student);
        final Button tutorButton = view.findViewById(R.id.tutorButton);
        final Button studentAndTutorButton = view.findViewById(R.id.studentAndTutorButton);

        button_login = view.findViewById(R.id.button_login);

        loginViewModel.getLoginFormState().observe(getViewLifecycleOwner(), new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(getViewLifecycleOwner(), new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        button_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(com.example.myapplication.ui.login.LoginFragment.this)
                        .navigate(R.id.action_loginFragment_to_home_student);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
                user.setNetID(usernameEditText.getText().toString());
                user.setPassword(passwordEditText.getText().toString());
                // TODO: Check if user is actually what they checked
                if (tutor.isChecked()) {user.setTutor(true);}
                if (student.isChecked()) {user.setStudent(true);}
                Bundle userData = new Bundle();
                userData.putSerializable("user", user);
                // Check if student or tutor
                // Example nav
                // TODO: decide which fragment to send to based on database results
                NavHostFragment.findNavController(com.example.myapplication.ui.login.LoginFragment.this)
                        .navigate(R.id.action_loginFragment_to_studentHome,userData);
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (tutor.isChecked() && student.isChecked()) {
                        users.addData(usernameEditText.getText().toString(), passwordEditText.getText().toString(), nameEditText.getText().toString(), usernameEditText.getText().toString(),
                                true, true);
                    } else if (student.isChecked()) {
                        users.addData(usernameEditText.getText().toString(), passwordEditText.getText().toString(), nameEditText.getText().toString(), usernameEditText.getText().toString(),
                                false, true);
                    } else if (tutor.isChecked()) {
                        users.addData(usernameEditText.getText().toString(), passwordEditText.getText().toString(), nameEditText.getText().toString(), usernameEditText.getText().toString(),
                                true, false);
                    } else {
                        // TODO: handle incorrect login
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                user.setNetID(usernameEditText.getText().toString());
                user.setPassword(passwordEditText.getText().toString());
                if (tutor.isChecked()) {user.setTutor(true);}
                if (student.isChecked()) {user.setStudent(true);}
                // TODO: send to fragment based on user inputs results
                //loadingProgressBar.setVisibility(View.VISIBLE);
                // loginViewModel.login(usernameEditText.getText().toString(),passwordEditText.getText().toString());
            }
        });

        studentButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(com.example.myapplication.ui.login.LoginFragment.this)
                        .navigate(R.id.action_loginFragment_to_studentHome);
            }
        });

        tutorButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(com.example.myapplication.ui.login.LoginFragment.this)
                        .navigate(R.id.action_loginFragment_to_tutorHome);
            }
        });

        studentAndTutorButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(com.example.myapplication.ui.login.LoginFragment.this)
                        .navigate(R.id.action_loginFragment_to_studentTutorHome);
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        if (getContext() != null && getContext().getApplicationContext() != null) {
            Toast.makeText(getContext().getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        }
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        if (getContext() != null && getContext().getApplicationContext() != null) {
            Toast.makeText(
                    getContext().getApplicationContext(),
                    errorString,
                    Toast.LENGTH_LONG).show();
        }
    }
}