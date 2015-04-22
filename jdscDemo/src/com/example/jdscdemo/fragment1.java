package com.example.jdscdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.jdscdemo.XListView.IXListViewListener;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class fragment1 extends Fragment implements IXListViewListener{
	private View mMainView;
	private TextView tv;
	private Button btn;
	ListView listview;
	//private SimpleAdapter mAdapter;
	private List<HashMap<String, Object>> mHashMaps;
	private HashMap<String, Object> map;
	private XListView mListView;
	private ArrayList<String> items = new ArrayList<String>();
	private ArrayAdapter<String> mAdapter;
	private Handler mHandler;
	private int start = 0;
	private static int refreshCnt = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		geneItems();
		LayoutInflater inflater = getActivity().getLayoutInflater();
		mMainView = inflater.inflate(R.layout.fragment1,
				(ViewGroup) getActivity().findViewById(R.id.viewpager), false);
		/*listview = (ListView) mMainView.findViewById(R.id.listview);
		mAdapter = new SimpleAdapter(getActivity().getApplication(), getData(),
				R.layout.simpleitem, new String[] { "image", "title", "info" },
				new int[] { R.id.img, R.id.title, R.id.info });
		listview.setAdapter(mAdapter);*/
		mListView = (XListView) mMainView.findViewById(R.id.xListView);
		mListView.setPullLoadEnable(true);
		mAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.list_item, items);
		mListView.setAdapter(mAdapter);
		//mListView.setXListViewListener(getActivity().getApplicationContext());
		mHandler = new Handler();
		tv = (TextView) mMainView.findViewById(R.id.tv1);
		btn = (Button) mMainView.findViewById(R.id.btn1);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tv.setText("Hello Viewpager\"");
			}
		});

	}

	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}
	
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				start = ++refreshCnt;
				items.clear();
				geneItems();
				mAdapter.notifyDataSetChanged();
				mAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.list_item, items);
				mListView.setAdapter(mAdapter);
				onLoad();
			}
		}, 2000);
	}
	private void geneItems() {
		for (int i = 0; i != 5; ++i) {
			items.add("refresh cnt " + (++start));
		}
	}

	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				geneItems();
				mAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}

	private List<HashMap<String, Object>> getData() {
		mHashMaps = new ArrayList<HashMap<String, Object>>();
		map = new HashMap<String, Object>();
		map.put("image", R.drawable.ic_launcher);
		map.put("title", "G1");
		map.put("info", "google 1");
		mHashMaps.add(map);

		map = new HashMap<String, Object>();
		map.put("image", R.drawable.ic_launcher);
		map.put("title", "G2");
		map.put("info", "google 2");
		mHashMaps.add(map);

		map = new HashMap<String, Object>();
		map.put("image", R.drawable.ic_launcher);
		map.put("title", "G3");
		map.put("info", "google 3");

		mHashMaps.add(map);
		map = new HashMap<String, Object>();
		map.put("image", R.drawable.ic_launcher);
		map.put("title", "G3");
		map.put("info", "google 3");

		mHashMaps.add(map);
		map = new HashMap<String, Object>();
		map.put("image", R.drawable.ic_launcher);
		map.put("title", "G3");
		map.put("info", "google 3");

		mHashMaps.add(map);
		map = new HashMap<String, Object>();
		map.put("image", R.drawable.ic_launcher);
		map.put("title", "G3");
		map.put("info", "google 3");

		mHashMaps.add(map);
		map = new HashMap<String, Object>();
		map.put("image", R.drawable.ic_launcher);
		map.put("title", "G3");
		map.put("info", "google 3");

		mHashMaps.add(map);
		map = new HashMap<String, Object>();
		map.put("image", R.drawable.ic_launcher);
		map.put("title", "G3");
		map.put("info", "google 3");

		mHashMaps.add(map);
		map = new HashMap<String, Object>();
		map.put("image", R.drawable.ic_launcher);
		map.put("title", "G3");
		map.put("info", "google 3");

		mHashMaps.add(map);
		
		return mHashMaps;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v("huahua", "fragment1-->onCreateView()");

		ViewGroup p = (ViewGroup) mMainView.getParent();
		if (p != null) {
			p.removeAllViewsInLayout();
			Log.v("huahua", "fragment1-->�Ƴ��Ѵ��ڵ�View");
		}

		return mMainView;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.v("huahua", "fragment1-->onDestroy()");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.v("huahua", "fragment1-->onPause()");
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v("huahua", "fragment1-->onResume()");
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.v("huahua", "fragment1-->onStart()");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.v("huahua", "fragment1-->onStop()");
	}

}
