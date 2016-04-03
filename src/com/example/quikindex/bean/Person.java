package com.example.quikindex.bean;

import com.example.quikindex.util.PinyinUtils;

public class Person implements Comparable<Person> {
	private String name;
	private String pinyin;

	public Person(String name) {
		this.name = name;
		this.pinyin = PinyinUtils.getPinyin(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	@Override
	public int compareTo(Person another) {
		return this.pinyin.compareTo(another.pinyin);
	}

}
