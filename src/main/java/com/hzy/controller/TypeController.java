package com.hzy.controller;

import com.hzy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/gettype/{userId}")
    public List<String> getTypeByUserId(@PathVariable("userId") int userId) {

        List<String> types = typeService.selectTypeNameByUserId(userId);

        return types;
    }
    
}
