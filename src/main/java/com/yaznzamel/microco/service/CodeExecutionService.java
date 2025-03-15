package com.yaznzamel.microco.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yaznzamel.microco.model.CodeRequest;
import com.yaznzamel.microco.model.CodeResponse;
import com.yaznzamel.microco.repository.ExecutionHistoryRepository;
import com.yaznzamel.microco.model.ExecutionHistory;

@Service
public class CodeExecutionService {

    @Autowired
    private ExecutionHistoryRepository historyRepository;  // Repository to store execution history

    public CodeResponse execute(CodeRequest request) {
        try {
            ProcessBuilder processBuilder;
            String language = request.getLanguage();
            String code = request.getCode();

            // Ensure correct language processing
            if (language.equalsIgnoreCase("java")) {
                // Escape double quotes in the code string
                String escapedCode = code.replace("\"", "\\\"");
                processBuilder = new ProcessBuilder("docker", "run", "--rm", "openjdk:17",
                    "sh", "-c", "echo \"" + escapedCode + "\" > Main.java && javac Main.java && java Main");
            }
            
            else if (language.equalsIgnoreCase("python")) {
                processBuilder = new ProcessBuilder("docker", "run", "--rm", "python:3.9",
                        "python", "-c", code);
            } 
            else {
                return new CodeResponse("Unsupported language", "error");
            }

            // Start the process
            Process process = processBuilder.start();

            // Capture standard output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Capture error output
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder errorOutput = new StringBuilder();
            while ((line = errorReader.readLine()) != null) {
                errorOutput.append(line).append("\n");
            }

            process.waitFor();

            // Determine final output (error or success)
            String finalOutput = errorOutput.toString().isEmpty() ? output.toString().trim() : errorOutput.toString().trim();
            String status = errorOutput.toString().isEmpty() ? "success" : "error";

            // Save execution history
            ExecutionHistory executionHistory = new ExecutionHistory(language, code, finalOutput, status);
            historyRepository.save(executionHistory);

            // Return response
            return new CodeResponse(finalOutput, status);

        } catch (Exception e) {
            return new CodeResponse("Execution Error: " + e.getMessage(), "error");
        }
    }
}
