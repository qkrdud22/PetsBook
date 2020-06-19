package com.young.animal.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CommVO {
	private int p=1;
	private int s=10;
	private int b=10;
	private String tb;
	@XmlElement
	private int idx=-1;
	private int m=0;
	
	@XmlElement
	private int currentPage=1;
	@XmlElement
	private int pageSize=10;
	@XmlElement
	private int blockSize=10;
	@XmlElement
	private int mode=0;
	@XmlElement
	private String tableName;

	
	public String getTb() {
		return tb;
	}
	public void setTb(String tb) {
		this.tb = tb;
		tableName = this.tb;
	}
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
		currentPage = this.p;
	}
	public int getS() {
		return s;
	}
	public void setS(int s) {
		this.s = s;
		pageSize = this.s;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
		blockSize = this.b;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
		mode = this.m;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getBlockSize() {
		return blockSize;
	}
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	@Override
	public String toString() {
		return "CommVO [p=" + p + ", s=" + s + ", b=" + b + ", idx=" + idx + ", m=" + m + ", tb=" + tb
				+ ", currentPage=" + currentPage + ", pageSize=" + pageSize + ", blockSize=" + blockSize + ", mode="
				+ mode + ", tableName=" + tableName + "]";
	}

}
