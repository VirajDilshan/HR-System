package com.epsychiatry.pojo;

public class FBPaging {
    private String next;
    private String previous;

    public FBPaging() {
    }

    public FBPaging(String next, String previous) {
        this.next = next;
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }
}
