package com.yaznzamel.microco.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yaznzamel.microco.model.CodeRequest;
import com.yaznzamel.microco.model.CodeResponse;
import com.yaznzamel.microco.model.ExecutionHistory;
import com.yaznzamel.microco.repository.ExecutionHistoryRepository;
import com.yaznzamel.microco.service.CodeExecutionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.*;


@RestController
@RequestMapping("/api")
public class CompilerController{


    @Autowired
    private CodeExecutionService codeExecutionService;
    @Autowired
    private ExecutionHistoryRepository historyRepository;

@GetMapping("/status")
public String checkStatus() {
    return "Micro compiler service is running";
}



@PostMapping("/execute")
public CodeResponse executeCode(@RequestBody CodeRequest request) {    
    return codeExecutionService.execute(request);
}


    @GetMapping("/history")
    public List<ExecutionHistory> getHistory() {
        return historyRepository.findTop10ByOrderByExecutedAtDesc();
    }



}