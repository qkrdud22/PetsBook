package com.young.animal.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.young.animal.dao.BoardCommentDAO;
import com.young.animal.dao.BoardConfigDAO;
import com.young.animal.dao.BoardDAO;
import com.young.animal.vo.BoardConfigVO;

@Service
public class BoardConfigService {
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private BoardCommentDAO commentDAO;
	@Autowired
	private BoardConfigDAO boardConfigDAO;

	static Logger logger = LoggerFactory.getLogger(BoardConfigService.class);
	// --------------------------------------------------------------

	// 1. 1개 얻기 -- 내용보기, 수정, 삭제
	public BoardConfigVO selectByIdx(int idx) {
		logger.info("BoardConfigService.selectByIdx 인수 : " +  idx);
		BoardConfigVO vo = null; // 이부분만 변경
		try {
			// ------------------------------------------------------------------
			// 이부분만 변경
			vo = boardConfigDAO.selectByIdx(idx);
			// ------------------------------------------------------------------
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("BoardConfigService.selectByIdx 리턴값 : " + vo);
		return vo; // 이부분만 변경
	}	
	public BoardConfigVO selectByTableName(String tableName) {
		logger.info("BoardConfigService.selectByTableName 인수 : " +  tableName);
		BoardConfigVO vo = null; // 이부분만 변경
		try {
			// ------------------------------------------------------------------
			// 이부분만 변경
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("tableName", tableName);
			vo = boardConfigDAO.selectByTableName(map);
			// ------------------------------------------------------------------
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("BoardConfigService.selectByIdx 리턴값 : " + vo);
		return vo; // 이부분만 변경
	}

	// 2. 1페이지 얻기 - 목록보기
	public List<BoardConfigVO> selectList() {
		logger.info("BoardConfigService.selectList 인수 : ");
		List<BoardConfigVO> list = null; // 이부분만 변경
		try {
			list = boardConfigDAO.selectList();
			// ------------------------------------------------------------------
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("BoardConfigService.selectByIdx 리턴값 : " + list);
		return list; // 이부분만 변경
	}

	// 3. 저장
	public void insert(BoardConfigVO vo) {
		logger.info("BoardConfigService.insert 인수 : " + vo);
		try {
			// ------------------------------------------------------------------
			if (vo != null) {
				boardConfigDAO.insert(vo);
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("tableName", vo.getTableName());
				boardConfigDAO.createSequence1(map);
				boardConfigDAO.createTable1(map);
				boardConfigDAO.createSequence2(map);
				boardConfigDAO.createTable2(map);
			}
			// ------------------------------------------------------------------
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("BoardConfigService.selectByIdx 리턴값 : ");
	}

	// 4. 수정하기
	public void update(BoardConfigVO vo) {
		logger.info("BoardConfigService.update 인수 : " + vo);
		try {
			// ------------------------------------------------------------------
			boardConfigDAO.update(vo);			
			// ------------------------------------------------------------------
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 5. 삭제하기
	public void delete(BoardConfigVO vo) {
		logger.info("BoardConfigService.delete 인수 : " + vo);
		try {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("tableName", vo.getTableName());
			// ------------------------------------------------------------------
			boardConfigDAO.delete(map);
			// ------------------------------------------------------------------
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteTable(BoardConfigVO vo) {
		logger.info("BoardConfigService.deleteTable 인수 : " + vo);
		try {
			if(vo != null) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("tableName", vo.getTableName());
				boardConfigDAO.delete(map);
				boardConfigDAO.deleteTable(map);
				boardConfigDAO.deleteTable2(map);
				boardConfigDAO.deleteSequence(map);
				boardConfigDAO.deleteSequence2(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
