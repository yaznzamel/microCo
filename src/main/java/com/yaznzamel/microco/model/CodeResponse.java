package com.yaznzamel.microco.model;

public class CodeResponse {
    private String output;
    private String status;




    public CodeResponse(String output, String status) {
        this.output = output;
        this.status = status;
    }

    
    public String getOutput() {
        return this.output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;

   
    }



}
