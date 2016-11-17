package com.unifam.heartpatrol;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;
import com.unifam.heartpatrol.model.Register;
import com.unifam.heartpatrol.model.net.NetworkManager;
import com.unifam.heartpatrol.register.ListUserActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Unifam on 9/14/2016.
 */
public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    private static final int RC_SIGN_IN = 9001;
    Toolbar toolbar;
    ImageView imgBack, imgUser;
    TextView txtLabel;

    EditText txtEmail, txtPassword;
    private TextView btnFacebook, btnLoginGoogle, btnLogin;
    private SignInButton btnGoogle;
    private GoogleApiClient mGoogleApiClient;
    String TAG = "Notifikasi";

    Register register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppConstant.USER_FROM_LIST = "";
        setContentView(R.layout.activity_login);
        imgUser = (ImageView)findViewById(R.id.icon5);
        toolbar = (Toolbar)findViewById(R.id.tool_bar);
        txtEmail = (EditText)findViewById(R.id.nav_login_email_edit_text);
        txtPassword = (EditText)findViewById(R.id.nav_login_password_edit_text);
        btnLogin = (TextView)findViewById(R.id.nav_login_button);
        imgBack = (ImageView)findViewById(R.id.arrow_back);
        txtLabel = (TextView)findViewById(R.id.textLabel);
        btnGoogle = (SignInButton)findViewById(R.id.google_sign_in_button);
        txtLabel.setText(getResources().getText(R.string.Login));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnLoginGoogle = (TextView) findViewById(R.id.btn_login_google);
        btnLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);*/
                AppConstant.AUTH_USERNAME = "Ronald";
                Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                startActivity(intent);
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(Plus.API)
                .build();

        btnGoogle.setSize(SignInButton.SIZE_STANDARD);
        btnGoogle.setScopes(gso.getScopeArray());
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);*/
                AppConstant.AUTH_USERNAME = "Ronald";
                Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sName = txtEmail.getText().toString().trim();
                AppConstant.AUTH_USERNAME = sName;
                Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                startActivity(intent);
                //LoginEmail();
            }
        });

        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FillGrid();
            }
        });
    }

    void FillGrid(){
        final ProgressDialog progress = ProgressDialog.show(this,"Information","Get data", true);
        try{
            Call<Register> call = NetworkManager.getNetworkService(this).getListUser(AppConstant.DEVICE_ID);
            call.enqueue(new Callback<Register>() {
                @Override
                public void onResponse(Call<Register> call, Response<Register> response) {
                    int code = response.code();
                    progress.dismiss();
                    if (code == 200){
                        register = response.body();
                        if (!register.error){
                            AppConstant.register = register;
                            Intent mIntent = new Intent(getBaseContext(), ListUserActivity.class);
                            startActivity(mIntent);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Register> call, Throwable t) {
                    progress.dismiss();
                }
            });
        }catch (Exception e){
            progress.dismiss();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        //updateUI(false);
                    }
                });
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();
            //String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();

            signOut();
            LoginSocmed(email);
        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    public void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                    }
                });
    }

    void LoginEmail(){
        try{
            Call<Register> call = NetworkManager.getNetworkService(this).getLogin(
                    txtEmail.getText().toString().trim(),txtPassword.getText().toString(),
                    AppConstant.DEVICE_ID,
                    AppConstant.TOKEN);
            call.enqueue(new Callback<Register>() {
                @Override
                public void onResponse(Call<Register> call, Response<Register> response) {
                    int code = response.code();
                    if (code == 200){
                        register = response.body();
                        if (!register.error){
                            AppController.getInstance().getSessionManager().setUserAccount(register);
                            finish();
                            Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                            startActivity(intent);
                        }else{
                            AppController.getInstance().CustomeDialog(LoginActivity.this,register.message);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Register> call, Throwable t) {

                }
            });
        }catch (Exception e){

        }
    }

    void LoginSocmed(String sEmail){
        try{
            Call<Register> call = NetworkManager.getNetworkService(this).getLoginSocMed(
                    sEmail);
            call.enqueue(new Callback<Register>() {
                @Override
                public void onResponse(Call<Register> call, Response<Register> response) {
                    int code = response.code();
                    if (code == 200){
                        register = response.body();
                        if (!register.error){
                            AppController.getInstance().getSessionManager().setUserAccount(register);
                            finish();
                            Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                            startActivity(intent);
                        }else{
                            AppController.getInstance().CustomeDialog(LoginActivity.this,register.message);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Register> call, Throwable t) {

                }
            });
        }catch (Exception e){

        }
    }
    @Override
    public void onResume(){
        super.onResume();

        txtEmail.setText(AppConstant.USER_FROM_LIST);
    }
}
