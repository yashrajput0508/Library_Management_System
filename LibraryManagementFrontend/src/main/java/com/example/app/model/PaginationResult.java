package com.example.app.model;

import java.util.List;

public class PaginationResult<T> {
	
	private List<T> items;
    private int currentPage;
    private int totalItems;
    private int pageSize;

    public PaginationResult(List<T> items, int currentPage, int totalItems, int pageSize) {
        this.items = items;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.pageSize = pageSize;
    }

	public List<T> getitems() {
		return items;
	}

	public void setitems(List<T> items) {
		this.items = items;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int gettotalItems() {
		return totalItems;
	}

	public void settotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "PaginationResult [items=" + items + ", currentPage=" + currentPage + ", totalItems=" + totalItems
				+ ", pageSize=" + pageSize + "]";
	}
}
