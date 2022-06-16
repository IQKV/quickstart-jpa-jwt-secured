package org.ujar.basics.restful.jwtauth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.ujar.basics.restful.jwtauth.model.User;

/**
 * DTO class for user requests by ROLE_USER
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
  private Long id;
  private String username;
  private String firstName;
  private String lastName;
  private String email;

  public static UserDto fromUser(User user) {
    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setUsername(user.getUsername());
    userDto.setFirstName(user.getFirstName());
    userDto.setLastName(user.getLastName());
    userDto.setEmail(user.getEmail());

    return userDto;
  }

  public User toUser() {
    User user = new User();
    user.setId(id);
    user.setUsername(username);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);

    return user;
  }
}
