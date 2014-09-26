package com.pegpop.createProfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.pegpop.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UploadProfilePicture extends ActionBarActivity {
	
	private ImageButton nextButton;
	private Button uploadPictureButton;
	
	private final int REQUEST_IMAGE_CAPTURE = 1;
//	private final int REQUEST_CAMERA = 1;
	private final int SELECT_FILE = 2;
	
	private String TAG = "UploadProfilePicture";
	
	private ImageView mImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_profile);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setCustomView(R.layout.actionbar_create_profile);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
				| ActionBar.DISPLAY_SHOW_CUSTOM); 
		setCustomTitle("Create Profile");
		
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff78CACA));

		nextButton = (ImageButton) findViewById(R.id.next);
		nextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplication(), TermsAndConditions.class);
				startActivity(i);
			}
		});
	}

	private void setCustomTitle(String title) {
		TextView textViewTitle = (TextView) findViewById(R.id.profilePitcureTitle);
		textViewTitle.setText(title);
	}
	
	public void selectPicture(View view) {
		Log.i(TAG, "upload picture button pressed"); 
		selectPicture();
	}
	
	private void selectPicture() {
		final CharSequence[] items = {"Take photo", "Gallery", "Facebook", "Twitter", "Instagram"};
		AlertDialog.Builder builder = new AlertDialog.Builder(UploadProfilePicture.this);
		builder.setItems(items, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int item) {
				if(items[item].equals("Take photo")) {
					dispatchTakePictureIntent();
					dialog.dismiss();
				}
				if(items[item].equals("Gallery")) {
					Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
				}
			}
		});
		builder.show();
	}
	
	private void dispatchTakePictureIntent() {
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
	        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
	    }
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
	        Bundle extras = data.getExtras();
	        Bitmap imageBitmap = (Bitmap) extras.get("data");
	        mImageView = (ImageView) findViewById(R.id.profilePicture); 
	        mImageView.setImageBitmap(imageBitmap);
	    }
	}

}
