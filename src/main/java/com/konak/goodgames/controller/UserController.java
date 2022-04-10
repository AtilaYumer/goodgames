package com.konak.goodgames.controller;

import com.konak.goodgames.domain.dto.UserDto;
import com.konak.goodgames.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @ResponseStatus(code = HttpStatus.CREATED)
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public void register(@RequestBody UserDto userDto) {
    userService.register(userDto);
  }
}
