package com.springboot.demo.core.model;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * Create By: SINYA
 * Create Time: 2019/2/4 12:22
 * Update Time: 2019/4/4 23:22
 * Project Name: CAMS
 * Description:Core for PageBean
 */

public class PageBean<T> implements Serializable {

    private long count;//总记录数
    private List<T> result;//结果集
    private int pageNum;//第几页
    private int pageSize;//每页记录数
    private int pages;//总页数


    /**
     * 包装Page对象
     * 若直接返回Page对象，在json处理以及其他情况下会被当成list来处理
     * 避免因此类原因出现的一些问题
     * @param list
     */
    public PageBean(List<T> list){
        if (list instanceof Page){
            Page<T> page = (Page<T>) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.count = page.getTotal();
            this.pages = page.getPages();
            this.result = page;
        }
    }

    //Getter & Setter

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

}
