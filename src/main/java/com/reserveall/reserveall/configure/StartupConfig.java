package com.reserveall.reserveall.configure;

import com.reserveall.reserveall.dto.request.UserRequestDto;
import com.reserveall.reserveall.model.Role;
import com.reserveall.reserveall.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartupConfig implements CommandLineRunner {

    private final UserService userService;

    public StartupConfig(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {
//        userService.createUser(
//                new UserRequestDto("IronPawn", "qalibismayilli8@gmail.com", "1234", Role.ADMIN ));
    }
}
