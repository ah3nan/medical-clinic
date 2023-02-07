package com.ah3nan.medicalclinic.dto;

import lombok.NoArgsConstructor;
import java.util.List;


@NoArgsConstructor
public class AppointmentResponse {
    private List<AppointmentDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public List<AppointmentDTO> content() {
        return content;
    }

    public AppointmentResponse setContent(List<AppointmentDTO> content) {
        this.content = content;
        return this;
    }

    public int pageNo() {
        return pageNo;
    }

    public AppointmentResponse setPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public int pageSize() {
        return pageSize;
    }

    public AppointmentResponse setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public long totalElements() {
        return totalElements;
    }

    public AppointmentResponse setTotalElements(long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public int totalPages() {
        return totalPages;
    }

    public AppointmentResponse setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public boolean last() {
        return last;
    }

    public AppointmentResponse setLast(boolean last) {
        this.last = last;
        return this;
    }
}
