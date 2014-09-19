package com.pegpop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pegpop.fragment.MainFragment;

public class MainPageActivity extends ActionBarActivity{
	
	public final static String EXTRA_MESSAGE = "com.pegpop.MESSAGE";
	private MainFragment mainFragment;
	ImageButton settingsButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.fragment_main); 
	    ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(false);
	    actionBar.setCustomView(R.layout.actionbar_top); 
	    actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_CUSTOM); //show it 
	    setCustomTitle("Home");
	    
	    settingsButton = (ImageButton) findViewById(R.id.settings);
	    settingsButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				PopupMenu popupMenu = new PopupMenu(MainPageActivity.this, settingsButton);
				popupMenu.getMenuInflater().inflate(R.menu.settings_popup_menu, popupMenu.getMenu());
				popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
					
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						switch(item.getItemId()) {
						case R.id.settings:
							return true;
						case R.id.logout:
							return true;
						default:
							return false;
						}
					}
				});
				popupMenu.show();
			}
	    });
	}
	
	private void setCustomTitle(String title)
	{
		TextView textViewTitle = (TextView) findViewById(R.id.myText);
		textViewTitle.setText(title);
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		
        return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.action_search:
			//Search action
			return true;
		case R.id.action_settings:
            return true;
		default:
            return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	//View as the only parameter (it will be the view that was clicked)
	public void sendMessage(View view) {
		/*Intent provides run time binding between separate components
		 * Intent is the app's intent to do something - usually used to start another activity
		 * Intent can carry data to the next activity
		 * this - activity is a subclass of Context
		*/
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}

}