package com.hzy.controller;

import com.hzy.service.TypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "分类", description = "分类controller")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/gettype/{userId}")
    @Operation(summary = "通过用户id获取类别")
    public List<String> getTypeByUserId(@PathVariable("userId") int userId) {

        List<String> types = typeService.selectTypeNameByUserId(userId);

        return types;
    }

}
