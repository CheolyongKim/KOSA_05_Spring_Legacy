package com.model;
/*
 * 게시판 글쓰기 + 파일 업로드
 * 
 * CREATE TABLE PHOTO(
 * 	NAME
 * 	AGE
 * 	IMAGE > 파일 저장 아니고 파일 이름(xxx.jpg)
 * )
 * 
 * 파일은 웹서버의 특정 폴더에 저장 > webapp/upload
 * AWS s3 server
 */

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class Photo {
	private String name;
	private int age;
	private String image;	// DTO 통해 설정되는 것이 아니고, 함수 안에서 가공
	@Override
	public String toString() {
		return "Photo [name=" + name + ", age=" + age + ", image=" + image + ", file=" + file + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	private CommonsMultipartFile file;
}
