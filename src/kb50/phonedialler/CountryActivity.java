package kb50.phonedialler;

import java.util.Locale;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class CountryActivity extends Activity {

	private TextView country;
	private TextView number;
	private String countryNum;
	private String phoneNum;
	private String callNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_country);
		
		//Retrieving the country code and phone number from the intent
		countryNum = getIntent().getStringExtra("tel_country");
		phoneNum = getIntent().getStringExtra("tel_number");
		callNum = countryNum + phoneNum;

		//Displaying the phone number in a textview
		number = (TextView) findViewById(R.id.number1);
		number.setText(callNum);

		//Retrieving and displaying country name in textview
		country = (TextView) findViewById(R.id.country1);
		country.setText(getCountry("+" + callNum));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.country, menu);
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

	private String getCountry(String phoneNumber){
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		String country = "";
		try {
			PhoneNumber telNummer = phoneUtil.parse(phoneNumber, "");
			int countryCode = telNummer.getCountryCode();
			if(!phoneUtil.isValidNumber(telNummer)){
				country = "Nummer is niet geldig!";
			}else{
				Locale l = new Locale("",
						phoneUtil.getRegionCodeForNumber(telNummer));
				country = "Country - " + l.getDisplayCountry()
						+ System.getProperty("line.separator") + "Code -  +"
						+ countryCode;
			}
		}catch(NumberParseException e){
			country = "The phone number you entered isn't valid. Try again.";
		}
		return country;
	}

	public void countryOnClick(View v){
		try{
			switch (v.getId()) {
				case R.id.countryButtonBack:
					Intent back = new Intent();
					back.putExtra("backValue", callNum);
					setResult(1, back);
					finish();
					break;
				case R.id.countryButtonCall:
					Intent call = new Intent(Intent.ACTION_CALL);
					call.setData(Uri.parse("tel:" + "+"+callNum));
					startActivity(call);
					break;
			}
		}
		catch(IllegalStateException e){
			System.out.println(e);
		}
	}
}
