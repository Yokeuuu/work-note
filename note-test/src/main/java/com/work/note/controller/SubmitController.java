package com.work.note.controller;


import com.work.note.annotation.NoRepeatSubmit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubmitController {

    @GetMapping("/submit")
    @NoRepeatSubmit()
    public void submit() {
        System.out.println(System.currentTimeMillis()+"请求成功！");
    }
}
