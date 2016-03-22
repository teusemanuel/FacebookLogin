package br.com.commons.facebooklogin.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;

import br.com.commons.facebooklogin.R;
import br.com.commons.facebooklogin.dto.FriendFacebookDTO;
import br.com.commons.facebooklogin.views.Friends.FriendsActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "br.com.commons.facebooklogin.views.LoginActivity";


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Set up facebook in app
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();


        setContentView(R.layout.activity_login);

        // Set up progressLoad
        mProgressView = findViewById(R.id.login_progress);


        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("public_profile", "user_friends");

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                requestFriends();

            }

            @Override
            public void onCancel() {
                // App code
                Log.i(TAG, "Cancel login action!");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.i(TAG, "Error on Login!", exception);
            }
        });

        if(AccessToken.getCurrentAccessToken() != null) {
            requestFriends();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void requestFriends() {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/invitable_friends",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {

                        Gson gson = new Gson();
                        FriendFacebookDTO friendsDTO = gson.fromJson(response.getJSONObject().toString(), FriendFacebookDTO.class);

                        Intent intent = new Intent(LoginActivity.this, FriendsActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("friend_list_dto", friendsDTO);
                        startActivity(intent);
                    }
                }
        ).executeAsync();
    }
}

