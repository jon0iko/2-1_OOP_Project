package org.example.salon.Database.Model;

public class Service {
    private int service_id;
    private String name;
    private double price;
    private String category;
    private int duration;

    public Service(String name, double price, int duration) {
        this.name = name;
        this.price = price;
        // this.category = category;
        this.duration = duration;
    }

    public Service(int service_id, String name, double price) {
        this.name = name;
        this.price = price;
        this.service_id = service_id;
    }

    public Service(int service_id, String name, double price, int duration, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.duration = duration;
        this.service_id = service_id;
    }

    public int getServiceID() {
        return service_id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getDuration() {
        return duration;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}


