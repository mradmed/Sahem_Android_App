package com.example.sahem_application.Model;

public class Campaign {

    public int idCmp;
    public int idOrg;
    public String cmpName;
    public String description;
    public double target;
    public double raised;
    public String imgUrl;
    public String creationDate;
    public String endDate;
    public int nbDons;

    public int getNbDons() {
        return nbDons;
    }

    public void setNbDons(int nbDons) {
        this.nbDons = nbDons;
    }



    public Campaign() {

    }

    public void setIdCmp(int idCmp) {
        this.idCmp = idCmp;
    }

    public void setIdOrg(int idOrg) {
        this.idOrg = idOrg;
    }

    public void setCmpName(String cmpName) {
        this.cmpName = cmpName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public void setRaised(double raised) {
        this.raised = raised;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getIdCmp() {
        return idCmp;
    }

    public int getIdOrg() {
        return idOrg;
    }

    public String getCmpName() {
        return cmpName;
    }

    public String getDescription() {
        return description;
    }

    public double getTarget() {
        return target;
    }

    public double getRaised() {
        return raised;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return Status;
    }

    public String Status;
}
