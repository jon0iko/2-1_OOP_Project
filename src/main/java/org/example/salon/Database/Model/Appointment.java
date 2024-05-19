package org.example.salon.Database.Model;

import java.sql.Date;
import java.sql.Time;


public class Appointment {
    private User customer;
    private User stylist;
    private Service service;
    private Date date;
    private Time time;
    private String status;

    public Appointment(User customer, User stylist, Service service, Date date, Time time, String status) {
        this.customer = customer;
        this.stylist = stylist;
        this.service = service;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public Appointment(User customer, User stylist, Service service, Date date, Time time) {
        this.customer = customer;
        this.stylist = stylist;
        this.service = service;
        this.date = date;
        this.time = time;
        this.status = "scheduled";
    }

    public User getCustomer() {
        return customer;
    }

    public User getStylist() {
        return stylist;
    }

    public Service getService() {
        return service;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public String getCustomer_phone() {
        return customer.getPhone();
    }


    public String getStatus() {
        return status;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setStylist(Employee stylist) {
        this.stylist = stylist;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
