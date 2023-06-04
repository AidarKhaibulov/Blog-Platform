package com.ms;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping()
    public String validateToken() {
        return "hello from secured demo!";
    }
}
