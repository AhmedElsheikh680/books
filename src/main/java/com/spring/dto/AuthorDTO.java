package com.spring.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AuthorDTO {

    private long id;

    private String name = "";
    private String ipAddress;
    private String email = "";
    private long bookCount;
    private String imagePath;
    private Date createdDate;

    public AuthorDTO(long id, String name, String ipAddress, String email, long bookCount, String imagePath, Date createdDate) {
        this.id = id;
        this.name = (name == null) ? "" : name;
        this.ipAddress = ipAddress;
        this.email = (email == null) ? "a@a.com" : email;
        this.bookCount = bookCount;
        this.imagePath = (imagePath == null) ? "https://tse3.mm.bing.net/th?id=OIP.ikVDUSGPcd7DQ-bBaudKRAHaEK&pid=Api&P=0&h=220" : imagePath;
        this.createdDate = new Date();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = (name == null) ? "Ahmed" : name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = (email == null) ? "a@a.com" : email;
    }

    public long getBookCount() {
        return bookCount;
    }

    public void setBookCount(long bookCount) {
        this.bookCount = bookCount;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = (imagePath == null) ? "https://tse3.mm.bing.net/th?id=OIP.ikVDUSGPcd7DQ-bBaudKRAHaEK&pid=Api&P=0&h=220" : imagePath;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}

