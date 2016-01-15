package com.clone.demo.shyp;

import android.app.ProgressDialog;
import android.content.Context;

public class LoadingDialog {

    ProgressDialog dialog;
    Context context;

    public LoadingDialog(Context context) {

        this.context = context;

    }

    public void show(String message) {

        dialog = new ProgressDialog(context) {
            @Override
            public void onBackPressed() {
                dialog.dismiss();
            }
        };

        dialog.setTitle(null);

        dialog.setIndeterminate(true);

        dialog.setMessage(message);

        dialog.setCancelable(false);

        dialog.show();

    }

    public void cancel() {

        dialog.cancel();

    }

}
