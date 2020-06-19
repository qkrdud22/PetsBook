package com.young.animal.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class MainCommentVO {
	private int idx;
	private int ref;
	private String name;
	private String content;
	private Date regDate;
	private String ip;
}
