package com.springboot.demo.config.PageHelper;

import java.io.Serializable;
import java.util.List;


/**
 * Create By SINYA
 * Description: PageBean
 */
public class PageBean<T> implements Serializable {

    private long count;//总记录数
    private List<T> result;//结果集
    private int pageNum;//第几页
    private int pageSize;//每页记录数
    private int pages;//总页数
    private int size;//当前页数量

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
