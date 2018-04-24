package com.ju.saqib.juwf;

import android.animation.Animator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    EditText lEmail, lPassword;
    Button lButton;
    TextView forgotPassword;
    GoogleSignInAccount signInAccount;

    EditText pEmail, pPassword, pConfirmPassword, pName;
    Button pButton;

    EditText fEmail;
    Button fButton;
    TextView createAccount;
    LinearLayout Login, Signup, ForgotPassword;
    Animation uptodown,downtoup;
    static FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    static FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;
    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
    // EMAIL_ADDRESS_PATTERN.matcher(Edittext).matches()




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        createAccount = (TextView) findViewById(R.id.createAccount);
        Login = (LinearLayout) findViewById(R.id.Login);
        Signup = (LinearLayout) findViewById(R.id.Signup);
        ForgotPassword = (LinearLayout) findViewById(R.id.Forgotpassword);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        Login.setAnimation(uptodown);

        sharedPreferences = getApplication().getSharedPreferences("data", Context.MODE_PRIVATE);


        //login
        lEmail = (EditText) findViewById(R.id.lEmail);
        lPassword = (EditText) findViewById(R.id.lPassword);
        lButton = (Button) findViewById(R.id.lButton);
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        //register
        pEmail = (EditText) findViewById(R.id.pEmail);
        pPassword = (EditText) findViewById(R.id.pPassword);
        pConfirmPassword = (EditText) findViewById(R.id.pConfirmpassword);
        pName = (EditText) findViewById(R.id.pName);
        pButton = (Button) findViewById(R.id.pButton);

        fEmail = (EditText) findViewById(R.id.fEmail);
        fButton = (Button) findViewById(R.id.fButton);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.setVisibility(View.GONE);
                Signup.setVisibility(View.VISIBLE);
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgotPassword.setVisibility(View.VISIBLE);
                Login.setVisibility(View.GONE);
                Signup.setVisibility(View.GONE);
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null)
                {
                    switchActivity(); //
                }
                else
                {
                    Log.d("Auth", "onAuthStateChanged:signed_out");
                    Login.setVisibility(View.VISIBLE);
                    Signup.setVisibility(View.GONE);
                }
            }
        };
        pButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmail = pEmail.getText().toString();
                String sPassword = pPassword.getText().toString();
                String sCOnformPassword = pConfirmPassword.getText().toString();

                if (pName.getText().toString().equals("")) {
                    Toast.makeText(Login.this, R.string.enter_name, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!EMAIL_ADDRESS_PATTERN.matcher(sEmail).matches()) {
                    Toast.makeText(Login.this, R.string.enter_id, Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("emaillogin", sEmail);
                editor.commit();
                if (sPassword.length() < 6) {
                    Toast.makeText(Login.this, R.string.password_6char, Toast.LENGTH_LONG).show();
                    return;
                }
                if (!sPassword.equals(sCOnformPassword)) {
                    Toast.makeText(Login.this, R.string.password_dont_match, Toast.LENGTH_SHORT).show();
                    return;
                }
                final ProgressDialog progressDialog = new ProgressDialog(Login.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage(getResources().getString(R.string.creating_user));
                progressDialog.show();



                mAuth.createUserWithEmailAndPassword(sEmail, sPassword)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //       Toast.makeText(LoginSignup.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_LONG).show();

                                if (task.isSuccessful()) {
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            writeNewUser(mDatabase, pName.getText().toString(), pEmail.getText().toString(), new Date().getTime());

                                        }
                                    }, 1);

//                                    Intent i = new Intent(LoginSignup.this, calender.class);
//                                    startActivity(i);
                                    Toast.makeText(Login.this, "You Have Registered Succesfully", Toast.LENGTH_SHORT).show();

                                    progressDialog.dismiss();
                                    //finish();
                                }

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Login.this, R.string.auth_failed, Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }

                                // ...
                            }
                        });
            }
        });

        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmail = fEmail.getText().toString();
                if (TextUtils.isEmpty(sEmail)) {
                    fEmail.setError(R.string.login_signup_enter_registered_id+"");
                    return;
                }
                final ProgressDialog progressDialog = new ProgressDialog(Login.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage(getString(R.string.sending_email));
                progressDialog.show();
                mAuth.sendPasswordResetEmail(sEmail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    progressDialog.dismiss();
                                    Toast.makeText(Login.this, R.string.instruction_sent, Toast.LENGTH_SHORT).show();
                                    ForgotPassword.setVisibility(View.GONE);
                                    Login.setVisibility(View.VISIBLE);

                                } else {
                                    Toast.makeText(Login.this, R.string.failed_to_send_instructions, Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }
                        });
            }
        });
        lButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmail = lEmail.getText().toString();
                String sPassword = lPassword.getText().toString();
                final String TAG = "SIGNIN";

                if (!EMAIL_ADDRESS_PATTERN.matcher(sEmail).matches()) {
                    Toast.makeText(Login.this, R.string.enter_id, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sPassword.length() < 6) {
                    Toast.makeText(Login.this, R.string.password_incorrect, Toast.LENGTH_LONG).show();
                    return;
                }


                final ProgressDialog progressDialog = new ProgressDialog(Login.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage(getResources().getString(R.string.authenticating));
                if (!progressDialog.isShowing()) {
                    progressDialog.show();
                }
                mAuth.signInWithEmailAndPassword(sEmail, sPassword)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                                if (task.isSuccessful()) {
                                    String email=mAuth.getCurrentUser().getEmail();
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("emaillogin", email);
                                    editor.commit();
                                    switchActivity();
//                                    Intent i = new Intent(LoginSignup.this, calender.class);
//                                    startActivity(i);
//                                    finish();
                                }
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "signInWithEmail:failed", task.getException());
                                    Toast.makeText(Login.this, R.string.auth_failed,
                                            Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }
                        });
            }
        });

    }


    public static void writeNewUser(DatabaseReference databaseReference, String displayName, String email, long createdAt)
    {
        users user = new users(displayName, email, createdAt);
        FirebaseUser fuserid = mAuth.getCurrentUser();
        databaseReference.child("Users").child(fuserid.getUid()).setValue(user);
    }
    public void switchActivity()
    {
        Intent i = new Intent(Login.this, MainActivity.class);
        i.putExtra("type", "default");
        startActivity(i);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    @Override
    public void onBackPressed() {
        if (Signup.getVisibility() == View.VISIBLE)
        {
            Signup.setVisibility(View.GONE);
            Login.setVisibility(View.VISIBLE);
        }
        else if (ForgotPassword.getVisibility() == View.VISIBLE)
        {
            ForgotPassword.setVisibility(View.GONE);
            Login.setVisibility(View.VISIBLE);
        }
        else
            super.onBackPressed();
    }
}
