package com.example.fragments;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.budgettracker.R;
import com.example.models.AccountModel;
import com.example.utilities.BudgetTrackerContract.AccountEntry;
import com.example.utilities.DatabaseUtility;

public class CreateNewAccountFragment extends Fragment {
	
	View view;
	String accountName;
	Double accountBalance;
	
	OnAccountModelSavedListener accountSavedCallback;
	
	public interface OnAccountModelSavedListener{
		public void onAccountSaved(AccountModel account);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIsnatcesState){
		view = inflater.inflate(R.layout.create_new_account_fragment, container, false);
		setViewListeners();
		return view;
	}
	
	private void setViewListeners() {
		Button button = (Button) view.findViewById(R.id.save_button);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				onSaveAccount();
			}
		});
	}

	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		
		accountSavedCallback = (OnAccountModelSavedListener) activity;
	}
	
	public void onSaveAccount(){
		EditText editText = (EditText) view.findViewById(R.id.account_name);
		accountName = editText.getText().toString();
		editText.setText("");
		editText = (EditText) view.findViewById(R.id.starting_balance);
		String temp = editText.getText().toString();
		editText.setText("");
		accountBalance = Double.parseDouble(temp);

		AccountModel model = new AccountModel(accountBalance, accountName);
		DatabaseUtility dbHelper = new DatabaseUtility(view.getContext());
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(AccountEntry.COLUMN_ACCOUNT_NAME, model.getAccountName());
		values.put(AccountEntry.COLUMN_BALANCE, model.getCurrentBalance());
		double newRowId;
		newRowId = db.insert(AccountEntry.TABLE_NAME, null ,values);
		
		accountSavedCallback.onAccountSaved(model);
	} 
}
