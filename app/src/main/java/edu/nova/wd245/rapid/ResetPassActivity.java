package edu.nova.wd245.rapid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class ResetPassActivity extends AppCompatActivity {

    BackendlessConfig backendlessConfig;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        backendlessConfig = new BackendlessConfig();
        Backendless.initApp(this,backendlessConfig.APP_ID,backendlessConfig.SECRET_KEY,backendlessConfig.VERSION);

        email = (EditText) findViewById(R.id.emailPwdEditText);
    }

    public void resetPassword(View view) {

        String emailPass;
        emailPass = email.getText().toString();

        Backendless.UserService.restorePassword(emailPass, new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void aVoid) {
                Toast.makeText(getApplicationContext(), "An email was sent to you.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {

                Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();

            }
        });

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}
