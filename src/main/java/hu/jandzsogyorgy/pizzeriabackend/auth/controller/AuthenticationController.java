package hu.jandzsogyorgy.pizzeriabackend.auth.controller;

import hu.jandzsogyorgy.pizzeriabackend.auth.dto.*;
import hu.jandzsogyorgy.pizzeriabackend.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public AuthenticationResponseDto login(@RequestBody AuthenticationRequestDto dto) {
        return authenticationService.login(dto);
    }



    @PostMapping("/logout")
    public LogoutResponseDto logout(@RequestBody LogoutRequestDto logoutRequest,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        return authenticationService.logout(logoutRequest, userDetails);
    }

//    @PostMapping("/refresh")
//    public AuthenticationResponseDto refreshToken(@RequestBody RefreshTokenRequestDto dto) {
//        return authenticationService.refreshToken(dto);
//    }


}
