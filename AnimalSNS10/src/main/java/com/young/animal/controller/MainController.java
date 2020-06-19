package com.young.animal.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.servlet.support.RequestContextUtils;

import com.young.animal.service.MainService;
import com.young.animal.vo.BoardVO;
import com.young.animal.vo.CommVO;
import com.young.animal.vo.MainCommentVO;
import com.young.animal.vo.MainVO;
import com.young.animal.vo.PagingVO;

@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private MainService mainService;
	@Resource(name = "uploadPath") // 빈으로 등록된 값 주입
	private String uploadPath;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String defaultPage(@ModelAttribute CommVO commVO, Model model, HttpServletRequest request) {
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
		model.addAttribute("vo", vo);
		model.addAttribute("pagingVO", pagingVO);
		model.addAttribute("commVO", commVO);
		model.addAttribute("newLine", "\n");
		model.addAttribute("br", "<br/>");
		return "home";
	}
	
	@RequestMapping(value = "/mainInsert")
	public String insert(@ModelAttribute CommVO commVO, Model model) {
		logger.info("AnimalController.insert : " + commVO.toString());
		model.addAttribute("commVO", commVO);
		return "mainInsert";
	}

	@RequestMapping(value = "/mainInsertOk", method = RequestMethod.GET)
	public String insertOKGet() {
		logger.info("AnimalController.insertOKGet!!!");
		return "redirect:/home";
	}

	@RequestMapping(value = "/mainInsertOk", method = RequestMethod.POST)
	public String insertOKPost(@ModelAttribute MainVO mainVO, @ModelAttribute CommVO commVO, Model model,
			HttpServletRequest request, MultipartFile file) throws IOException, Exception {
		logger.info("AnimalController.insertOKPost : " + commVO.toString());
		logger.info("AnimalController.insertOKPost : " + mainVO.toString());

		@SuppressWarnings("deprecation")
		String realPath = request.getRealPath(uploadPath);
		logger.debug("실제 경로 : " + realPath);
		mainVO.setImage(request.getParameter("image"));

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
			mainVO.setImage(fileName);
		}
		mainService.insert(mainVO);
		FlashMap map = RequestContextUtils.getOutputFlashMap(request);
		map.put("p", 1);
		map.put("s", commVO.getPageSize());
		map.put("b", commVO.getBlockSize());
		return "redirect:/home";
	}
	// 수정하기
		@RequestMapping(value = "/mainupdate") // 폼
		public String boardUpdate(@ModelAttribute CommVO commVO, Model model, HttpServletRequest reqeust) {
			MainVO vo = mainService.selectByIdx(commVO.getIdx(), commVO.getMode());
			model.addAttribute("vo", vo);
			model.addAttribute("commVO", commVO);
			return "mainupdate";
		}

		@RequestMapping(value = "/mainupdateOk", method = RequestMethod.GET) // 폼
		public String boardUpdateOkGet() {
			return "redirect:/home";
		}

		@RequestMapping(value = "/mainupdateOk", method = RequestMethod.POST) // 폼
		public String boardUpdateOkPost(@ModelAttribute MainVO mainVO, @ModelAttribute CommVO commVO, Model model,
				HttpServletRequest request, MultipartFile file) {
			String realPath = request.getRealPath(uploadPath);
			logger.debug("실제 경로 : " + realPath);
			mainVO.setImage(request.getParameter("image"));

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
				mainVO.setImage(fileName);
			}
			mainService.update(mainVO);
			FlashMap map = RequestContextUtils.getOutputFlashMap(request);
			map.put("p", commVO.getCurrentPage());
			map.put("s", commVO.getPageSize());
			map.put("b", commVO.getBlockSize());
			map.put("idx", mainVO.getIdx());
			map.put("m", 0);
			return "redirect:/home";
		}

		// 삭제하기
		@RequestMapping(value = "/maindelete") // 폼
		public String boardDelete(@ModelAttribute CommVO commVO, Model model, HttpServletRequest reqeust) {
			MainVO vo = mainService.selectByIdx(commVO.getIdx(), commVO.getMode());
			model.addAttribute("vo", vo);
			model.addAttribute("commVO", commVO);
			return "maindelete";
		}

		@RequestMapping(value = "/maindeleteOk", method = RequestMethod.GET) // 폼
		public String boardDeleteOkGet() {
			return "redirect:/home";
		}

		@RequestMapping(value = "/maindeleteOk", method = RequestMethod.POST) // 폼
		public String boardDeleteOkPost(@ModelAttribute MainVO mainVO, @ModelAttribute CommVO commVO, Model model,
				HttpServletRequest request) {
			mainService.delete(mainVO);
			FlashMap map = RequestContextUtils.getOutputFlashMap(request);
			map.put("p", commVO.getCurrentPage());
			map.put("s", commVO.getPageSize());
			map.put("b", commVO.getBlockSize());
			map.put("idx", mainVO.getIdx());
			map.put("m", 0);
			return "redirect:/home";
		}
	
	// 댓글 저장
		@RequestMapping(value = "/mainCommentInsertOk")
		public String commentInsertOk(@ModelAttribute MainCommentVO commentVO, @ModelAttribute CommVO commVO, Model model,
				HttpServletRequest request) {
			commentVO.setIp(request.getRemoteAddr());
			mainService.commentInsert(commentVO);
			FlashMap map = RequestContextUtils.getOutputFlashMap(request);
			map.put("p", commVO.getCurrentPage());
			map.put("s", commVO.getPageSize());
			map.put("b", commVO.getBlockSize());
			map.put("idx", commentVO.getRef());
			map.put("m", 0);
			return "redirect:/home";
		}
		@RequestMapping(value = "/mainCommentUpdateOk")
		public String commentUpdateOk(@ModelAttribute MainCommentVO mainCommentVO, @ModelAttribute CommVO commVO, Model model, HttpServletRequest request) {
			mainCommentVO.setIp(request.getRemoteAddr());
			mainService.commentUpdate(mainCommentVO);
			FlashMap map = RequestContextUtils.getOutputFlashMap(request);
			map.put("p", commVO.getCurrentPage());
			map.put("s", commVO.getPageSize());
			map.put("b", commVO.getBlockSize());
			map.put("idx", mainCommentVO.getRef());
			map.put("m", 0);
			return "redirect:/home";
		}
		@RequestMapping(value = "/mainCommentDeleteOk")
		public String commentDeleteOk(@ModelAttribute MainCommentVO mainCommentVO, @ModelAttribute CommVO commVO, Model model, HttpServletRequest request) {
			mainCommentVO.setIp(request.getRemoteAddr());
			mainService.commetDelete(mainCommentVO);
			FlashMap map = RequestContextUtils.getOutputFlashMap(request);
			map.put("p", commVO.getCurrentPage());
			map.put("s", commVO.getPageSize());
			map.put("b", commVO.getBlockSize());
			map.put("idx", mainCommentVO.getRef());
			map.put("m", 0);
			return "redirect:/home";
		}
		@RequestMapping(value = "/organicanimals")
		public String organicanimals(@ModelAttribute CommVO commVO, Model model, HttpServletRequest request) {
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
			model.addAttribute("vo", vo);
			model.addAttribute("pagingVO", pagingVO);
			model.addAttribute("commVO", commVO);
			model.addAttribute("newLine", "\n");
			model.addAttribute("br", "<br/>");
			return "organicanimals";
		}
		@RequestMapping(value = "/video")
		public String video(@ModelAttribute CommVO commVO, Model model, HttpServletRequest request) {
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
			model.addAttribute("vo", vo);
			model.addAttribute("pagingVO", pagingVO);
			model.addAttribute("commVO", commVO);
			model.addAttribute("newLine", "\n");
			model.addAttribute("br", "<br/>");
			return "video";
		}

}
