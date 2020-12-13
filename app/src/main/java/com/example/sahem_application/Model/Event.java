package com.example.sahem_application.Model;

public class Event {

    private String idEvent;
    private String idOrg;
    private String eventName;
    private String description;
    private String startDate;
    private String endDate;
    private String address;
    private String location;
    private String eventBudget;
    private String eventImg;
    private String nbSponsors;
    private String status;

    public Event() {
    }

    public Event(String idEvent, String idOrg, String eventName, String description, String startDate, String endDate,
                 String address, String location, String eventBudget, String eventImg, String nbSponsors, String status) {
        this.idEvent = idEvent;
        this.idOrg = idOrg;
        this.eventName = eventName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.address = address;
        this.location = location;
        this.eventBudget = eventBudget;
        this.eventImg = eventImg;
        this.nbSponsors = nbSponsors;
        this.status = status;
    }

    public String getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    public String getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(String idOrg) {
        this.idOrg = idOrg;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventBudget() {
        return eventBudget;
    }

    public void setEventBudget(String eventBudget) {
        this.eventBudget = eventBudget;
    }

    public String getEventImg() {
        return eventImg;
    }

    public void setEventImg(String eventImg) {
        this.eventImg = eventImg;
    }

    public String getNbSponsors() {
        return nbSponsors;
    }

    public void setNbSponsors(String nbSponsors) {
        this.nbSponsors = nbSponsors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Event{" +
                "idEvent='" + idEvent + '\'' +
                ", idOrg='" + idOrg + '\'' +
                ", eventName='" + eventName + '\'' +
                ", description='" + description + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", address='" + address + '\'' +
                ", location='" + location + '\'' +
                ", eventBudget='" + eventBudget + '\'' +
                ", eventImg='" + eventImg + '\'' +
                ", nbSponsors='" + nbSponsors + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
