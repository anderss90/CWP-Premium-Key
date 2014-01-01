package com.morksoftware.keytest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

public class SecondFragment extends Fragment implements OnClickListener {

	public static SecondFragment newInstance() {
		return new SecondFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View v = null; 
		
		v = inflater.inflate(R.layout.fragment_second, null);	
		
		return v;
	}

	@Override
	public void onClick(View v) {
	}		
}
