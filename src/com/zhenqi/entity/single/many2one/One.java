package com.zhenqi.entity.single.many2one;

public class One {
	private int id;
	private String name;
	private Many many;// 声明一方对象，这里是Many实体类

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

	public Many getMany() {
		return many;
	}

	public void setMany(Many many) {
		this.many = many;
	}

}
