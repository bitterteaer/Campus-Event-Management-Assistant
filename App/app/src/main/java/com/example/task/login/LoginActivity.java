package com.example.task.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.task.MainActivityTeacher;
import com.example.task.bean.Teacher;
import com.example.task.bean.User;
//import com.example.task.CallBack.LoginCallable;
import com.example.task.MainActivity;
import com.example.task.R;
import com.example.task.http.Threads.LoginThreads;
import com.example.task.databinding.ActivityLoginBinding;
import com.example.task.http.Threads.TeacherLoginThread;
import com.example.task.http.Threads.UserThread;
import com.example.task.sqlite.dao.AutoLoginDao;
import com.example.task.sqlite.dao.TeacherDao;
import com.example.task.sqlite.dao.UserDao;
import com.google.gson.Gson;

import okhttp3.FormBody;


public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    private Button loginButton;
    private Button buttonBreak;
    private EditText usernameEditText;
    private EditText passwordEditText;
    UserDao ud;
    AutoLoginDao ald;

    public LoginActivity() {
        ud = new UserDao(this);
        ald = new AutoLoginDao(this);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        loginButton = binding.login;
        usernameEditText = binding.username;
        passwordEditText = binding.password;
//        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final ProgressBar loadingProgressBar = binding.loading;

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
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

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
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
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
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

        buttonBreak = findViewById(R.id.breakLogin);
        buttonBreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setPassword("002974");
                user.setUsername("3119002974");

                //请求后端User数据
                FormBody.Builder builderUser = new FormBody.Builder();
                builderUser.add("username",user.getUsername());
                UserThread userThread = new UserThread(builderUser);
                userThread.start();
                try {
                    userThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(userThread.value);
                //数据库操作
                User obj = new Gson().fromJson(userThread.value, User.class);
                ud.add(obj);

                Bundle bundle = new Bundle();
                bundle.putSerializable("User", user);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        Button buttonAutoLogin = findViewById(R.id.autoLogin);

        Spinner spinner = findViewById(R.id.loginSelect);
        spinner.setSelection(0);		//初始化，默认选择列表中第0个元素
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                // TODO
                if (pos==0){
                    buttonAutoLogin.setVisibility(View.VISIBLE);
                    buttonBreak.setVisibility(View.VISIBLE);
                }
                else{
                    usernameEditText.setText("101");
                    passwordEditText.setText("teacher1");
                    buttonAutoLogin.setVisibility(View.GONE);//不可见且不占用空间
                    buttonBreak.setVisibility(View.GONE);//不可见且不占用空间
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO
            }
        });


        String flag = ald.query();
        if(flag == null){
            ald.add("false");
            buttonAutoLogin.setText("自动登录");
        }else if(flag.equals("true")){
            User user = new User();
            user.setPassword("002974");
            user.setUsername("3119002974");

            Bundle bundle = new Bundle();
            bundle.putSerializable("User", user);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);

        }else{
            System.out.println("flag error");
        }

        buttonAutoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String flag = buttonAutoLogin.getText().toString();
                if(flag.equals("自动登录")){
                    buttonAutoLogin.setText("取消自动登录");
                }else {
                    buttonAutoLogin.setText("自动登录");
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadingProgressBar.setVisibility(View.VISIBLE);
//                loginViewModel.login(usernameEditText.getText().toString(),
//                        passwordEditText.getText().toString());
                if(buttonAutoLogin.getText().toString().equals("自动登录")){
                    ald.delete();
                    ald.add("false");
                }else{
                    ald.delete();
                    ald.add("true");
                }

                //todo 学生登录
                if(spinner.getSelectedItem().toString().equals("学生登录")){

                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                Log.i("loginTest", username+", "+password);

                FormBody.Builder builder = new FormBody.Builder();
                builder.add("username",username);
                builder.add("password",password);

                LoginThreads thread = new LoginThreads(builder);
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.value);

                if(thread.value.equals("success")){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    bundle.putSerializable("User", user);

                    //请求后端User数据
                    FormBody.Builder builderUser = new FormBody.Builder();
                    builderUser.add("username",user.getUsername());
                    UserThread userThread = new UserThread(builderUser);
                    userThread.start();
                    try {
                        userThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(userThread.value);
                    //数据库操作
                    User obj = new Gson().fromJson(userThread.value, User.class);
                    ud.add(obj);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }else{
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                    alertDialogBuilder.setTitle("登录失败");
                    alertDialogBuilder.setMessage("账号或密码错误");
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }}


                //todo 教师登录
                else {
                    String username = usernameEditText.getText().toString();
                    String password = passwordEditText.getText().toString();

                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("username",username);
                    builder.add("password",password);

                    TeacherLoginThread thread = new TeacherLoginThread(builder);
                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(thread.value);
                    if(thread.value.equals("error")){
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                        alertDialogBuilder.setTitle("登录失败");
                        alertDialogBuilder.setMessage("账号或密码错误");
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }else{
                        Intent intent = new Intent(LoginActivity.this, MainActivityTeacher.class);
                        Bundle bundle = new Bundle();
                        Teacher teacher = new Gson().fromJson(thread.value, Teacher.class);
                        bundle.putSerializable("Teacher", teacher);

                        //数据库操作
                        new TeacherDao(LoginActivity.this).add(teacher);
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                }

            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
