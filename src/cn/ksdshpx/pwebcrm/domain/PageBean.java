package cn.ksdshpx.pwebcrm.domain;

import java.util.List;

/**
 * @author peng.x
 * @date 2018年10月1日 下午9:42:49 分页Bean
 */
public class PageBean<T> {
	private Integer pageNow;// 当前页
	private Integer pageSize;// 每页记录数
	private Integer pageCount;// 总页数
	private Integer rowCount;// 总记录
	private List<T> beanList;// 当前页数据

	public Integer getPageNow() {
		return pageNow;
	}

	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 计算总页数
	 * 
	 * @return
	 */
	public Integer getPageCount() {
		Integer pageCount = rowCount / pageSize;
		return (rowCount % pageSize == 0) ? pageCount : pageCount + 1;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public List<T> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
}
