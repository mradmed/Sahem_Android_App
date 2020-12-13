package com.example.sahem_application.Model;

public class Organization {

    private String idOrg;
    private String idUser;
    private String orgName;
    private String description;
    private String address;
    private String location;
    private String phone;
    private String socials;
    private String brCertificate;
    private String orgImage;
    private String status;

    public Organization() {
    }

    public Organization(String idOrg, String idUser, String orgName, String description, String address, String location,
                        String phone, String socials, String brCertificate, String orgImage, String status) {
        this.idOrg = idOrg;
        this.idUser = idUser;
        this.orgName = orgName;
        this.description = description;
        this.address = address;
        this.location = location;
        this.phone = phone;
        this.socials = socials;
        this.brCertificate = brCertificate;
        this.orgImage = orgImage;
        this.status = status;
    }

    public String getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(String idOrg) {
        this.idOrg = idOrg;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSocials() {
        return socials;
    }

    public void setSocials(String socials) {
        this.socials = socials;
    }

    public String getBrCertificate() {
        return brCertificate;
    }

    public void setBrCertificate(String brCertificate) {
        this.brCertificate = brCertificate;
    }

    public String getOrgImage() {
        return orgImage;
    }

    public void setOrgImage(String orgImage) {
        this.orgImage = orgImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Organisation{" +
                "idOrg='" + idOrg + '\'' +
                ", idUser='" + idUser + '\'' +
                ", orgName='" + orgName + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                ", socials='" + socials + '\'' +
                ", brCertificate='" + brCertificate + '\'' +
                ", orgImage='" + orgImage + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}
