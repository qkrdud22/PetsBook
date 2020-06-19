package com.young.animal.vo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

// 업로드한 파일 중 이미지 파일만 걸러주는 클래스
public class MediaUtis {
	// 이미지 파일 형식을 저장할 변수
	private static Map<String, MediaType> mediaMap;
	
	static {
		mediaMap = new HashMap<String, MediaType>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}
	
	// mediaType을 리턴해주는 메서드
	public static MediaType getMediaType(String type) {
		return mediaMap.get(type.toUpperCase());
	}
}
