package com.young.animal.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.young.animal.service.BoardConfigService;
import com.young.animal.service.BoardService;
import com.young.animal.service.MainService;
import com.young.animal.vo.BoardCommentVO;
import com.young.animal.vo.BoardConfigVO;
import com.young.animal.vo.BoardPagingVO;
import com.young.animal.vo.BoardVO;
import com.young.animal.vo.CommVO;
import com.young.animal.vo.MainVO;
import com.young.animal.vo.PagingVO;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired
	private MainService mainService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardConfigService boardConfigService;
	@Resource(name = "uploadPath") // 빈으로 등록된 값 주입
	private String uploadPath;

	@RequestMapping(value = "/boardList")
	public String home(Model model, CommVO commVO,HttpServletRequest request) {
		logger.info("BoardConfigController.admin.list");
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if(flashMap != null) {
			Integer p = (Integer) flashMap.get("p");
			if(p!=null) commVO.setCurrentPage(p);
			Integer s = (Integer) flashMap.get("s");
			if(s!=null) commVO.setPageSize(s);
			Integer b = (Integer) flashMap.get("b");
			if(b!=null) commVO.setBlockSize(b);
			Integer m = (Integer) flashMap.get("m");
		    if(m!=null) commVO.setMode(m);
		    Integer idx = (Integer) flashMap.get("idx");
		    if(idx!=null) commVO.setIdx(idx);
		}
		PagingVO<MainVO> pagingVO = 
				mainService.selectList(commVO.getCurrentPage(), commVO.getPageSize(), commVO.getBlockSize());
		MainVO vo = mainService.selectByIdx(commVO.getIdx(), commVO.getMode());
		List<BoardConfigVO> list = boardConfigService.selectList();
		model.addAttribute("vo", vo);
		model.addAttribute("pagingVO", pagingVO);
		model.addAttribute("commVO", commVO);
		model.addAttribute("newLine", "\n");
		model.addAttribute("br", "<br/>");
		model.addAttribute("list", list);
		logger.info("BoardConfigController.list : " + list);
		return "boardList";
	}
	
	@RequestMapping(value = "/index")
	public String dog(@ModelAttribute CommVO commVO, Model model, HttpServletRequest request, RedirectAttributes rttr) {
		logger.info("AnimalController.index : " + commVO.toString());
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if (flashMap != null) {
			Integer idx = (Integer) flashMap.get("idx");
			if(idx!=null) commVO.setIdx(idx);
			Integer p = (Integer) flashMap.get("p");
			if (p != null)
				commVO.setCurrentPage(p);
			Integer s = (Integer) flashMap.get("s");
			if (s != null)
				commVO.setPageSize(s);
			Integer b = (Integer) flashMap.get("b");
			if (b != null)
				commVO.setBlockSize(b);
			Integer m = (Integer) flashMap.get("m");
			if(m!=null) commVO.setMode(m);
			String tb = (String) flashMap.get("tb");
			if(tb != null) 
				commVO.setTb(tb);
				commVO.setTableName(tb);
		}
		if(commVO.getTb()==null || commVO.getTb().trim().length()==0) {
			PagingVO<MainVO> pagingVO = 
					mainService.selectList(commVO.getCurrentPage(), commVO.getPageSize(), commVO.getBlockSize());
			MainVO vo = mainService.selectByIdx(commVO.getIdx(), commVO.getMode());
			model.addAttribute("vo", vo);
			model.addAttribute("pagingVO", pagingVO);
			model.addAttribute("commVO", commVO);
			model.addAttribute("newLine", "\n");
			model.addAttribute("br", "<br/>");
			return "home";
		}
		logger.info("AnimalController.index : " + commVO.toString());
		BoardPagingVO<BoardVO> pagingVO = boardService.selectList(commVO.getTb(),commVO.getCurrentPage(), commVO.getPageSize(),
				commVO.getBlockSize());
		model.addAttribute("pagingVO", pagingVO);
		model.addAttribute("commVO", commVO);
		model.addAttribute("newLine", "\n");
		model.addAttribute("br", "<br/>");
		//logger.info("AnimalController.index : " + pagingVO.toString());
		logger.info("AnimalController.index : " + commVO.toString());
		return "index";
	}

	@RequestMapping(value = "/insert")
	public String insert(@ModelAttribute CommVO commVO, Model model) {
		logger.info("AnimalController.insert : " + commVO.toString());
		model.addAttribute("commVO", commVO);
		return "insert";
	}

	@RequestMapping(value = "/insertOk", method = RequestMethod.GET)
	public String insertOKGet() {
		logger.info("AnimalController.insertOKGet!!!");
		return "redirect:/index";
	}

	@RequestMapping(value = "/insertOk", method = RequestMethod.POST)
	public String insertOKPost(@ModelAttribute BoardVO boardVO, @ModelAttribute CommVO commVO, Model model,
			HttpServletRequest request, MultipartFile file) throws IOException, Exception {
		logger.info("AnimalController.insertOKPost : " + commVO.toString());
		logger.info("AnimalController.insertOKPost : " + boardVO.toString());

		@SuppressWarnings("deprecation")
		String realPath = request.getRealPath(uploadPath);
		logger.debug("실제 경로 : " + realPath);
		boardVO.setImage(request.getParameter("image"));

		if (file != null && file.getSize() > 0) {
			String fileName = file.getOriginalFilename();
			File uploadFile = new File(realPath + "//" + fileName);
			try {
				file.transferTo(uploadFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			boardVO.setImage(fileName);
		}
		boardService.insert(boardVO);
		FlashMap map = RequestContextUtils.getOutputFlashMap(request);
		map.put("p", 1);
		map.put("s", commVO.getPageSize());
		map.put("b", commVO.getBlockSize());
		map.put("tb", commVO.getTableName());
		return "redirect:/index";
	}

	@RequestMapping(value = "/view")
	public String boardView(@ModelAttribute CommVO commVO, Model model, HttpServletRequest request) {
		logger.info("뷰 : " + commVO.toString());
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if (flashMap != null) {
			Integer p = (Integer) flashMap.get("p");
			if (p != null)
				commVO.setCurrentPage(p);
			Integer s = (Integer) flashMap.get("s");
			if (s != null)
				commVO.setPageSize(s);
			Integer b = (Integer) flashMap.get("b");
			if (b != null)
				commVO.setBlockSize(b);
			Integer m = (Integer) flashMap.get("m");
			if (m != null)
				commVO.setMode(m);
			Integer idx = (Integer) flashMap.get("idx");
			if (idx != null)
				commVO.setIdx(idx);
			String tb = (String) flashMap.get("tb");
			if(tb!=null) 
			commVO.setTableName(tb);
			commVO.setTb(tb);
		}
		if(commVO.getTb()==null || commVO.getTb().trim().length()==0) {
			PagingVO<MainVO> pagingVO = 
					mainService.selectList(commVO.getCurrentPage(), commVO.getPageSize(), commVO.getBlockSize());
			MainVO vo = mainService.selectByIdx(commVO.getIdx(), commVO.getMode());
			List<BoardConfigVO> list = boardConfigService.selectList();
			model.addAttribute("vo", vo);
			model.addAttribute("pagingVO", pagingVO);
			model.addAttribute("commVO", commVO);
			model.addAttribute("newLine", "\n");
			model.addAttribute("br", "<br/>");
			model.addAttribute("list", list);
			logger.info("BoardConfigController.list : " + list);
			return "boardList";
		}
		BoardVO vo = boardService.selectByIdx(commVO.getTableName(),commVO.getIdx(), commVO.getMode());
		model.addAttribute("vo", vo);
		model.addAttribute("commVO", commVO);
		model.addAttribute("newLine", "\n");
		model.addAttribute("br", "<br/>");
		return "view";
	}

	// 수정하기
	@RequestMapping(value = "/update") // 폼
	public String boardUpdate(@ModelAttribute CommVO commVO, Model model, HttpServletRequest reqeust) {
		BoardVO vo = boardService.selectByIdx(commVO.getTableName(), commVO.getIdx(), commVO.getMode());
		model.addAttribute("vo", vo);
		model.addAttribute("commVO", commVO);
		return "update";
	}

	@RequestMapping(value = "/updateOk", method = RequestMethod.GET) // 폼
	public String boardUpdateOkGet() {
		return "redirect:/index";
	}

	@RequestMapping(value = "/updateOk", method = RequestMethod.POST) // 폼
	public String boardUpdateOkPost(@ModelAttribute BoardVO boardVO, @ModelAttribute CommVO commVO, Model model,
			HttpServletRequest request, MultipartFile file) {
		String realPath = request.getRealPath(uploadPath);
		logger.debug("실제 경로 : " + realPath);
		boardVO.setImage(request.getParameter("image"));

		if (file != null && file.getSize() > 0) {
			String fileName = file.getOriginalFilename();
			File uploadFile = new File(realPath + "//" + fileName);
			try {
				file.transferTo(uploadFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			boardVO.setImage(fileName);
		}
		boardService.update(boardVO);
		FlashMap map = RequestContextUtils.getOutputFlashMap(request);
		map.put("p", commVO.getCurrentPage());
		map.put("s", commVO.getPageSize());
		map.put("b", commVO.getBlockSize());
		map.put("idx", boardVO.getIdx());
		map.put("m", 0);
		map.put("tb", commVO.getTableName());
		return "redirect:/view";
	}

	// 삭제하기
	@RequestMapping(value = "/delete") // 폼
	public String boardDelete(@ModelAttribute CommVO commVO, Model model, HttpServletRequest reqeust) {
		BoardVO vo = boardService.selectByIdx(commVO.getTableName(), commVO.getIdx(), commVO.getMode());
		model.addAttribute("vo", vo);
		model.addAttribute("commVO", commVO);
		return "delete";
	}

	@RequestMapping(value = "/deleteOk", method = RequestMethod.GET) // 폼
	public String boardDeleteOkGet() {
		return "redirect:/index";
	}

	@RequestMapping(value = "/deleteOk", method = RequestMethod.POST) // 폼
	public String boardDeleteOkPost(@ModelAttribute BoardVO boardVO, @ModelAttribute CommVO commVO, Model model,
			HttpServletRequest request) {
		boardService.delete(boardVO);
		FlashMap map = RequestContextUtils.getOutputFlashMap(request);
		map.put("p", commVO.getCurrentPage());
		map.put("s", commVO.getPageSize());
		map.put("b", commVO.getBlockSize());
		map.put("idx", boardVO.getIdx());
		map.put("tb", commVO.getTableName());
		map.put("m", 0);
		return "redirect:/index";
	}

	// 댓글 저장
	@RequestMapping(value = "/CommentInsertOk")
	public String commentInsertOk(@ModelAttribute BoardCommentVO commentVO, @ModelAttribute CommVO commVO, Model model,
			HttpServletRequest request) {
		commentVO.setIp(request.getRemoteAddr());
		boardService.commentInsert(commentVO);
		FlashMap map = RequestContextUtils.getOutputFlashMap(request);
		map.put("p", commVO.getCurrentPage());
		map.put("s", commVO.getPageSize());
		map.put("b", commVO.getBlockSize());
		map.put("idx", commentVO.getRef());
		map.put("m", 0);
		map.put("tb", commVO.getTableName());
		return "redirect:/view";
	}
	@RequestMapping(value = "/CommentUpdateOk")
	public String commentUpdateOk(@ModelAttribute BoardCommentVO commentVO, @ModelAttribute CommVO commVO, Model model, HttpServletRequest request) {
		commentVO.setIp(request.getRemoteAddr());
		boardService.commentUpdate(commentVO);
		FlashMap map = RequestContextUtils.getOutputFlashMap(request);
		map.put("p", commVO.getCurrentPage());
		map.put("s", commVO.getPageSize());
		map.put("b", commVO.getBlockSize());
		map.put("idx", commentVO.getRef());
		map.put("m", 0);
		map.put("tb", commVO.getTableName());
		return "redirect:/view";
	}
	@RequestMapping(value = "/CommentDeleteOk")
	public String commentDeleteOk(@ModelAttribute BoardCommentVO commentVO, @ModelAttribute CommVO commVO, Model model, HttpServletRequest request) {
		commentVO.setIp(request.getRemoteAddr());
		boardService.commetDelete(commentVO);
		FlashMap map = RequestContextUtils.getOutputFlashMap(request);
		map.put("p", commVO.getCurrentPage());
		map.put("s", commVO.getPageSize());
		map.put("b", commVO.getBlockSize());
		map.put("idx", commentVO.getRef());
		map.put("m", 0);
		map.put("tb", commVO.getTableName());
		return "redirect:/view";
	}
}
