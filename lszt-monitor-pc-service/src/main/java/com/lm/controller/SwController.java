package com.lm.controller;

import com.lm.base.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sw")
public class SwController {
    @Autowired
    private BaseRedisService baseRedisService;


}
