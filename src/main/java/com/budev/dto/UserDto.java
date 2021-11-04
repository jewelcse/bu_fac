package com.budev.dto;

public class UserDto {

    private String username;
    private String first_name;
    private String last_name;
    private String cell_phone;
    private String designation;
    private String department;
    private String highest_education_level;
    private String email;
    private String password;
    private int authorityId;

    public UserDto() {
    }


    public UserDto(String username, String first_name, String last_name, String cell_phone, String designation, String department, String highest_education_level, String email, String password, int authorityId) {
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.cell_phone = cell_phone;
        this.designation = designation;
        this.department = department;
        this.highest_education_level = highest_education_level;
        this.email = email;
        this.password = password;
        this.authorityId = authorityId;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCell_phone() {
        return cell_phone;
    }

    public void setCell_phone(String cell_phone) {
        this.cell_phone = cell_phone;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getHighest_education_level() {
        return highest_education_level;
    }

    public void setHighest_education_level(String highest_education_level) {
        this.highest_education_level = highest_education_level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
