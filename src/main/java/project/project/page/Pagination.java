package project.project.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.Data;

@Data
public class Pagination {
	private int currentPage;
	private final int size = 6;
	private int totalPage;
	private int totalRecord;
	private boolean hasPreviousPage;
	private boolean hasNextPage;

	Logger log = LoggerFactory.getLogger(this.getClass());

	public Pagination(int totalRecord) {
		this.currentPage = 0;
		this.totalRecord = totalRecord;
		this.totalPage = (totalRecord % size == 0) ? totalRecord / size : totalRecord / size + 1;
		this.hasPreviousPage = false;
		this.hasNextPage = (totalPage > currentPage) ? true : false;
	}

	public boolean chkCurrentPage(String currentPage) {
		if (isExist(currentPage)) {
			if (isDigit(currentPage)) {
				this.currentPage = Integer.parseInt(currentPage);
				this.hasPreviousPage = (getCurrentPage() > 0) ? true : false;
				this.hasNextPage = (totalPage > getCurrentPage()) ? true : false;
				return true;
			}
		}
		return false;
	}

	public boolean isDigit(String currentPage) {
		for (int idx = 0; idx < currentPage.length(); idx++) {
			char tmp = currentPage.charAt(idx);
			if (!(tmp >= '0' && tmp <= '9')) {
				return false;
			}
		}
		return true;
	}

	public boolean isExist(String currentPage) {
		if (StringUtils.isEmpty(currentPage)) {
			return false;
		} else {
			return true;
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getSize() {
		return size;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public boolean hasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public boolean hasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
}
