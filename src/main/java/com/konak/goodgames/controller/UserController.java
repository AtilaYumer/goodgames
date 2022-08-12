package com.konak.goodgames.controller;

import com.konak.goodgames.domain.dto.UserDto;
import com.konak.goodgames.domain.dto.UserInfoDto;
import com.konak.goodgames.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping
  public List<UserInfoDto> getUsers() {
    return userService.getUsers();
  }

  @ResponseStatus(code = HttpStatus.CREATED)
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public void register(@RequestBody UserDto userDto) {
    userService.register(userDto);
  }

  @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
  public void login(@RequestBody UserDto userDto, HttpServletResponse response) {
    userService.login(userDto, response);
  }

  @GetMapping(path = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserInfoDto getUserInfo() {
    return userService.getUserInfo();
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping(path = "/{userId}/role")
  public void updateUserRole(@RequestBody UserInfoDto userInfoDto,
                             @PathVariable long userId) {
    userService.updateUserRole(userId, userInfoDto);
  }
}
