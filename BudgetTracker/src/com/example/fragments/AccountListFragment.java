package com.example.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapters.AccountAdapter;
import com.example.budgettracker.R;
import com.example.models.AccountModel;
import com.example.utilities.BudgetTrackerContract.AccountEntry;

public class AccountListFragment extends Fragment {

	
	Context context;
	View view;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		view = inflater.inflate(R.layout.account_list_fragment, container, false);

		return view;
	}

	public void populateAccountList(Cursor table, Context context) {
		String accountName;
		double balance;
		ArrayList<AccountModel> accounts = new ArrayList<AccountModel>();
		int nameIndex, balanceIndex;
		ListView list = (ListView) view;
		table.moveToFirst();
		for (int i = 0; i < table.getCount(); i++) {
			nameIndex = table.getColumnIndex(AccountEntry.COLUMN_ACCOUNT_NAME);
			balanceIndex = table.getColumnIndex(AccountEntry.COLUMN_BALANCE);

			accountName = table.getString(nameIndex);
			balance = table.getDouble(balanceIndex);

			AccountModel account = new AccountModel(balance, accountName);
			accounts.add(account);
			table.moveToNext();
		}
		AccountAdapter adapter = new AccountAdapter(context,R.layout.account_list_items, accounts);
		list.setAdapter(adapter);

	}
}