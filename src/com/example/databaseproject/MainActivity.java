package com.example.databaseproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
TextView id,name,desination,salary;
EditText eid,ename,edestination,esalary;
String sid="ID",sname="NAME",sdesination="DESTINATION",ssalary="SALARY",bcancel="CANCEL",
bview="VIEW",bsave="SAVE",employeeid,employeename,employeedestination,employeesalary;
Button cancel,view,save;
SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		cancel=(Button)findViewById(R.id.button1);
		view=(Button)findViewById(R.id.button2);
		save=(Button)findViewById(R.id.button3);
		id=(TextView)findViewById(R.id.textView1);
		name=(TextView)findViewById(R.id.textView2);
		desination=(TextView)findViewById(R.id.textView3);
		salary=(TextView)findViewById(R.id.textView4);
		eid=(EditText)findViewById(R.id.editText1);
		ename=(EditText)findViewById(R.id.editText2);
		edestination=(EditText)findViewById(R.id.editText3);
		esalary=(EditText)findViewById(R.id.editText4);
		id.setText(sid);
		name.setText(sname);
		desination.setText(sdesination);
		salary.setText(ssalary);
		cancel.setText(bcancel);
		view.setText(bview);
		save.setText(bsave);
	    db=openOrCreateDatabase("EMPLOYEEDB", MODE_PRIVATE,null);
		db.execSQL("CREATE TABLE IF NOT EXISTS EMPtable(EId Int(3),EName VARCHAR,EDest VARCHAR,ESalary Int(10));");
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "CANCEL", Toast.LENGTH_SHORT).show();
				Intent i=new Intent(MainActivity.this,MainActivity.class);
			    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				MainActivity.this.finish();
				
			}
		});
view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "VIEW", Toast.LENGTH_SHORT).show();
				Intent i=new Intent(MainActivity.this,ViewEmployeeDetail.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				MainActivity.this.finish();
				startActivity(i);
			}
		});
save.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
//		Toast.makeText(getApplicationContext(), "SAVE", Toast.LENGTH_SHORT).show();
		
		employeeid=eid.getText().toString();
		employeename=ename.getText().toString();
		employeedestination=edestination.getText().toString();
		employeesalary=esalary.getText().toString();
		db.execSQL("CREATE TABLE IF NOT EXISTS `EMPtable` ( `EId` INT( 3 ), `EName` VARCHAR , `EDest` VARCHAR , `ESalary` Int(10));");
		db.execSQL("INSERT INTO EMPtable VALUES('"+employeeid+"','"+employeename+"','"+employeedestination+"','"+employeesalary+"');");
		  Cursor c=db.rawQuery("SELECT *FROM EMPtable",null);
		    c.moveToFirst();
		    while(!c.isAfterLast())
			{
		    	 Log.d("check", c.getString(c.getColumnIndex("EName")));
			c.moveToNext();
			}	
		    
		Toast.makeText(getApplicationContext(), "SAVE", Toast.LENGTH_SHORT).show();
		
		
	}
});
	}

}