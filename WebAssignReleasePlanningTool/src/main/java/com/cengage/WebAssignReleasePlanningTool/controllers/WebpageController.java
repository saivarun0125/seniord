package com.cengage.WebAssignReleasePlanningTool.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cengage.WebAssignReleasePlanningTool.models.Assignment;

@Controller
public class WebpageController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
    
    @GetMapping("/DueDateReport")
    public String DueDateReportPage(){
        return "DueDateReport";
    }
    
    @GetMapping("/UsageReport")
    public String UsageReportPage(){
        return "UsageReport";
    }
    
    @GetMapping("/OrderOfOperations")
    public String OrderOfOperationsPage(){
        return "OrderOfOperations";
    }
}
