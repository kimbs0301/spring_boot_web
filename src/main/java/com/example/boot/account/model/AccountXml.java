package com.example.boot.account.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "account")
public class AccountXml {
	private String name;
	private String text;

	public AccountXml() {

	}

	public AccountXml(String name, String text) {
		this.name = name;
		this.text = text;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
