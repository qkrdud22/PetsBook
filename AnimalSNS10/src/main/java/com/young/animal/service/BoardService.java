package com.young.animal.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.young.animal.dao.BoardCommentDAO;
import com.young.animal.dao.BoardDAO;
import com.young.animal.vo.BoardCommentVO;
import com.young.animal.vo.BoardPagingVO;
import com.young.animal.vo.BoardVO;

@Service
public class BoardService {
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private BoardCommentDAO commentDAO;
	static Logger logger = LoggerFactory.getLogger(BoardService.class);

	public BoardVO selectByIdx(String tableName, int idx, int mode) {
		logger.info("BoardService.selectByIdx 인수 : "+ tableName+"," + idx + ", " + mode);
		BoardVO vo = null;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("tableName", tableName);
		map.put("idx", idx+"");
		vo = boardDAO.selectByIdx(map);
		try {
			if (vo != null && mode == 1) {
				boardDAO.hitUpdate(map);
				vo.setHit(vo.getHit() + 1);
				vo.setTb(tableName);
				vo.setTableName(tableName);
			}
			if (vo != null) {
				HashMap<String, String> map2 = new HashMap<String, String>();
				map2.put("tableName",tableName);
				map2.put("ref",vo.getIdx()+"");
				vo.setCommentList(commentDAO.selectList(map2));
				vo.setTb(tableName);
				vo.setTableName(tableName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("BoardService.selectByIdx 리턴값 : " + vo);
		return vo;
	}

	public BoardPagingVO<BoardVO> selectList(String tableName, int currentPage, int pageSize, int blockSize) {
		logger.info("BoardService.selectList 인수 : " + tableName + "," + currentPage + ", " + pageSize + ", " + blockSize);
		BoardPagingVO<BoardVO> pagingVO = null;
		try {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("tableName",tableName);
			int totalCount = boardDAO.selectCount(map); // 전체 개수 얻기
			pagingVO = new BoardPagingVO<>(totalCount, tableName, currentPage, pageSize, blockSize); // 페이징 객체 생성
			HashMap<String, String> map2 = new HashMap<String, String>();
			map2.put("startNo", pagingVO.getStartNo()+"");
			map2.put("endNo", pagingVO.getEndNo()+"");
			map2.put("tableName",tableName);
			List<BoardVO> list = boardDAO.selectList(map2);
			
			if (list != null) {
				for (BoardVO vo : list) {
					HashMap<String, String> map3 = new HashMap<String, String>();
					map3.put("tableName",tableName);
					map3.put("ref",vo.getIdx()+"");
					vo.setCommentCount(commentDAO.selectCount(map3)); // 댓글의 개수를 구해서 넣는다.
					vo.setTb(tableName);
					vo.setTableName(tableName);				
				}
			}
			pagingVO.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("BoardService.selectList 리턴값 : " + pagingVO);
		return pagingVO;
	}

	public void insert(BoardVO vo) {
		logger.info("BoardService.insert 인수값 : " + vo);
		try {
			if (vo != null) {
				boardDAO.insert(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("BoardService.insert 리턴값 : ");
	}

	public void update(BoardVO vo) {
		logger.info("BoardService.update 인수 : " + vo);
		try {
			if (vo != null) {
				if (vo.getSubject() != null && vo.getSubject().trim().length() > 0) {
					if (vo.getContent() != null && vo.getContent().trim().length() > 0) {
						if (vo.getIp() != null && vo.getIp().trim().length() > 0) {
							vo.setName(vo.getName().trim());
							vo.setSubject(vo.getSubject().trim());
							vo.setContent(vo.getContent().trim());
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("tableName", vo.getTableName());
							map.put("idx",vo.getIdx()+"");
							BoardVO dbVO = boardDAO.selectByIdx(map);
							if(vo.getImage()==null) {
								vo.setImage(dbVO.getImage());
							}
							if (dbVO != null && dbVO.getName().equals(vo.getName())) {
								boardDAO.update(vo);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("BoardService.update 리턴값 : ");
	}

	public void delete(BoardVO vo) {
		logger.info("BoardService.delete 인수 : " + vo);
		try {
			if (vo != null) {
				if (vo.getName() != null && vo.getName().trim().length() > 0) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("tableName", vo.getTableName());
					map.put("idx", vo.getIdx()+"");
					BoardVO dbVO = boardDAO.selectByIdx(map);
					if (dbVO != null && dbVO.getName().equals(vo.getName())) {
						boardDAO.delete(map);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("BoardService.delete 리턴값 : ");
	}

	public void commentInsert(BoardCommentVO vo) {
		logger.info("BoardService.commentInsert 인수 : " + vo);
		try {
			if (vo != null) {
				commentDAO.insert(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("BoardService.commentInsert 리턴값 ");
	}

	public void commentUpdate(BoardCommentVO vo) {
		logger.info("BoardService.commentUpdate 인수 : " + vo);
		try {
			if (vo != null) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("tableName", vo.getTableName());
				map.put("idx", vo.getIdx()+"");
				BoardCommentVO dbVO = commentDAO.selectByIdx(map);
				if (dbVO != null && dbVO.getName().equals(vo.getName())) {
					commentDAO.update(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("BoardService.commentUpdate 리턴값 : ");
	}

	public void commetDelete(BoardCommentVO vo) {
		logger.info("BoardService.commentDelete 인수 : " + vo);
		try {
			if (vo != null) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("tableName",vo.getTableName());
				map.put("idx",vo.getIdx()+"");
				BoardCommentVO dbVO = commentDAO.selectByIdx(map);
				if (dbVO != null && dbVO.getName().equals(vo.getName())) {
					commentDAO.delete(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("BoardService.commentDelete 리턴값 : ");
	}
}
