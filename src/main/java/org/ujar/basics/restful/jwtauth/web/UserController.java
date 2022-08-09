package org.ujar.basics.restful.jwtauth.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ujar.basics.restful.jwtauth.dto.UserDto;
import org.ujar.basics.restful.jwtauth.entity.User;
import org.ujar.basics.restful.jwtauth.service.UserService;

@RestController
@RequestMapping(value = "/api/v1/users/")
record UserController(UserService userService) {

  @GetMapping(value = "{id}")
  ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") final Long id) {
    final User user = userService.findById(id);

    if (user == null) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    final UserDto result = UserDto.fromUser(user);

    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
