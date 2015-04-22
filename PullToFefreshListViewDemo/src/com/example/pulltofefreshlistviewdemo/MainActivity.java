package com.example.pulltofefreshlistviewdemo;


import java.util.Arrays;
import java.util.LinkedList;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.R;
import com.handmark.pulltorefresh.library.extras.SoundPullEventListener;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.text.format.DateUtils;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	private PullToRefreshListView mPullRefreshListView;
	private ArrayAdapter<String> mAdapter;
	private LinkedList<String> mListItems;
	private String[] mStrings = { "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
			"Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
			"Allgauer Emmentaler" };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ptr_list);
		mPullRefreshListView=(PullToRefreshListView) findViewById(R.id.pull_refresh_list);
		// Set a listener to be invoked when the list should be refreshed.
				mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
					@Override
					public void onRefresh(PullToRefreshBase<ListView> refreshView) {
						String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
								DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

						// Update the LastUpdatedLabel
						refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

						// Do work to refresh the list here.
						new GetDataTask().execute();
					}
				});

				// Add an end-of-list listener
				mPullRefreshListView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

					@Override
					public void onLastItemVisible() {
						Toast.makeText(MainActivity.this, "End of List!", Toast.LENGTH_SHORT).show();
					}
				});

				ListView actualListView = mPullRefreshListView.getRefreshableView();

				// Need to use the Actual ListView when registering for Context Menu
				registerForContextMenu(actualListView);

				mListItems = new LinkedList<String>();
				mListItems.addAll(Arrays.asList(mStrings));

				mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);

				/**
				 * Add Sound Event Listener
				 */
				/*SoundPullEventListener<ListView> soundListener = new SoundPullEventListener<ListView>(this);
				soundListener.addSoundEvent(State.PULL_TO_REFRESH, R.raw.pull_event);
				soundListener.addSoundEvent(State.RESET, R.raw.reset_sound);
				soundListener.addSoundEvent(State.REFRESHING, R.raw.refreshing_sound);
				mPullRefreshListView.setOnPullEventListener(soundListener);*/

				// You can also just use setListAdapter(mAdapter) or
				// mPullRefreshListView.setAdapter(mAdapter)
				actualListView.setAdapter(mAdapter);
			}

			private class GetDataTask extends AsyncTask<Void, Void, String[]> {

				@Override
				protected String[] doInBackground(Void... params) {
					// Simulates a background job.
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
					}
					return mStrings;
				}

				@Override
				protected void onPostExecute(String[] result) {
					mListItems.addFirst("Added after refresh...");
					mAdapter.notifyDataSetChanged();

					// Call onRefreshComplete when the list has been refreshed.
					mPullRefreshListView.onRefreshComplete();

					super.onPostExecute(result);
				}
			}
	  
	}

	


