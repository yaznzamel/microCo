// package com.yaznzamel.microco.service;

// import java.io.BufferedReader;
// import java.io.InputStreamReader;

// import org.springframework.stereotype.Service;
// import com.yaznzamel.microco.MicrocoApplication;
// import com.yaznzamel.microco.controller.CompilerController;
// import com.yaznzamel.microco.model.CodeRequest;
// import com.yaznzamel.microco.model.CodeResponse;

// @Service
// public class CodeExecutionService{

//     // private final MicrocoApplication microcoApplication;

//     // private final CompilerController compilerController;


//     // CodeExecutionService(CompilerController compilerController, MicrocoApplication microcoApplication) {
//     //     this.compilerController = compilerController;
//     //     this.microcoApplication = microcoApplication;
//     // }


//     public CodeResponse execute(CodeRequest request){

//         try {
//             ProcessBuilder processBuilder;

//             if(request.getLanguage().toLowerCase().equals("java")){
//                 processBuilder = new ProcessBuilder("docker", "run" , "--rm" , "openjdk:17" , "java" ,"-e" , request.getCode());
//             }

//             else if(request.getLanguage().toLowerCase().equals("python")){
//                 processBuilder = new ProcessBuilder("docker" , "run" , "--rm" , "python:3.9" , "python", "-c" , request.getCode());
//             }

//             else{
//                 return new CodeResponse("Unsupported language" , "error");
//             }


//             // starting the processs
//             Process process = processBuilder.start();
//             BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

//             StringBuilder output = new StringBuilder();

//             String line;

//             while((line = reader.readLine()) != null){
                
//                 output.append(line);
//             }

//             process.waitFor();
//             return new CodeResponse(line, "success");


//         } catch (Exception e) {
            
//             return new CodeResponse("Error while executing the code" + e.getMessage(),"error");
    
//         }
//     }
// }



package com.yaznzamel.microco.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.springframework.stereotype.Service;
import com.yaznzamel.microco.model.CodeRequest;
import com.yaznzamel.microco.model.CodeResponse;

@Service
public class CodeExecutionService {

    public CodeResponse execute(CodeRequest request) {

        try {
            ProcessBuilder processBuilder;

            // Ensure correct language processing
            if (request.getLanguage().equalsIgnoreCase("java")) {
                processBuilder = new ProcessBuilder("docker", "run", "--rm", "openjdk:17",
                        "sh", "-c", "echo '" + request.getCode() + "' > Main.java && javac Main.java && java Main");
            } 

            else if (request.getLanguage().equalsIgnoreCase("python")) {
                processBuilder = new ProcessBuilder("docker", "run", "--rm", "python:3.9",
                        "python", "-c", request.getCode());
            } 

            else {
                return new CodeResponse("Unsupported language", "error");
            }

            // Start the process
            Process process = processBuilder.start();

            // Capture output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            process.waitFor();

            // Capture error output if any
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder errorOutput = new StringBuilder();
            while ((line = errorReader.readLine()) != null) {
                errorOutput.append(line).append("\n");
            }

            // If there is an error output, return it instead of normal output
            if (!errorOutput.toString().isEmpty()) {
                return new CodeResponse(errorOutput.toString(), "error");
            }

            return new CodeResponse(output.toString().trim(), "success");

        } catch (Exception e) {
            return new CodeResponse("Execution Error: " + e.getMessage(), "error");
        }
    }
}
