package com.young.animal.dao;

import java.util.HashMap;
import java.util.List;

import com.young.animal.vo.BoardVO;

public interface BoardDAO {
	public int selectCount(HashMap<String, String> map);
	public BoardVO selectByIdx(HashMap<String, String> map);
	public List<BoardVO> selectList(HashMap<String, String> map);
	public void insert(BoardVO vo);
	public void update(BoardVO vo);
	public void delete(HashMap<String, String> map);
	public void hitUpdate(HashMap<String, String> map);
}
