package com.example.databaseproject;

public class Employee {
	private String name;

	public Employee(){
        super();
    }
	@Override
    public String toString() {
    	 return this.name;
    }
	
	public void employeename(String string1) {
		name=string1;
	} 
}
