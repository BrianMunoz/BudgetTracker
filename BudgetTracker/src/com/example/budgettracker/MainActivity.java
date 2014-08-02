package com.example.budgettracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.fragments.AccountListFragment;
import com.example.fragments.CreateNewAccountFragment;
import com.example.utilities.BudgetTrackerContract.AccountEntry;
import com.example.utilities.DatabaseUtility;

public class MainActivity extends FragmentActivity {
	
	AccountListFragment accountListFragment;
	CreateNewAccountFragment newAccount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		accountListFragment = new AccountListFragment();
		newAccount = new CreateNewAccountFragment();
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		
		transaction.add(R.id.account_list_frame, accountListFragment);
		transaction.add(R.id.create_new_account_frame, newAccount);
		
		transaction.commit();
		 
	}

	public void onCreateNewAccount(View view) {
		Intent intent = new Intent(this, CreateNewAccountActivity.class);

		startActivity(intent);
	}

	@Override
	public void onStart(){
		super.onStart();
		
		DatabaseUtility dbu = new DatabaseUtility(getBaseContext());
		SQLiteDatabase db = dbu.getReadableDatabase();
		
		Cursor c = db.query(AccountEntry.TABLE_NAME, null, null, null, null,
				null, AccountEntry.COLUMN_ACCOUNT_NAME + " ASC");
		int anyRows = c.getCount();
		if (anyRows > 0) {
			accountListFragment.populateAccountList(c, this);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.main, menu);
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

}
