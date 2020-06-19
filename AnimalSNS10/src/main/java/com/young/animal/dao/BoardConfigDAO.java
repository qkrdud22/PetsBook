package com.young.animal.dao;

import java.util.HashMap;
import java.util.List;

import com.young.animal.vo.BoardConfigVO;

public interface BoardConfigDAO {
	public int selectCount();
	public BoardConfigVO selectByIdx(int idx);
	public BoardConfigVO selectByTableName(HashMap<String, String> map);
	public List<BoardConfigVO> selectList();
	public void insert(BoardConfigVO vo);
	public void update(BoardConfigVO vo);
	public void delete(HashMap<String, String> map);
	public void createSequence1(HashMap<String, String> map);
	public void createTable1(HashMap<String, String> map);
	public void createSequence2(HashMap<String, String> map);
	public void createTable2(HashMap<String, String> map);
	public void deleteTable(HashMap<String, String> map);
	public void deleteTable2(HashMap<String, String> map);
	public void deleteSequence(HashMap<String, String> map);
	public void deleteSequence2(HashMap<String, String> map);
}
