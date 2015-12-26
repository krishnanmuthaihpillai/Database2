package com.example.databaseproject;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ViewEmployeeDetail extends Activity {
	ListView lv;
	 SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_employee_detail);
		
		lv = (ListView) findViewById(R.id.listView1);

			List<Employee> contactList = new ArrayList<Employee>();
		     // Select All Query
		     String selectQuery = "SELECT * FROM EMPtable";
		     db=openOrCreateDatabase("EMPLOYEEDB", MODE_PRIVATE,null);	
		 	db.execSQL("CREATE TABLE IF NOT EXISTS EMPtable(EId Int(3),EName VARCHAR,EDest VARCHAR,ESalary Int(10));");

		    Cursor cursor = db.rawQuery(selectQuery, null);
		   
		    
		    if (cursor.moveToFirst()) {
		        do {
		        	Employee contact = new Employee();
		            contact.employeename(cursor.getString(1));
		            // Adding contact to list
		            contactList.add(contact);
		        } while (cursor.moveToNext());
		    }
		    ArrayAdapter<Employee> adapter = new ArrayAdapter<Employee>(this,
		            android.R.layout.simple_list_item_1, contactList);

		lv.setAdapter(adapter);	
		
		
		
		
		
		lv.setOnItemClickListener(new OnItemClickListener() {
	         @Override
	         public void onItemClick(AdapterView<?> parent, View view, int position,
	                 long id) {
	        	 String item = ((TextView)view).getText().toString();
	        	 Intent i=new Intent(ViewEmployeeDetail.this,EmployeeDetail.class);
	             i.putExtra("name",item );
	             startActivity(i);
	         }
	         
	     });
		 db.close();
		
		
		
	}
	@Override
    public void onBackPressed() {
		
		ViewEmployeeDetail.this.finish();
		Intent i=new Intent(ViewEmployeeDetail.this,MainActivity.class);
	    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
	}
	
	
}