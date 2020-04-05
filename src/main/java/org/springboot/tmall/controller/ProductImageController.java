package org.springboot.tmall.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springboot.tmall.pojo.Product;
import org.springboot.tmall.pojo.ProductImage;
import org.springboot.tmall.service.ProductImageService;
import org.springboot.tmall.service.ProductService;
import org.springboot.tmall.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ProductImageController {

	@Autowired ProductService productService;
	@Autowired ProductImageService productImageService;
	
	@GetMapping("/products/{pid}/productImages")
	public List<ProductImage> list(@PathVariable int pid,@RequestParam String type){
		Product product = productService.get(pid);
		if(ProductImageService.type_single.equals(type)) {
			List<ProductImage> singles =productImageService.listSingleProductImages(product);
			return singles;
		}else if(ProductImageService.type_detail.equals(type)) {
			List<ProductImage> details =productImageService.listDetailProductImages(product);
			return details;
		}else {
			return new ArrayList<ProductImage>();
		}
	}
	
	@PostMapping("/productImages")
	public ProductImage add(@RequestParam String type,@RequestParam int pid,
MultipartFile image,HttpServletRequest request) throws Exception {
		ProductImage productImage=new ProductImage();
		Product product=productService.get(pid);
		productImage.setProduct(product);
		productImage.setType(type);
		productImageService.add(productImage);//保存到数据库中
		
		String folder="img/";
		//根据type类型拼接该图片存放位置路径字符串
		if(ProductImageService.type_single.equals(type)) {
			folder+="productSingle";
		}else {
			folder+="productDetail";
		}
		//根据拼接的相对路径字符串通过getRealPath获取绝对路径,以该路径创建文件类
		File imageFolder=new File(request.getServletContext().getRealPath(folder));
		File file=new File(imageFolder,productImage.getId()+".jpg");//路径拼接文件名
		String fileName=file.getName();//将该文件名保存,为了之后转换图片大小
		if(!file.getParentFile().exists()) {//判断父目录是否存在
			file.getParentFile().mkdirs();//创建父目录
		}
		try {
			image.transferTo(file);//输出图片到本地
			BufferedImage img= ImageUtil.change2jpg(file);//使用工具类将图片格式强制转换为JPG
			ImageIO.write(img, "jpg", file);//转换后写出到本地
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		//如果是单个图片,需要转大中小三种不同尺寸的图片,方便前端显示
		if(ProductImageService.type_single.equals(productImage.getType())) {
			String imageFolder_small=request.getServletContext()
					.getRealPath("img/productSingle_small");//获取小号图片的路径
			String imageFolder_middle=request.getServletContext()
					.getRealPath("img/productSingle_middle");//获取中号图片的路径
			File f_small=new File(imageFolder_small,fileName);
			File f_middle=new File(imageFolder_middle,fileName);
			ImageUtil.resizeImage(file, 56, 56, f_small);//使用工具类转换图片size
			ImageUtil.resizeImage(file, 217, 190, f_middle);
		}
		return productImage;
	}
	
	@DeleteMapping("productImages/{id}")
	public String delete(@PathVariable int id,HttpServletRequest request) {
		ProductImage productImage=productImageService.get(id);
		productImageService.delete(id);
		String folder="img/";
		
		if(ProductImageService.type_single.equals(productImage.getType())) {
			folder+="productSingle";
		}else{
			folder+="productDetail";
		}
		File fileFolder=new File(request.getServletContext().getRealPath(folder));
		File file=new File(fileFolder,productImage.getId()+".jpg");
		String fileName=file.getName();
		if(file.exists()) {
			file.delete();
		}
		
		if(ProductImageService.type_single.equals(productImage.getType())) {
			String imageFolder_small=request.getServletContext().getRealPath("img/productSingle_small");
			String imageFolder_middle=request.getServletContext().getRealPath("img/productSingle_middle");
			
			File f_small=new File(imageFolder_small,fileName);
			File f_middle=new File(imageFolder_middle,fileName);
			if(f_small.exists()) {
				f_small.delete();
			}
			if(f_middle.exists()) {
				f_middle.delete();
			}
			
		}
		
		return null;
	}
	
}
