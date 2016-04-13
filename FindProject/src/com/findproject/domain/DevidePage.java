package com.findproject.domain;

import com.findproject.DB.DBConnection;

public class DevidePage {
  private int pageSize;// 每页显示的条数
  private int recordCount;// 记录的总条数
  private int currentPage;// 当前页
  private int pageCount;// 总页数
  private Boolean bto = true;	//判斷是否加載完畢

  public DevidePage(int pageSize, int recordCount, int currentPage) {
    this.pageSize = pageSize;
    this.recordCount = recordCount;
    this.setCurrentPage(currentPage);
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getRecordCount() {
    return recordCount;
  }

  public void setRecordCount(int recordCount) {
    this.recordCount = recordCount;
  }

  /**
   * 获得总页数
   */
  public int getPageCount() {
    pageCount = recordCount / pageSize;
    int mod = recordCount % pageSize;
    if (mod != 0) {
      pageCount++;
    }
    return pageCount == 0 ? 1 : pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  /**
   * 设置定位在当前页
   */
  private void setCurrentPage(int currentPage) {
    int activePage = currentPage <= 0 ? 1 : currentPage;
    activePage = activePage > getPageCount() ? getPageCount() : activePage;
    this.currentPage = activePage;
  }

  public int getFromIndex() {
    return (currentPage - 1) * pageSize;
  }

  public int getToIndex() {
	  if(currentPage * pageSize>recordCount){
		  this.bto = false;
	  }
//	  if(currentPage == 2){
//		  this.bto = false;
//	  }
    return Math.min(recordCount, currentPage * pageSize);
    
  }

public Boolean getBto() {
	return bto;
}

public void setBto(Boolean bto) {
	this.bto = bto;
}

}
