package com.tianfan.control;

import java.util.List;

import com.tianfan.bean.FileItem;
import com.tianfan.moneylife.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;



public class FileItemAdapter extends BaseAdapter {

	private List<FileItem> files;
	private LayoutInflater inflater;
	private OnClickListener listener;

	public FileItemAdapter(Context context, List<FileItem> files, OnClickListener listener) {
		inflater = LayoutInflater.from(context);
		this.files = files;
		this.listener = listener;
	}

	@Override
	public int getCount() {
		return files.size();
	}

	@Override
	public Object getItem(int position) {
		return files.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.file_item, parent, false);
			holder = new ViewHolder();
			holder.iv_fileIcon = (ImageView) convertView.findViewById(R.id.iv_file_icon);
			holder.tv_fileName = (TextView) convertView.findViewById(R.id.tv_file_name);
			holder.tv_fileMsg = (TextView) convertView.findViewById(R.id.tv_file_msg);
			holder.ibtn_fileOperate = (ImageButton) convertView.findViewById(R.id.ibtn_file_operate);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		FileItem item = files.get(position);
		holder.iv_fileIcon.setImageResource(item.fileIconRes);

		holder.tv_fileName.setText(item.fileName);
		if (item.fileMsg.length() != 0) {
			holder.tv_fileMsg.setVisibility(View.VISIBLE);
			holder.tv_fileMsg.setText(item.fileMsg);
		} else {
			holder.tv_fileMsg.setVisibility(View.GONE);
		}
		holder.ibtn_fileOperate.setOnClickListener(listener);
		return convertView;
	}

	private class ViewHolder {
		ImageView iv_fileIcon;
		TextView tv_fileName;
		TextView tv_fileMsg;
		ImageButton ibtn_fileOperate;
	}
}