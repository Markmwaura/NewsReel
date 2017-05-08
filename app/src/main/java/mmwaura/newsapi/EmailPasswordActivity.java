package mmwaura.newsapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by mark on 4/23/17.
 */

public class EmailPasswordActivity extends BaseActivity implements View.OnClickListener {


    private static final String TAG = "EmailPasswordActivity";

    private TextView mStatusTextView;
    private TextView mDetailTextView;
    private EditText emailField;
    private EditText passwordField;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emailpassword_activity);

        // Views
        emailField = (EditText) findViewById(R.id.field_email);
        passwordField = (EditText) findViewById(R.id.field_password);

        // Buttons
        findViewById(R.id.email_sign_in_button).setOnClickListener(this);
        findViewById(R.id.email_create_account_button).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.verify_email_button).setOnClickListener(this);

        //initialize auth
        auth = FirebaseAuth.getInstance();
    }

    private void signIn(String email, String password) {

        if (!validateInputs()) {
            return;
        }

        showProgressDialog();


        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            Intent i = new Intent(EmailPasswordActivity.this, MainActivity.class);
                            startActivity(i);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                        hideProgressDialog();

                    }
                });

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.email_create_account_button) {
            createAccount(emailField.getText().toString(), passwordField.getText().toString());
        } else if (i == R.id.email_sign_in_button) {
            signIn(emailField.getText().toString(), passwordField.getText().toString());
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in
        FirebaseUser currentUser = auth.getCurrentUser();

    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateInputs()) {
            return;
        }

        showProgressDialog();

        // Create User with the email
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            Intent i = new Intent(EmailPasswordActivity.this, MainActivity.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }


                        hideProgressDialog();
                    }
                });

    }


    private boolean validateInputs() {
        boolean valid = true;

        String email = emailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailField.setError("Required.");
            valid = false;
        } else {
            emailField.setError(null);
        }

        String password = passwordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordField.setError("Required.");
            valid = false;
        } else {
            passwordField.setError(null);
        }

        return valid;

    }
}
