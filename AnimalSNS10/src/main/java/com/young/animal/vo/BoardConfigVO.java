package com.young.animal.vo;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement
@Data
public class BoardConfigVO {
	private int idx;
	private String tableName;
	private String boardName;
	private String boardInfo;
}
