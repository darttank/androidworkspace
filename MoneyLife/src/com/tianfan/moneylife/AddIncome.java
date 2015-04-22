package com.tianfan.moneylife;

import java.util.ArrayList;
import java.util.Calendar;

import com.tianfan.bean.IncomeClass;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class AddIncome  extends Activity{
	private TextView addDate = null;
	private Spinner typeSpinner;
	public String addType="";
	private Button addButton;
	private EditText money;
	private EditText remark;
	DatePickerDialog.OnDateSetListener OnDateSetListener ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_income);
		
		
	    this.typeSpinner = ((Spinner)findViewById(R.id.income_type));
	    ArrayList<String> localArrayList = new ArrayList();
	    localArrayList.add("��������");
	    localArrayList.add("��Ʊ����");
	    localArrayList.add("��������");
	    @SuppressWarnings("unchecked")
		ArrayAdapter<String> localArrayAdapter = new ArrayAdapter(this, R.layout.spinner_item, R.id.spinnerItem, localArrayList);
	    this.typeSpinner.setAdapter(localArrayAdapter);
	    this.typeSpinner.setPrompt("��ѡ����������");
	    this.typeSpinner.setOnItemSelectedListener(new SpinnerSelected());
	    this.addDate = ((TextView)findViewById(R.id.income_addDate));
	    this.addDate.setOnClickListener(new DateOnClick());
	    this.addButton = ((Button)findViewById(R.id.income_addButton));
	    this.addButton.setOnClickListener(new AddPocketClick());
	    this.money = ((EditText)findViewById(R.id.income_money));
	    this.remark=((EditText)findViewById(R.id.edit_remark_income));
	    OnDateSetListener = new DatePickerDialog.OnDateSetListener()
		{
		    public void onDateSet(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3)
		    {
		    	AddIncome.this.addDate.setText(paramInt1 + "-" + (paramInt2 + 1) + "-" + paramInt3);
		    }
		};
    	
    }
protected Dialog onCreateDialog(int paramInt)
	  {
	    Calendar localCalendar = Calendar.getInstance();
	    int i = localCalendar.get(Calendar.YEAR);
	    int j = localCalendar.get(Calendar.MONTH);
	    int k = localCalendar.get(Calendar.DAY_OF_MONTH);
	    switch (paramInt)
	    {
	    default:
	      return null;
	    case 1:
	    }
	    return new DatePickerDialog(this, this.OnDateSetListener, i, j, k);
	  }	
	
//������ʾ
private void dialog()
	  {
	    new AlertDialog.Builder(this).setTitle("���һ��������?").setMessage("�������ͣ�" + this.addType + "\n�����" + this.money.getText().toString() + "\n�������ڣ�" + this.addDate.getText().toString()).setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
	    {
	      public void onClick(DialogInterface paramDialogInterface, int paramInt)
	      {
	    	  AddIncome.this.setResult(-1);
	        //AddEvent.this.addPocket();
	        //ȷ�����
	        IncomeClass trade=new IncomeClass(0, Float.parseFloat(AddIncome.this.money.getText().toString()), 
	        		AddIncome.this.addDate.getText().toString(), remark.getText().toString().trim(), addType, AddIncome.this);
	        
	        trade.trade_add();
	        Toast.makeText(AddIncome.this, "������", 0).show();
	      }
	    }).setNegativeButton("ȡ��", new DialogInterface.OnClickListener()
	    {
	      public void onClick(DialogInterface paramDialogInterface, int paramInt)
	      {
	      }
	    }).show();
	  }


class AddPocketClick implements View.OnClickListener
	  {
	    AddPocketClick()
	    {
	    }

	    public void onClick(View paramView)
	    {
	      if (AddIncome.this.addDate.getText().equals("���ѡ������"))
	      {
	        Toast.makeText(AddIncome.this, "����ѡ����������", 0).show();
	        return;
	      }
	      if (AddIncome.this.money.getText().toString().trim().length() == 0)
	      {
	        Toast.makeText(AddIncome.this, "������д������", 0).show();
	        return;
	      }
	      AddIncome.this.dialog();
	    }
	  }
	
class DateOnClick implements View.OnClickListener
  {
    DateOnClick()
    {

    }

    public void onClick(View paramView)
    {
    	AddIncome.this.showDialog(1);
    }
  }
	
	
class SpinnerSelected implements OnItemSelectedListener
	{
		SpinnerSelected()
		{
		}
		
		@Override
		public void onItemSelected(AdapterView<?> paramAdapterView, View arg1, int paramInt,
				long arg3) {
			// TODO Auto-generated method stub
			 String str = paramAdapterView.getItemAtPosition(paramInt).toString();
			 AddIncome.this.addType = str;
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}