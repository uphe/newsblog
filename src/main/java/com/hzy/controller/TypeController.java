package com.hzy.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hzy.pojo.Type;
import com.hzy.service.TypeService;
import com.hzy.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TypeController {
    @Autowired
    private TypeService typeService;

    @RequestMapping("/gettype/{userId}")
    public List<String> getTypeByUserId(@PathVariable("userId") int userId) {

        List<String> types = typeService.selectTypeNameByUserId(userId);

        return types;
    }
    
}
