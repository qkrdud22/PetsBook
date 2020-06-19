package com.young.animal.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement
@Data
public class BoardCommentVO {
	private int 	idx;
	private int 	ref;
	private String 	name;
	private String 	content;
	private Date 	regDate;
	private String 	ip;
	private String  tb;
	private String  tableName;
	
	public void setTb(String tb) {
		this.tb = tb;
		tableName = this.tb;
	}
}
