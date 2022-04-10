package com.konak.goodgames.service.impl;

import com.konak.goodgames.domain.dto.UserDto;
import com.konak.goodgames.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperService extends ModelMapper {

  public ModelMapperService() {
    userDtoToUser();
  }

  private void userDtoToUser() {
    createTypeMap(UserDto.class, User.class)
        .setPostConverter(
            mappingContext -> {
              UserDto source = mappingContext.getSource();
              User destination = mappingContext.getDestination();

              BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
              String encodedPassword = bCryptPasswordEncoder.encode(source.getPassword());
              destination.setPassword(encodedPassword);
              return destination;
            });
  }
}
