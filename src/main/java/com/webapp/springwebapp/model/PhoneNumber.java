package com.webapp.springwebapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "phone_number")
public class PhoneNumber {

    @Id
    @Column(name = "id_phone_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "owner_firstname")
    private String ownerFirstname;

    @Column(name = "owner_lastname")
    private String ownerLastname;

    @ManyToOne
    @JoinColumn(name = "id_phone_plan")
    private PhonePlan phonePlan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOwnerFirstname() {
        return ownerFirstname;
    }

    public void setOwnerFirstname(String ownerFirstname) {
        this.ownerFirstname = ownerFirstname;
    }

    public String getOwnerLastname() {
        return ownerLastname;
    }

    public void setOwnerLastname(String ownerLastname) {
        this.ownerLastname = ownerLastname;
    }

    public PhonePlan getPhonePlan() {
        return phonePlan;
    }

    public void setPhonePlan(PhonePlan phonePlan) {
        this.phonePlan = phonePlan;
    }


}
