package com.morksoftware.keytest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		ViewPager vp = (ViewPager) findViewById(R.id.viewpager);
		
		FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
		
		vp.setAdapter(adapter);
	}
	
	private class FragmentAdapter extends FragmentPagerAdapter {

		public FragmentAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch(position) {
				case 0:
					return FirstFragment.newInstance();
				case 1:
					return SecondFragment.newInstance();
				default:
					return FirstFragment.newInstance();
			}
		}

		@Override
		public int getCount() {
			return 2;
		}
		
	}
}
