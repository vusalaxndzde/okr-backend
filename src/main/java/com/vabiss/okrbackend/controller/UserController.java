package com.vabiss.okrbackend.controller;

import com.vabiss.okrbackend.dto.ResetPasswordDto;
import com.vabiss.okrbackend.dto.SuccessResponseDto;
import com.vabiss.okrbackend.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
public class UserController {

    private final UserService userService;

    @PutMapping("/reset-password")
    public ResponseEntity<SuccessResponseDto> updatePassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        return ResponseEntity.ok(SuccessResponseDto.of(userService.updatePassword(resetPasswordDto.getVerificationToken(), resetPasswordDto.getNewPassword())));
    }

}
