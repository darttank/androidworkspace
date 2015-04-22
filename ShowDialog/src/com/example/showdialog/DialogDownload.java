package com.example.showdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class DialogDownload extends Dialog {
	
	Context context;

	public DialogDownload(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	public DialogDownload(Context context, int theme){
        super(context, theme);
        this.context = context;
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.dialog_download);
		
	}

}
