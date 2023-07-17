package com.vabiss.okrbackend.service.inter;

import com.vabiss.okrbackend.dto.UserDto;
import com.vabiss.okrbackend.entity.User;

import java.util.List;

public interface UserService {

    List<User> findUsersByWorkspaceId(int workspaceId);

    String updatePassword(String verificationToken, String newPassword);

    User updateDisplayName(int userId, String newDisplayName);

    User updateAvatar(int userId, String newAvatar);

    UserDto convertToUserDto(User user);

    User getById(int userId);

    //    User getAllTeamMember(String organizationName);
    User save(User user);

    void deleteTeamMemberAndViewer(int userId, int workspaceId);

    User addTeamMemberAndViewer(int userId, int workspaceId);

}
