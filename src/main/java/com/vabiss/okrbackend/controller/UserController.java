package com.vabiss.okrbackend.controller;

import com.vabiss.okrbackend.dto.ResetPasswordDto;
import com.vabiss.okrbackend.dto.SuccessResponseDto;
import com.vabiss.okrbackend.dto.UserDto;
import com.vabiss.okrbackend.dto.UserFormDto;
import com.vabiss.okrbackend.entity.User;
import com.vabiss.okrbackend.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/workspaces/{workspaceId}")
    public ResponseEntity<SuccessResponseDto> getUsersByWorkspaceId(@PathVariable int workspaceId) {
        List<User> users = userService.findUsersByWorkspaceId(workspaceId);
        List<UserDto> userDtos = users.stream().map(userService::convertToUserDto).toList();
        return ResponseEntity.ok(SuccessResponseDto.of("Users of workspace", userDtos));
    }

    @PutMapping("/reset-password")
    public ResponseEntity<SuccessResponseDto> updatePassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        return ResponseEntity.ok(SuccessResponseDto.of(userService.updatePassword(resetPasswordDto.getVerificationToken(), resetPasswordDto.getNewPassword())));
    }

    @PutMapping("/{userId}/fullname")
    public ResponseEntity<SuccessResponseDto> updateDisplayName(@PathVariable int userId,
                                                                @RequestBody UserDto userDto) {
        User user = userService.updateFullName(userId, userDto.getFullName());
        UserDto userDto2 = userService.convertToUserDto(user);
        return ResponseEntity.ok(SuccessResponseDto.of("Display name updated!", userDto2));
    }

    @PutMapping("/{userId}/avatar")
    public ResponseEntity<SuccessResponseDto> updateProfilePhoto(@PathVariable int userId,
                                                                 @RequestBody UserDto userDto) {
        User user = userService.updateAvatar(userId, userDto.getAvatar());
        UserDto userDto2 = userService.convertToUserDto(user);
        return ResponseEntity.ok(SuccessResponseDto.of("Avatar updated!", userDto2));
    }

    @DeleteMapping("/remove/{userId}/{workspaceId}")
    public void removeTeamMemberAndViewer(@PathVariable int userId, @PathVariable int workspaceId) {
        userService.deleteTeamMemberAndViewer(userId, workspaceId);

    }

    @PostMapping("/add/{userId}/{workspaceId}")
    public void addMemberAndViewer(@PathVariable int userId, @PathVariable int workspaceId) {
        userService.addTeamMemberAndViewer(userId, workspaceId);
    }

    @PostMapping("/{organizationId}")
    public ResponseEntity<SuccessResponseDto> createUser(@PathVariable int organizationId,
                                                         @RequestBody UserFormDto userFormDto) {
        User user = userService.createUser(organizationId, userFormDto);
        UserFormDto userFormDto1 = userService.convertToUserFormDto(user);
        return ResponseEntity.ok(SuccessResponseDto.of("User created!", userFormDto1));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{userId}")
    public void removeUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }

}
