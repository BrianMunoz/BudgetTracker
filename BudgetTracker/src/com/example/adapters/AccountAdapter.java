package com.example.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.budgettracker.R;
import com.example.models.AccountModel;

public class AccountAdapter extends ArrayAdapter<AccountModel> {
	int resource;
	String response;
	Context context;
	
	public AccountAdapter(Context context, int resource, List<AccountModel> items){
		super(context, resource, items);
		this.resource = resource;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LinearLayout accountView = null;
		
		AccountModel account = getItem(position);
		
		if (convertView == null){
			accountView = new LinearLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater view;
			view = (LayoutInflater)getContext().getSystemService(inflater);
			view.inflate(resource, accountView, true);
		}
		else{
			accountView = (LinearLayout) convertView;
		}
		
		TextView accountName = (TextView)accountView.findViewById(R.id.accountName);
		TextView accountBalance = (TextView)accountView.findViewById(R.id.accountBalance);
		
		accountName.setText(account.getAccountName());
		accountBalance.setText(String.valueOf(account.getCurrentBalance()));
		
		return accountView;
	}
}
