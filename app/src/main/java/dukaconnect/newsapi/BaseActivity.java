package dukaconnect.newsapi;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by mark on 4/23/17.
 */

public class BaseActivity extends AppCompatActivity{

    public ProgressDialog progressDialog;
    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading");
            progressDialog.setIndeterminate(true);
        }

        progressDialog.show();
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }
}
