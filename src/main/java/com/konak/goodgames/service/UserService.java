package com.konak.goodgames.service;

import com.konak.goodgames.domain.dto.UserDto;
import com.konak.goodgames.domain.dto.UserInfoDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {
  void register(UserDto userDto);

  void login(UserDto userDto, HttpServletResponse response);

  UserInfoDto getUserInfo();

  List<UserInfoDto> getUsers();

  void updateUserRole(long userId, UserInfoDto userInfoDto);
}
