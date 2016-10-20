package com.unifam.heartpatrol.register.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

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
import com.unifam.heartpatrol.AppConstant;
import com.unifam.heartpatrol.AppController;
import com.unifam.heartpatrol.MainMenu;
import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.model.Register;
import com.unifam.heartpatrol.model.net.NetworkManager;
import com.unifam.heartpatrol.register.RegisterActivity;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Unifam on 9/15/2016.
 */
public class Frag_Register_With extends Fragment implements GoogleApiClient.OnConnectionFailedListener{
    private static final int RC_SIGN_IN = 9001;
    private TextView btnFacebook, btnLoginGoogle;
    private EditText txtEmail;
    private SignInButton btnGoogle;
    private GoogleApiClient mGoogleApiClient;
    String TAG = "Notifikasi";
    TextView textRegister;
    Register register;
    ProgressDialog progress;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the view from fragmenttab1.xml

        View view = inflater.inflate(R.layout.frag_register_with, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        InitControl(view);

    }

    void InitControl(View v){
        txtEmail = (EditText) v.findViewById(R.id.nav_login_email_edit_text);
        textRegister = (TextView)v.findViewById(R.id.register_button);
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstant.AUTH_USERNAME = txtEmail.getText().toString();
                if (!AppConstant.AUTH_USERNAME.equals("")){
                    ((RegisterActivity)getActivity()).displayView(1);
                }else{
                    AppController.getInstance().CustomeDialog(getActivity(),"Email Address is Empty, Try Again !");
                }
            }
        });

        btnGoogle = (SignInButton)v.findViewById(R.id.google_sign_in_button);
        btnLoginGoogle = (TextView) v.findViewById(R.id.btn_login_google);
        btnLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        try{
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                    .requestEmail()
                    .build();
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .enableAutoManage(getActivity(), this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .addApi(Plus.API)
                    .build();

            //signOut();
            btnGoogle.setSize(SignInButton.SIZE_STANDARD);
            btnGoogle.setScopes(gso.getScopeArray());
            btnGoogle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                }
            });
        }catch (Exception e){

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
            try{
                progress = ProgressDialog.show(getActivity(), "Information",
                        "Registration", true);
                progress.show();

                Call<Register> call = NetworkManager.getNetworkService(getActivity()).getRegister(
                        email,
                        "google",
                        email,
                        personName,
                        personName);
                call.enqueue(new Callback<Register>() {
                    @Override
                    public void onResponse(Call<Register> call, Response<Register> response) {
                        int code = response.code();
                        register = response.body();
                        progress.dismiss();
                        if (code == 200){
                            if (!register.error){
                                AppController.getInstance().getSessionManager().setUserAccount(register);
                                getActivity().finish();
                                Intent intent = new Intent(getActivity(), MainMenu.class);
                                startActivity(intent);
                            }else{
                                AppController.getInstance().CustomeDialog(getActivity(),register.message);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Register> call, Throwable t) {
                        progress.dismiss();
                    }
                });
            }catch (Exception e){
                AppController.getInstance().CustomeDialog(getActivity(), e.getMessage());
            }

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
}
