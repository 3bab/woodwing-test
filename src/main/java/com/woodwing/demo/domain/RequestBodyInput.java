package com.woodwing.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestBodyInput {
    private Integer summandOneValue;
    private String summandOneType;
    private Integer summandTwoValue;
    private String summandTwoType;
    private String sumType;

    public RequestBodyInput() {
    }

    public RequestBodyInput(@JsonProperty("summand_one_value") Integer summandOneValue,
                            @JsonProperty("summand_one_type") String summandOneType,
                            @JsonProperty("summand_two_value") Integer summandTwoValue,
                            @JsonProperty("summand_two_type") String summandTwoType,
                            @JsonProperty("sum_type") String sumType) {
        this.summandOneValue = summandOneValue;
        this.summandOneType = summandOneType;
        this.summandTwoValue = summandTwoValue;
        this.summandTwoType = summandTwoType;
        this.sumType = sumType;
    }

    public Integer getSummandOneValue() {
        return summandOneValue;
    }

    public void setSummandOneValue(Integer summandOneValue) {
        this.summandOneValue = summandOneValue;
    }

    public String getSummandOneType() {
        return summandOneType;
    }

    public void setSummandOneType(String summandOneType) {
        this.summandOneType = summandOneType;
    }

    public Integer getSummandTwoValue() {
        return summandTwoValue;
    }

    public void setSummandTwoValue(Integer summandTwoValue) {
        this.summandTwoValue = summandTwoValue;
    }

    public String getSummandTwoType() {
        return summandTwoType;
    }

    public void setSummandTwoType(String summandTwoType) {
        this.summandTwoType = summandTwoType;
    }

    public String getSumType() {
        return sumType;
    }

    public void setSumType(String sumType) {
        this.sumType = sumType;
    }
}
