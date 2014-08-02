package com.example.budgettracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.fragments.AccountListFragment;
import com.example.fragments.CreateNewAccountFragment;
import com.example.models.AccountModel;

public class MainActivity extends FragmentActivity implements CreateNewAccountFragment.OnAccountModelSavedListener{

	AccountListFragment accountListFragment;
	CreateNewAccountFragment newAccount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		accountListFragment = new AccountListFragment();
		newAccount = new CreateNewAccountFragment();

		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		transaction.add(R.id.account_list_frame, accountListFragment);
		transaction.add(R.id.create_new_account_frame, newAccount);

		transaction.commit();

	}

	public void onCreateNewAccount(View view) {
		Intent intent = new Intent(this, CreateNewAccountActivity.class);

		startActivity(intent);
	}

	@Override
	public void onStart() {
		super.onStart();

		accountListFragment.populateAccountList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
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

	@Override
	public void onAccountSaved(AccountModel account) {
		// TODO Auto-generated method stub
		accountListFragment.refreshList(account);
	}

}
