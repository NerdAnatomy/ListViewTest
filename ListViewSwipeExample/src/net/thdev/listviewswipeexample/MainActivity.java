package net.thdev.listviewswipeexample;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity implements SwipeDismissListViewTouchListener.OnDismissCallback {
	
	ListView mListView;
	ArrayAdapter<String> mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView = (ListView)findViewById(R.id.listview);
		
		String[] items = new String[20];
		for(int i=0; i<20; i++) {
			items[i] = "List " + (i + 1);
		}
		
		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, new ArrayList<String>(Arrays.asList(items)));
		mListView.setAdapter(mAdapter);
		
		SwipeDismissListViewTouchListener touchListener = new SwipeDismissListViewTouchListener(mListView, MainActivity.this);
		
		mListView.setOnTouchListener(touchListener);
		mListView.setOnScrollListener(touchListener.makeScrollListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onDismiss(ListView listView, int[] reverseSortedPositions) {
		for (int i=0; i<reverseSortedPositions.length; i++) {
			Log.i("onDismiss", i + " position " + reverseSortedPositions[i]);
			mAdapter.remove(mAdapter.getItem(reverseSortedPositions[i]));
		}
		
		
		mAdapter.notifyDataSetChanged();
		
	}
}

