package com.yaznzamel.microco.model;

public class CodeRequest {
    private String language;
    private String code;



    public CodeRequest(String language, String code) {
        this.language = language;
        this.code = code;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}