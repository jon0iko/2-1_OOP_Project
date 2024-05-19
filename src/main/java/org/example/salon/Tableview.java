package org.example.salon;

import java.sql.Date;

public class Tableview {
    private int id;
    private String customer_name;
    private String customer_phone;
    private String service_name;
    private Date date;

    public Tableview(int id, String customer_name, String customer_phone, String service_name, Date date) {
        this.id = id;
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
        this.service_name = service_name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public String getService_name() {
        return service_name;
    }

    public Date getDate() {
        return date;
    }
}
