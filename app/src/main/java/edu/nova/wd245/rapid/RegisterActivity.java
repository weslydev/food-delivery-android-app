package edu.nova.wd245.rapid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;

public class RegisterActivity extends AppCompatActivity {

    public final String APP_ID = "5CEF286A-8FD9-07F3-FFD1-0E3054DCEE00";
    public final String SECRET_KEY = "0FF67760-9E68-DC4C-FF3C-A1D292835900";
    public final String VERSION = "v1";

    EditText name;
    EditText email;
    EditText password;
    EditText confirmPass;


    String yourName;
    String yourEmail;
    String yourPass;
    String yourConfirmP;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Backendless.initApp(this, APP_ID, SECRET_KEY, VERSION);

        name = (EditText) findViewById(R.id.nameEditText);
        email = (EditText) findViewById(R.id.emailPwdEditText);
        password = (EditText) findViewById(R.id.PassEditText);
        confirmPass = (EditText) findViewById(R.id.confPassEditText);





    }


     public void loginUser(String emailUser, String passUser){
         BackendlessUser user = new BackendlessUser();
         Backendless.UserService.login(emailUser, passUser, new AsyncCallback<BackendlessUser>() {
             @Override
             public void handleResponse(BackendlessUser backendlessUser) {

             }

             @Override
             public void handleFault(BackendlessFault backendlessFault) {

             }
         });
     }

    // Register User
    public void registerUser() {
        BackendlessUser user = new BackendlessUser();

        yourName = name.getText().toString();
        yourEmail = email.getText().toString();
        yourPass = password.getText().toString();
        yourConfirmP = confirmPass.getText().toString();



        user.setEmail(yourEmail);
        user.setPassword(yourPass);
        user.setProperty("name", yourName);


        Backendless.UserService.register(user, new BackendlessCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser backendlessUser) {
                Log.i("Registration", backendlessUser.getEmail() + " successfully registered");
                loginUser(yourEmail,yourPass);
                Intent i = new Intent(RegisterActivity.this, CityListActivity.class);
                startActivity(i);
            }


            @Override
            public void handleFault(BackendlessFault fault) {

                Toast.makeText(getApplicationContext(), fault + " ", Toast.LENGTH_SHORT).show();

            }

        });

    }



    public void Register(View view) {
            registerUser();

    }

}