package com.lf.controller;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class SysUserTest {
    @GetMapping
    public String user(@RequestParam(value = "key",required = false) String key){
        String time = LocalDateTime.now().toString();
        if (ObjectUtil.isNull(key)){
            return "hello"+time;
        }
        return "hello"+key+time;
    }
}
