package com.example.demo.common.share;

import com.github.pagehelper.Page;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */
public class PageInfo<T>{

    //当前页
    private int pageNum;
    //每页显示的总条数
    private int pageSize;
    //总条数
    private long total;
    //总页数
    private int pages;
    // 分页结果
    private List<T> list;
    //是否为第一页
    private boolean isFirstPage = false;
    //是否为最后一页
    private boolean isLastPage = false;

    public PageInfo() {
    }

    public PageInfo(List<T> list){
        if(list instanceof Page){
            Page page = (Page)list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.pages = page.getPages();
            this.list = page;
            this.total = page.getTotal();
        }else if (list instanceof Collection){
            this.pageNum = 1;
            this.pageSize = list.size();
            this.pages=1;
            this.list = list;
            this.total = list.size();
        }
        if (list instanceof Collection){
            judgePageBoundary();
        }
    }

    private void judgePageBoundary(){
        this.isFirstPage = pageNum==1;
        this.isLastPage = pageNum==pages;
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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }
}
