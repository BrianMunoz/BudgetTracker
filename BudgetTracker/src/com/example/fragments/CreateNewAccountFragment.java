package com.example.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.budgettracker.R;

public class CreateNewAccountFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIsnatcesState){
		View view = inflater.inflate(R.layout.create_new_account_fragment, container, false);
		
		return view;
	}
}
