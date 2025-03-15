package com.yaznzamel.microco.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "execution_history")
public class ExecutionHistory {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    private String language;

    @Column(columnDefinition = "TEXT")
    private String code;


    @Column(columnDefinition = "TEXT")
    private String output;


    @Column(name = "execution_status")
    private String status;
    private LocalDateTime executedAt = LocalDateTime.now();



    public ExecutionHistory() {
    }
    


    public ExecutionHistory(String language, String code, String output, String status) {
        this.language = language;
        this.code = code;
        this.output = output;
        this.status = status;    
    }


    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public LocalDateTime getExecutedAt() {
        return this.executedAt;
    }

    public void setExecutedAt(LocalDateTime executedAt) {
        this.executedAt = executedAt;
    }

}