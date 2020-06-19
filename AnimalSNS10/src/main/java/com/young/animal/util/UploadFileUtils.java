package com.young.animal.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import com.young.animal.vo.MediaUtis;

public class UploadFileUtils {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	// 파일이름을 만들어 준다.
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception{
		String iconName = uploadPath + path + File.separator + fileName;
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	// 썸네일 이미지 생성하는 메서드
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception{
		// 이미지 읽기
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		// 썸네일 이미지 생성
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);
		// 썸네일 이미지 이름 만들기
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
		// 파일 생성
		File newFile = new File(thumbnailName);
		// 파일 형식 얻기
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		// 이미지 저장
		ImageIO.write(destImg,formatName.toUpperCase(),newFile);
		// 파일이름 리턴
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	// 폴더를 만들어 주는 메서드 
	private static void makeDir(String uploadPath, String... paths) {
		// 이미 폴더가 존재 한다면 
		if(new File(uploadPath + paths[paths.length-1]).exists()) {
			return; // 아무일도 하지 않는다.
		}
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
			if(!dirPath.exists()) {
				dirPath.mkdir(); // 폴더 생성
			}
		}
	}
	// 폴더를 "년/월/일" 형태로 만들어 주는 메서드
	private static String calcPath(String uploadPath) {
		Calendar calendar = Calendar.getInstance();;
		// /0000
		String yearPath = File.separator + calendar.get(Calendar.YEAR);
		// /0000/00
		String monthPath = yearPath + File.separator + String.format("%02d", calendar.get(Calendar.MONTH)+1);
		// /0000/00/00
		String datePath = monthPath + File.separator + String.format("%02d", calendar.get(Calendar.DATE));
		makeDir(uploadPath, yearPath,monthPath,datePath);
		logger.debug("만들어진 폴더 : " + datePath);
		return datePath;
	}
	
	public static String uploadFile(String uploadPath, String originalName, byte[] fileDate) throws Exception {
		//UUID uuid = UUID.randomUUID(); // 겹치지 않는 값을 자동으로 만들어주는 클래스
		String saveName = originalName; // 유일한이름 만들기
		String savePath = calcPath(uploadPath); // 년/월/일 순으로 폴더 만들기
		File   target = new File(uploadPath + File.separator + savePath , saveName);  // 만든 위치에 파일 생성
		FileCopyUtils.copy(fileDate, target); // 파일 저장
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);// 확장자 추출
		String uploadedFileName = null;
		if(MediaUtis.getMediaType(formatName)!=null) { // 이미지면 썸네일 생성
			uploadedFileName = makeThumbnail(uploadPath, savePath, saveName);
		}else { // 아니면 그냥이름 생성
			uploadedFileName = makeIcon(uploadPath, savePath, saveName);
		}
		return uploadedFileName;
	}
}