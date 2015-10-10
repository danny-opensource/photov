/*
 * Created on Jul 3, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package net.wgbv.photov.objects;

/**
 * @author William_G_Brownlow
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Page implements Photov  {

	private int currentPage = 0;
	private int totalPages = 0;

	public Page(){
		
	}
	public Page(int totalPages){
		this.setTotalPages(totalPages);
	}
	public Page(int currentPage, int totalPages){
		this.setTotalPages(totalPages);
		this.setCurrentPage(currentPage);
		
	}

	/**
	 * @return
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @return
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * @param i
	 */
	public void setCurrentPage(int i) {
		currentPage = i;
	}

	/**
	 * @param i
	 */
	public void setTotalPages(int i) {
		totalPages = i;
	}

}
