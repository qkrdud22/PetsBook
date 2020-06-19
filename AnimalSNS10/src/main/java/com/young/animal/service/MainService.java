package com.young.animal.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.young.animal.dao.MainCommentDAO;
import com.young.animal.dao.MainDAO;
import com.young.animal.vo.MainCommentVO;
import com.young.animal.vo.MainVO;
import com.young.animal.vo.PagingVO;

@Service
public class MainService {
	@Autowired
	private MainDAO mainDAO;
	@Autowired
	private MainCommentDAO mainCommentDAO;
	
	static Logger logger = LoggerFactory.getLogger(MainService.class);

	public MainVO selectByIdx(int idx, int mode) {
		logger.info("MainService.selectByIdx 인수 : " + idx + "," + mode);
		MainVO vo = null;
		try {
			vo = mainDAO.selectByIdx(idx);
			if (vo != null && mode == 1) {
				mainDAO.hitUpdate(idx);
				vo.setHit(vo.getHit() + 1);
			}
			if (vo != null) {
				vo.setCommentList(mainCommentDAO.selectList(vo.getIdx()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("MainService.selectByIdx 리턴값 : " + vo);
		return vo;
	}

	public PagingVO<MainVO> selectList(int currentPage, int pageSize, int blockSize) {
		logger.info("MainService.selectList 인수 : " + currentPage + ", " + pageSize + ", " + blockSize);

		PagingVO<MainVO> pagingVO = null;
		try {
			int totalCount = mainDAO.selectCount();
			pagingVO = new PagingVO<>(totalCount, currentPage, pageSize, blockSize);
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("startNo", pagingVO.getStartNo());
			map.put("endNo", pagingVO.getEndNo());
			List<MainVO> list = mainDAO.selectList(map);
			
			if(map != null) {
				for(MainVO vo : list) {
					vo.setCommentCount(mainCommentDAO.selectCount(vo.getIdx()));
					vo.setCommentList(mainCommentDAO.selectList(vo.getIdx()));
				}
			}
			pagingVO.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("MainService.selectList 리턴값 : " + pagingVO);
		return pagingVO;
	}

	public void insert(MainVO vo) {
		logger.info("MainService.insert 인수값 : " + vo);
		try {
			if (vo != null) {
				mainDAO.insert(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("MainService.insert 리턴값 : ");
	}

	public void update(MainVO vo) {
		logger.info("MainService.update 인수 : " + vo);
		try {
			if (vo != null) {
				if (vo.getContent() != null && vo.getContent().trim().length() > 0) {
					if (vo.getIp() != null && vo.getIp().trim().length() > 0) {
						vo.setName(vo.getName().trim());
						vo.setContent(vo.getContent().trim());
						MainVO dbVO = mainDAO.selectByIdx(vo.getIdx());
						if (vo.getImage() == null) {
							vo.setImage(dbVO.getImage());
						}
						if (dbVO != null && dbVO.getName().equals(vo.getName())) {
							mainDAO.update(vo);
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("MainService.update 리턴값 : ");
	}
	
	public void delete(MainVO vo) {
		logger.info("MainService.delete 인수 : " + vo);
		try {
			if (vo != null) {
				if (vo.getName() != null && vo.getName().trim().length() > 0) {
					MainVO dbVO = mainDAO.selectByIdx(vo.getIdx());
					if (dbVO != null && dbVO.getName().equals(vo.getName())) {
						mainDAO.delete(vo.getIdx());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("MainService.delete 리턴값 : ");
	}
	
	public void commentInsert(MainCommentVO vo) {
		logger.info("MainService.commentInsert 인수 : " + vo);
		try {
			if (vo != null) {
				mainCommentDAO.insert(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("MainService.commentInsert 리턴값 ");
	}

	public void commentUpdate(MainCommentVO vo) {
		logger.info("MainService.commentUpdate 인수 : " + vo);
		try {
			if (vo != null) {
				MainCommentVO dbVO = mainCommentDAO.selectByIdx(vo.getIdx());
				if (dbVO != null && dbVO.getName().equals(vo.getName())) {
					mainCommentDAO.update(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("MainService.commentUpdate 리턴값 : ");
	}

	public void commetDelete(MainCommentVO vo) {
		logger.info("MainService.commentDelete 인수 : " + vo);
		try {
			if (vo != null) {
				MainCommentVO dbVO = mainCommentDAO.selectByIdx(vo.getIdx());
				if (dbVO != null && dbVO.getName().equals(vo.getName())) {
					mainCommentDAO.delete(vo.getIdx());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("MainService.commentDelete 리턴값 : ");
	}
}
