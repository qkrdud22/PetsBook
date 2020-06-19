package com.young.animal.dao;

import java.util.HashMap;
import java.util.List;

import com.young.animal.vo.MainVO;

public interface MainDAO {
	public int selectCount();
	public MainVO selectByIdx(int idx);
	public List<MainVO> selectList(HashMap<String, Integer> map);
	public int insert(MainVO vo);
	public int update(MainVO vo);
	public int delete(int idx);
	public int hitUpdate(int idx);
}
