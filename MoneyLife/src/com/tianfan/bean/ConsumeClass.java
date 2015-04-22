package com.tianfan.bean;

import java.util.Date;


import android.content.Context;

public class ConsumeClass extends TradeClass{


	public ConsumeClass(int id, float money, String time, String mark,
			String packageType, Context context) {
		super(id, money, time, mark, packageType, context);
		// TODO Auto-generated constructor stub
	}
	public void trade_add(){
		super.settablename("tb_outaccount");
		super.trade_add();
	}
	public void trade_modify(){
		
	}
	public int trade_delect(int id){
		super.settablename("tb_outaccount");
		return super.trade_delect(id);
	}
}
