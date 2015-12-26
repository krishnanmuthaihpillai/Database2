package com.example.databaseproject;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EmployeeEdit extends Activity {
String employeename,employeeid,employeedestnation,employeesalary;
TextView Textid;
String temp;
EditText edid,edname,eddes,edsalary;
Button save;
SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.employee_edit);
		edid=(EditText)findViewById(R.id.editText1);
		edname=(EditText)findViewById(R.id.editText2);
		eddes=(EditText)findViewById(R.id.editText3);
		edsalary=(EditText)findViewById(R.id.editText4);
		save=(Button)findViewById(R.id.button1);
		 Intent i = getIntent();
		 employeename = i.getStringExtra("name");
		 employeeid = i.getStringExtra("id");
		 employeedestnation = i.getStringExtra("des");
		 employeesalary = i.getStringExtra("sal");
		 Textid=(TextView)findViewById(R.id.textView1);
		 Textid.setText(employeename+" "+"Edit");
		 edid.setText(employeeid);
		 edname.setText(employeename);
		 eddes.setText(employeedestnation);
		 edsalary.setText(employeesalary);
		 temp=employeename;
		 
		 save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				db=openOrCreateDatabase("EMPLOYEEDB", MODE_PRIVATE,null);	
				 db.execSQL("CREATE TABLE IF NOT EXISTS EMPtable(EId Int(3),EName VARCHAR,EDest VARCHAR,ESalary Int(10));");
				 db.execSQL("UPDATE EMPtable SET EId='"+edid.getText()+"',  EName='"+edname.getText()+"',  EDest='"+eddes.getText()+"',  ESalary='"+edsalary.getText()+"' WHERE  EName='"+temp+"' ;");
				 
				 EmployeeEdit.this.finish();
					Intent i=new Intent(EmployeeEdit.this,ViewEmployeeDetail.class);
				    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
			}
		});
		 
	}

}