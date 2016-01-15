package com.clone.demo.shyp;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.widget.Toast;

public class PublicDelegate {

	Context objContext;

	public PublicDelegate(Context context) {

		this.objContext = context;		

	}

    public Bitmap base64ToImage(String strBase64) {

        byte[] decodedString = Base64.decode(strBase64, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        return decodedByte;

    }// end base64ToImage

	// Many more lines of awesomeness.

	public final static boolean isValidEmail(CharSequence target) {
		if (target == null) {
			return false;
		} else {
			return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
		}
	}


	// programming mark --------- ---------- ----------- --------- ------------

	/**	
	 * Show alert dialog	
	 * */
	public void showAlertDialog(String title, String message) {

		// Create an object of alert class for an activity
		AlertDialog alertDialog = new AlertDialog.Builder(objContext).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting Icon to Dialog
		alertDialog.setIcon(R.drawable.ic_launcher);

		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		alertDialog.show();
	}// end of alert dialog showAlertDialog


	public boolean checkNetworkStatus() {

		ConnectivityManager connectivity = (ConnectivityManager)
				objContext.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {

						return true;
					}
				}
			}
		}

		return false;

	}// end isNetworkAvailable

	public void showToast(String text){

		Toast.makeText(objContext, text, Toast.LENGTH_LONG).show();


	}// end showToast


}// end PublicDelegate
