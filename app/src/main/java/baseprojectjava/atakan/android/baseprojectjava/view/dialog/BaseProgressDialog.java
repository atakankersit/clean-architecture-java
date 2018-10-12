package baseprojectjava.atakan.android.baseprojectjava.view.dialog;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import baseprojectjava.atakan.android.baseprojectjava.R;

public class BaseProgressDialog {
    private AlertDialog progressDialog;


    public static BaseProgressDialog getDialog(){
        return new BaseProgressDialog();
    }

    public void show(Context c) {
        if (progressDialog ==null){
            progressDialog = ProgressDialog.show(c, "", "");
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.setContentView(R.layout.loading);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    public void dismiss() {
        if (progressDialog==null||!progressDialog.isShowing()) {
            return;
        }
        progressDialog.dismiss();
    }

    private BaseProgressDialog(){}

}
