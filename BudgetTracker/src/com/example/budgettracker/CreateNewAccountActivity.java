package com.example.budgettracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.models.AccountModel;
import com.example.utilities.BudgetTrackerContract.AccountEntry;
import com.example.utilities.DatabaseUtility;

public class CreateNewAccountActivity extends ActionBarActivity {
	public static final String MODEL = "com.example.budgettracker.MODEL"; 
	
	private double accountBalance;
	private String accountName;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_new_account);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	public void onSaveAccount(View view) {

		EditText editText = (EditText) findViewById(R.id.account_name);
		accountName = editText.getText().toString();
		editText = (EditText) findViewById(R.id.starting_balance);
		String temp = editText.getText().toString();
		accountBalance = Double.parseDouble(temp);

		AccountModel model = new AccountModel(accountBalance, accountName);
		//TODO: make sure to save the new account to persist the data.
		Intent intent = new Intent(this, DisplayAccountActivity.class);
		intent.putExtra(MODEL, model);
		
		DatabaseUtility dbHelper = new DatabaseUtility(getBaseContext());
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(AccountEntry.COLUMN_ACCOUNT_NAME, model.getAccountName());
		values.put(AccountEntry.COLUMN_BALANCE, model.getCurrentBalance());
		double newRowId;
		newRowId = db.insert(AccountEntry.TABLE_NAME, null ,values);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_new_account, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.create_new_account_fragment, container, false);
			return rootView;
		}
	}

}
