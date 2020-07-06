package ict.plan.entity.vo;

import ict.plan.entity.po.Customer;

public class CustomerVO extends Customer {

    private int [] ids;
    private int page;
    private int rows;

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
