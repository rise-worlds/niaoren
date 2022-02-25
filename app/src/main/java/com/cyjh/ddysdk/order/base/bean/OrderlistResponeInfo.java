package com.cyjh.ddysdk.order.base.bean;

import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class OrderlistResponeInfo implements Serializable {
    private ArrayList<OrderInfoRespone> OrderList;
    private int lastOrderIndex;

    public int getLastOrderIndex() {
        return this.lastOrderIndex;
    }

    public void setLastOrderIndex(int i) {
        this.lastOrderIndex = i;
    }

    public ArrayList<OrderInfoRespone> getOrderList() {
        return this.OrderList;
    }

    public void setOrderList(ArrayList<OrderInfoRespone> arrayList) {
        this.OrderList = arrayList;
    }
}
