package com.young.animal.dao;

import java.util.List;

import com.young.animal.vo.MainCommentVO;

public interface MainCommentDAO {
	public int selectCount(int ref);
	public List<MainCommentVO> selectList(int ref);
	public MainCommentVO selectByIdx(int idx);
	public int insert(MainCommentVO vo);
	public int update(MainCommentVO vo);
	public int delete(int idx);
}
