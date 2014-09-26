package com.pegpop.createProfile;

import com.pegpop.R;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class TermsAndConditions extends ActionBarActivity {
	
private ImageButton nextButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.terms_and_conditions);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setCustomView(R.layout.actionbar_t_and_c);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
				| ActionBar.DISPLAY_SHOW_CUSTOM); 
		setCustomTitle("Terms and Conditions");
		
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff78CACA));

		nextButton = (ImageButton) findViewById(R.id.next);
		nextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
//				Intent i = new Intent(getApplication(), AddFriends.class);
//				startActivity(i);
			}
		});
	}

	private void setCustomTitle(String title) {
		TextView textViewTitle = (TextView) findViewById(R.id.termsAndConditions);
		textViewTitle.setText(title);
	}
	
	public void readTermsAndConditions(View view) {
		Intent i = new Intent(getApplication(), ReadTermsAndConditions.class);
		startActivity(i);
	}

}
