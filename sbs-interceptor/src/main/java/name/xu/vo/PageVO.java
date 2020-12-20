package name.xu.vo;

import java.util.List;


public class PageVO<T> {
    private int id;
    private int pageNum;
    private int pageSize;
    private int index;
    private int totalCount;
    private int pageCount;
    private List<T> list;

    public PageVO(int pageNum, int pageSize, int totalCount) {
        this.pageNum = pageNum;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
    }
    public PageVO(int id,int pageNum, int pageSize, int totalCount) {
        this.setId(id);
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getIndex() {
        return index = (pageNum - 1) * pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getPageCount() {
        return pageCount = totalCount % pageSize == 0 ? (totalCount / pageSize) : (totalCount / pageSize + 1);
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
