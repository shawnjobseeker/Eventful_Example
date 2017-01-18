package uk.co.example.shawn.loginapp.model.venue;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Search {

    @SerializedName("last_item")
    @Expose
    private Object lastItem;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("total_items")
    @Expose
    private String totalItems;
    @SerializedName("first_item")
    @Expose
    private Object firstItem;
    @SerializedName("page_number")
    @Expose
    private String pageNumber;
    @SerializedName("page_size")
    @Expose
    private String pageSize;
    @SerializedName("page_items")
    @Expose
    private Object pageItems;
    @SerializedName("search_time")
    @Expose
    private String searchTime;
    @SerializedName("page_count")
    @Expose
    private String pageCount;
    @SerializedName("venues")
    @Expose
    private Venues venues;

    /**
     *
     * @return
     * The lastItem
     */
    public Object getLastItem() {
        return lastItem;
    }

    /**
     *
     * @param lastItem
     * The last_item
     */
    public void setLastItem(Object lastItem) {
        this.lastItem = lastItem;
    }

    /**
     *
     * @return
     * The version
     */
    public String getVersion() {
        return version;
    }

    /**
     *
     * @param version
     * The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     *
     * @return
     * The totalItems
     */
    public String getTotalItems() {
        return totalItems;
    }

    /**
     *
     * @param totalItems
     * The total_items
     */
    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    /**
     *
     * @return
     * The firstItem
     */
    public Object getFirstItem() {
        return firstItem;
    }

    /**
     *
     * @param firstItem
     * The first_item
     */
    public void setFirstItem(Object firstItem) {
        this.firstItem = firstItem;
    }

    /**
     *
     * @return
     * The pageNumber
     */
    public String getPageNumber() {
        return pageNumber;
    }

    /**
     *
     * @param pageNumber
     * The page_number
     */
    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     *
     * @return
     * The pageSize
     */
    public String getPageSize() {
        return pageSize;
    }

    /**
     *
     * @param pageSize
     * The page_size
     */
    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    /**
     *
     * @return
     * The pageItems
     */
    public Object getPageItems() {
        return pageItems;
    }

    /**
     *
     * @param pageItems
     * The page_items
     */
    public void setPageItems(Object pageItems) {
        this.pageItems = pageItems;
    }

    /**
     *
     * @return
     * The searchTime
     */
    public String getSearchTime() {
        return searchTime;
    }

    /**
     *
     * @param searchTime
     * The search_time
     */
    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }

    /**
     *
     * @return
     * The pageCount
     */
    public String getPageCount() {
        return pageCount;
    }

    /**
     *
     * @param pageCount
     * The page_count
     */
    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    /**
     *
     * @return
     * The venues
     */
    public Venues getVenues() {
        return venues;
    }

    /**
     *
     * @param venues
     * The venues
     */
    public void setVenues(Venues venues) {
        this.venues = venues;
    }

}