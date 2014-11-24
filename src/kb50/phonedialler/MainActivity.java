package kb50.phonedialler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.Selection;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText phoneFieldCountry;
	private EditText phoneFieldNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Initialize global variables 
		phoneFieldCountry = (EditText) findViewById(R.id.phone_input_country);
		phoneFieldNumber = (EditText) findViewById(R.id.phone_input_number);
		
		//Disable soft keyboard
		phoneFieldCountry.setRawInputType(InputType.TYPE_CLASS_TEXT);
		phoneFieldCountry.setTextIsSelectable(true);
		
		phoneFieldNumber.setRawInputType(InputType.TYPE_CLASS_TEXT);
		phoneFieldNumber.setTextIsSelectable(true);		
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		phoneFieldNumber = (EditText) findViewById(R.id.phone_input_number);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.show_country:
			Intent i = new Intent("kb50.PhoneDailer.CountryActivity");
			
			String countryCode = phoneFieldCountry.getText().toString();			
			String phoneNumber = phoneFieldNumber.getText().toString();
			
			if (countryCode.startsWith("00")) {
				countryCode = countryCode.substring(2);
			}
			
			i.putExtra("tel_country", countryCode);
			i.putExtra("tel_number", phoneNumber);
			
			startActivityForResult(i, 1);
			break;
		case R.id.btn_1:
			place_text("1");
			break;
		case R.id.btn_2:
			place_text("2");
			break;
		case R.id.btn_3:
			place_text("3");
			break;
		case R.id.btn_4:
			place_text("4");
			break;
		case R.id.btn_5:
			place_text("5");
			break;
		case R.id.btn_6:
			place_text("6");
			break;
		case R.id.btn_7:
			place_text("7");
			break;
		case R.id.btn_8:
			place_text("8");
			break;
		case R.id.btn_9:
			place_text("9");
			break;
		case R.id.btn_0:
			place_text("0");
			break;
		case R.id.btn_back:
			remove_text();
			break;
		case R.id.btn_ocd:
			Context context = getApplicationContext();
			CharSequence text = "I'm filler...";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			break;
		}
	}

	private void place_text(String number) {
		if (phoneFieldCountry.isFocused()) {
			phoneFieldCountry.setText(phoneFieldCountry.getText() + number);
			int position = phoneFieldCountry.length();
			Selection.setSelection(phoneFieldCountry.getText(), position);
		} else if (phoneFieldNumber.isFocused()) {
			phoneFieldNumber.setText(phoneFieldNumber.getText() + number);
			int position = phoneFieldNumber.length();
			Selection.setSelection(phoneFieldNumber.getText(), position);
		} else {
			Context context = getApplicationContext();
			CharSequence text = "Select a field please";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
	}

	private void remove_text() {
		if (phoneFieldCountry.isFocused()) {
			String phrase = phoneFieldCountry.getText().toString();
			String rephrase = "";

			if (phrase.length() >= 1) {
				rephrase = phrase.substring(0, phrase.length() - 1);
				phoneFieldCountry.setText(rephrase);
			}
			int position = phoneFieldCountry.length();
			Selection.setSelection(phoneFieldCountry.getText(), position);
		} else if (phoneFieldNumber.isFocused()) {
			String phrase = phoneFieldNumber.getText().toString();
			String rephrase = "";

			if (phrase.length() >= 1) {
				rephrase = phrase.substring(0, phrase.length() - 1);
				phoneFieldNumber.setText(rephrase);
			}
			int position = phoneFieldNumber.length();
			Selection.setSelection(phoneFieldNumber.getText(), position);
		} else {
			Context context = getApplicationContext();
			CharSequence text = "Select a field please";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
	}
}
