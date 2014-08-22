package com.example.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.adapters.AccountAdapter;
import com.example.budgettracker.R;
import com.example.models.AccountModel;
import com.example.utilities.BudgetTrackerContract.AccountEntry;
import com.example.utilities.DatabaseUtility;
import com.example.utilities.SwipeDetectorUtility;

public class AccountListFragment extends Fragment {

	Context context;
	ListView view;
	private final SwipeDetectorUtility swipeDetector = new SwipeDetectorUtility();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = (ListView) inflater.inflate(R.layout.account_list_fragment, container,
				false);
		view.setOnTouchListener(swipeDetector);
		setOnClickListener();
		return view;
	}

	private void setOnClickListener(){
		view.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				if (swipeDetector.swipeDetected()){
					deleteItemFromList(position);
				}else{
					displayClickedItem(position);
				}
			}

			
		});
		
	}
	
	private void displayClickedItem(int position) {
		AccountAdapter adapter = (AccountAdapter) view.getAdapter();
		AccountModel model = adapter.getItem(position);
		
	}
	
	private void deleteItemFromList(int position){
		AccountAdapter adapter = (AccountAdapter) view.getAdapter();
		AccountModel item = adapter.getItem(position);
		adapter.remove(item);
		removeItemFromDatabase(item.getAccountName());
		
		adapter.notifyDataSetChanged();
		view.refreshDrawableState();
	}
	
	public void refreshList(AccountModel account) {
		AccountAdapter adapter = (AccountAdapter) view.getAdapter();
		adapter.add(account);

		adapter.notifyDataSetChanged();
		view.refreshDrawableState();
	}

	private void removeItemFromDatabase(String name){
		DatabaseUtility dbu = new DatabaseUtility(view.getContext());
		SQLiteDatabase db = dbu.getReadableDatabase();
		db.execSQL("DELETE from " + AccountEntry.TABLE_NAME +
				" WHERE " + AccountEntry.COLUMN_ACCOUNT_NAME + "='" + name +"'");
	}
	
	public void populateAccountList() {
		String accountName;
		double balance;

		DatabaseUtility dbu = new DatabaseUtility(view.getContext());
		SQLiteDatabase db = dbu.getReadableDatabase();
		Cursor table = db.query(AccountEntry.TABLE_NAME, null, null, null,
				null, null, AccountEntry.COLUMN_ACCOUNT_NAME + " ASC");

		int anyRows = table.getCount();
		ArrayList<AccountModel> accounts = new ArrayList<AccountModel>();

		if (anyRows > 0) {
			int nameIndex, balanceIndex;
			table.moveToFirst();
			for (int i = 0; i < table.getCount(); i++) {
				nameIndex = table
						.getColumnIndex(AccountEntry.COLUMN_ACCOUNT_NAME);
				balanceIndex = table
						.getColumnIndex(AccountEntry.COLUMN_BALANCE);

				accountName = table.getString(nameIndex);
				balance = table.getDouble(balanceIndex);

				AccountModel account = new AccountModel(balance, accountName);
				accounts.add(account);
				table.moveToNext();
			}
			AccountAdapter adapter = new AccountAdapter(view.getContext(),
					R.layout.account_list_items, accounts);
			view.setAdapter(adapter);
			table.close();
			db.close();
		}
		
	}

}
