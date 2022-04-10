package com.konak.goodgames.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @GetMapping("/application/test")
  public String test() {
    return "Hello world!";
  }
}
