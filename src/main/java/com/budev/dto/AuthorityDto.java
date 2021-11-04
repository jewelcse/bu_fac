package com.budev.dto;

public class AuthorityDto {

    private String authorityName;

    public AuthorityDto() {
    }

    public AuthorityDto(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }
}
