package com.search.counties.dto;

public class CountyResponseDto {

    private String fips;
    private String state;
    private String name;

    public CountyResponseDto(String fips, String state, String name) {
        this.fips = fips;
        this.state = state;
        this.name = name;
    }

    public String getFips() {
        return fips;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }
}
