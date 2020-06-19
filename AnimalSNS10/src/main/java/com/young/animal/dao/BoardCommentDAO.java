package com.young.animal.dao;

import java.util.HashMap;
import java.util.List;

import com.young.animal.vo.BoardCommentVO;

public interface BoardCommentDAO {
	public int selectCount(HashMap<String, String> map);
	public List<BoardCommentVO> selectList(HashMap<String, String> map);
	public BoardCommentVO selectByIdx(HashMap<String, String> map);
	public void insert(BoardCommentVO vo);
	public void update(BoardCommentVO vo);
	public void delete(HashMap<String, String> map);
}
