package com.techbooker.auth.rest;

import com.techbooker.auth.dto.Role;
import com.techbooker.auth.dto.UserDto;
import com.techbooker.auth.util.constance.ControllerConstance;
import com.techbooker.auth.model.User;
import com.techbooker.auth.service.UserService;
import com.techbooker.sm.util.dto.ResponseDto;
import com.techbooker.auth.util.converter.EntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ControllerConstance.API_V1)
@Slf4j
public class UserController {
    private final UserService userService;
    private final EntityConverter entityConverter;

    @PostMapping(ControllerConstance.PUBLIC + ControllerConstance.REGISTER)
    public ResponseDto<UserDto> register(@RequestBody UserDto user) {
        return saveUser(user, Role.ROLE_USER);
    }

    @PostMapping(ControllerConstance.ADMIN + ControllerConstance.REGISTER)
    public ResponseDto<UserDto> registerAdmin(@RequestBody UserDto user) {
        return saveUser(user, Role.ROLE_ADMIN);
    }

    @GetMapping(ControllerConstance.PUBLIC + ControllerConstance.LOGOUT)
    public void logout() {
//TODO invalidate token
    }

    private ResponseDto<UserDto> saveUser(UserDto user, Role role) {
        try {
            userService.saveUser(user, role);
            //TODO send confirmation email
            return new ResponseDto<UserDto>().buildSuccessMsgWithData(user);
        } catch (RoleNotFoundException rnfe) {
            log.error("Unable to assign role to username : {}", user.getUsername());
            return new ResponseDto<UserDto>().buildFailureMsg("Unable to register to the system try again later!", rnfe);
        } catch (Exception de) {
            log.error("User already exist : {}", user.getUsername());
            return new ResponseDto<UserDto>().buildFailureMsg("User already exist!", de);
        }
    }
}