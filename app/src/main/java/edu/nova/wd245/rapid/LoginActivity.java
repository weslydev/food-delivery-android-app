package edu.nova.wd245.rapid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class LoginActivity extends AppCompatActivity {

    public final String APP_ID = "5CEF286A-8FD9-07F3-FFD1-0E3054DCEE00";
    public final String SECRET_KEY = "0FF67760-9E68-DC4C-FF3C-A1D292835900";
    public final String VERSION = "v1";

    EditText email;
    EditText password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Backendless.initApp(this, APP_ID, SECRET_KEY, VERSION);

        email = (EditText) findViewById(R.id.emailEditText1);
        password = (EditText)findViewById(R.id.passEditText1);


  //log user automatically
//        String userToken = UserTokenStorageFactory.instance().getStorage().get();
//        if((userToken != null) && !userToken.equals("") ){
//
//            Intent i = new Intent(LoginActivity.this, CityListActivity.class);
//            startActivity(i);
//
//        }


        //push notifications


    }


    public void login(View view) {

        String mail = email.getText().toString();
        String pass = password.getText().toString();
        boolean stayLoggedIn = true;

        BackendlessUser user = new BackendlessUser();




        Backendless.UserService.login(mail, pass,  new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser backendlessUser) {
              //  Toast.makeText(getApplicationContext(), "you logged in" , Toast.LENGTH_SHORT ).show();
                Intent i = new Intent(LoginActivity.this, CityListActivity.class);
                startActivity(i);
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(getApplicationContext(), "Sorry something went wrong" , Toast.LENGTH_SHORT ).show();
            }
        },stayLoggedIn);

    }

    public void facebookLogin(View view) {


            Backendless.UserService.loginWithFacebook(this, new AsyncCallback<BackendlessUser>() {
                @Override
                public void handleResponse(BackendlessUser backendlessUser) {
                    Toast.makeText(getApplicationContext(), "you logged in" , Toast.LENGTH_SHORT ).show();

                }

                @Override
                public void handleFault(BackendlessFault backendlessFault) {

                }
            });

    }

    public void register(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    public void resetPassword(View view) {

        Intent intent3 = new Intent(this, ResetPassActivity.class);
        startActivity(intent3);
    }
}
