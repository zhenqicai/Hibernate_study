package com.zhenqi.entity.single.one2many;

import java.util.HashSet;
import java.util.Set;

public class Dept {
	
	private int id;
	private String name;
	
	private Set<Employee> emps=new HashSet<Employee>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmps() {
		return emps;
	}

	public void setEmps(Set<Employee> emps) {
		this.emps = emps;
	}
	
	

}
