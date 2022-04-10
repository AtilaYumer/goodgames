package com.konak.goodgames.service.impl;

import com.konak.goodgames.domain.dto.UserDto;
import com.konak.goodgames.domain.model.User;
import com.konak.goodgames.repository.UserRepository;
import com.konak.goodgames.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final ModelMapperService modelMapperService;

  private final UserRepository userRepository;

  @Override
  public void register(UserDto userDto) {
    User user = modelMapperService.map(userDto, User.class);
    userRepository.save(user);
  }
}
