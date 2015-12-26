package com.example.databaseproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EmployeeDetail extends Activity {
	String employeename;
	TextView Detailname,Textname,Textid,TextDes,Textsalary;
	 SQLiteDatabase db;
	 int id;
	 Button close,edit,delete;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.employee_detail);
		  Intent i = getIntent();
		  employeename = i.getStringExtra("name");
		  Detailname=(TextView)findViewById(R.id.textView1);
		  Textid=(TextView)findViewById(R.id.textView6);
		  Textname=(TextView)findViewById(R.id.textView7);
		  TextDes=(TextView)findViewById(R.id.textView8);
		  Textsalary=(TextView)findViewById(R.id.textView9);
		  close=(Button)findViewById(R.id.button1);
		  edit=(Button)findViewById(R.id.button2);
		  delete=(Button)findViewById(R.id.button4);
		  Detailname.setText(employeename+" "+"Details");
		  String selectQuery = "SELECT * FROM EMPtable where EName='"+employeename+"' ";
		     db=openOrCreateDatabase("EMPLOYEEDB", MODE_PRIVATE,null);	
		 	db.execSQL("CREATE TABLE IF NOT EXISTS EMPtable(EId Int(3),EName VARCHAR,EDest VARCHAR,ESalary Int(10));");
		 	 Cursor cursor = db.rawQuery(selectQuery, null);
		    if (cursor.moveToFirst()) {
		    	 Textid.setText(cursor.getString(0)); 
		    	 Textname.setText(cursor.getString(1)); 
		    	 TextDes.setText(cursor.getString(2)); 
		    	 Textsalary.setText(cursor.getString(3)); 
		    }
		  close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EmployeeDetail.this.finish();
				Intent i=new Intent(EmployeeDetail.this,ViewEmployeeDetail.class);
			    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
		
		  edit.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i=new Intent(EmployeeDetail.this,EmployeeEdit.class);
					String str=Textname.getText().toString();
					String str2=Textid.getText().toString();
					String str3=TextDes.getText().toString();
					String str4=Textsalary.getText().toString();
					  i.putExtra("name",str );
					  i.putExtra("id",str2 );
					  i.putExtra("des",str3 );
					  i.putExtra("sal",str4 );
					  
			             startActivity(i);
					
				}
			});
		  delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String str=Textname.getText().toString();
					db.execSQL("DELETE FROM EMPtable WHERE EName='"+str+"';");
					EmployeeDetail.this.finish();
					Intent i=new Intent(EmployeeDetail.this,ViewEmployeeDetail.class);
				    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
					db.close();
				}
			});
	}
	@Override
    public void onBackPressed() {
		
		EmployeeDetail.this.finish();
		Intent i=new Intent(EmployeeDetail.this,ViewEmployeeDetail.class);
	    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
	}
}