package org.springboot.tmall.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springboot.tmall.pojo.Category;
import org.springboot.tmall.service.CategoryService;
import org.springboot.tmall.util.ImageUtil;
import org.springboot.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CategoryController {
	@Autowired CategoryService categoryService;  
	
	@GetMapping(value="/categories")
	public Page4Navigator<Category> listCategory(@RequestParam(value="start",
defaultValue = "0") int start ,@RequestParam(value="size",
defaultValue = "5") int size) throws Exception{
		start=start<0?0:start;//三目运算符
		return categoryService.list(start,size,5);
		
	}
	
	@PostMapping(value="/categories")
	public Object add(Category category,MultipartFile image
,HttpServletRequest request) throws Exception {
		categoryService.add(category);
		saveUploadImageFile(category,image,request);
		return category;
	}
	
	public void saveUploadImageFile(Category category,MultipartFile image
,HttpServletRequest request) throws IOException{
		if(image==null)
			return;
		String filePath=request.getServletContext().getRealPath("/img/category");
		File file=new File(filePath,category.getId()+".jpg");
		if(!file.getParentFile().exists()) {// 判断该文件的上级目录是否存在
			file.getParentFile().mkdirs();//不存在则创建,mkdirs会创建自己和其他所有的父目录
		}
		image.transferTo(file);//将multipart中的图片文件转移到文件file中,该步已经保存文件到服务器中
		BufferedImage img =ImageUtil.change2jpg(file);
		ImageIO.write(img, "jpg", file);
		
	}
	
	@DeleteMapping(value="/categories/{id}")
	public String delete(@PathVariable int id ,HttpServletRequest request) {
		categoryService.delete(id);//从数据库中删除
		String filePath=request.getServletContext().getRealPath("/img/category");
		File file=new File(filePath,id+".jpg");
		file.delete();//从本地图片服务器中删除
		return null;//基于REST风格,删除成功需要返回一个空值
	}

	@GetMapping(value="/categories/{id}")
	public Category get(@PathVariable int id) {
		return categoryService.get(id);
	}
	
	@PutMapping(value="/categories")
	public String update(Category category,MultipartFile image,HttpServletRequest request) throws Exception {
		categoryService.update(category);
		saveUploadImageFile(category,image,request);
		return null;
	}
}
