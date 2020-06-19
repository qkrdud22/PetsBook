package com.young.animal.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MainVO {
	private int idx;
	private String name;
	private String image;
	private String content;
	private Date regDate;
	private String ip;
	private int hit;
	
	private int commentCount;
	private List<MainCommentVO> commentList;
}
