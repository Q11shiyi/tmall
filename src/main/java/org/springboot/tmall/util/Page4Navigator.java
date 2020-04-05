package org.springboot.tmall.util;

import java.util.List;

import org.springframework.data.domain.Page;

/*Page4Navigator工具类
**用来封装JPA自带的分页工具Page
* 	并且提供分页的底部导航栏数据
*/
public class Page4Navigator<T> {
	//jpa 传递出来的分页对象， Page4Navigator 类就是对它进行封装以达到扩展的效果
	Page<T> pageFromJPA;
	//分页的底部导航栏的数量 如[5,6,7,8,9] 该值就是5
	int navigatePages;
    //总页面数
    int totalPages;
    //当前页码
    int number;
    //总共有多少条数据
    long totalElements;
    //一页最大有多少条数据
    int size;
    //当前页有多少条数据,最后一页可能不满size
    int numberOfElements;
    //数据集合
    List<T> content;
    //是否有数据
    boolean isHasContent;
    //是否是首页
    boolean isFirst;
    //是否是尾页
    boolean isLast;
    //是否有下一页
    boolean hasNext;
    //是否有上一页
    boolean hasPrevious;
    //分页的底部导航栏的数据数组,如[4,5,6,7,8],则navigatepageNums就是这个数组,便于前端显示
    int[] navigatePageNums;
	public Page4Navigator(){
	}
	public Page4Navigator(Page<T> pageFromJPA,int navigatePages ){
		this.pageFromJPA=pageFromJPA;
		this.navigatePages=navigatePages;
		totalPages=pageFromJPA.getTotalPages();
		number=pageFromJPA.getNumber();//索引从0开始
		totalElements=pageFromJPA.getTotalElements();
		size=pageFromJPA.getSize();
		numberOfElements=pageFromJPA.getNumberOfElements();
		content=pageFromJPA.getContent();
		isFirst=pageFromJPA.isFirst();
		isLast=pageFromJPA.isLast();
		hasNext=pageFromJPA.hasNext();
		hasPrevious=pageFromJPA.hasPrevious();
		calcNavigatePageNums();
	}
	
	//用于计算navigatePageNums数组的值 	 [1,2,3,4,5]
	public void calcNavigatePageNums() {
		int[] navigatePageNums;
		int totalPages=getTotalPages();
		int num=getNumber();
		
		//如果总页数小于导航栏页码数时:即无法显示完的数据[1,2,3,4,5] 只能显示 [1,2,3,4]
		if(totalPages<=navigatePages) {
			navigatePageNums=new int[totalPages];
			for(int i=0;i<totalPages;i++) {
				navigatePageNums[i]=i+1;//赋值为[1,2,3,4]
			}
		}else {//总页数大于导航栏页码数:即可以显示 五条页码[2,3,4,5,6]
			//又分为三种情况 一:当前页为1,2,3页时,显示的都是[1,2,3,4,5]页码
			//				二:最后的三页也是同理
			//				三:中间的所有页
			navigatePageNums=new int[navigatePages];
			int startNum = num - navigatePages/2;
			int endNum = num + navigatePages/2;
			if(startNum<1) {
				startNum=1;
				for(int i=0;i<navigatePages;i++) {
					navigatePageNums[i]=startNum++;//赋值为[1,2,3,4,5]
				}
			}else if(endNum>totalPages) {
				for(int i=navigatePages-1;i>=0;i--) {
					navigatePageNums[i]=endNum--;//赋值为[,6,7,8,9,10]
				}
			}else {
				for(int i=0;i<navigatePages;i++) {
					navigatePageNums[i]=startNum++;//赋值为[3,4,5,6,7]
				}
			}
		}	 
		this.navigatePageNums=navigatePageNums;
		
	}
	
	
	public int getNavigatePages() {
		return navigatePages;
	}
	public void setNavigatePages(int navigatePages) {
		this.navigatePages = navigatePages;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getNumberOfElements() {
		return numberOfElements;
	}
	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	public boolean isHasContent() {
		return isHasContent;
	}
	public void setHasContent(boolean isHasContent) {
		this.isHasContent = isHasContent;
	}
	public boolean isFirst() {
		return isFirst;
	}
	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}
	public boolean isLast() {
		return isLast;
	}
	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	public boolean isHasPrevious() {
		return hasPrevious;
	}
	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}
	public int[] getNavigatepageNums() {
		return navigatePageNums;
	}
	public void setNavigatepageNums(int[] navigatepageNums) {
		this.navigatePageNums = navigatepageNums;
	}
	
	
}
