package edu.nova.wd245.rapid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class PushNotificationActivity extends AppCompatActivity {

    BackendlessConfig backendlessConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notification);

        backendlessConfig = new BackendlessConfig();

        Backendless.initApp(this, backendlessConfig.APP_ID, backendlessConfig.SECRET_KEY, backendlessConfig.VERSION);

        Backendless.Messaging.registerDevice("289715559415", new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(getApplicationContext(), backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
