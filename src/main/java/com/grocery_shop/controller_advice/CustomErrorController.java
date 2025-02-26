package com.grocery_shop.controller_advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

    @RequestMapping
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Something went wrong");
        response.put("status", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        return ResponseEntity.status((Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).body(response);
    }

    public String getErrorPath() {
        return "/error";
    }
}
