package com.itheima.pojo;

import java.util.List;

//��ҳ��ѯ��JavaBean
public class PageBean<T> {
    // �ܼ�¼��
    private int totalCount;
    // ��ǰҳ����
    private List<T> rows;


    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
