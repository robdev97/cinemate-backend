package com.cinemate.backend.mapper;

import com.cinemate.backend.domain.Friend;
import com.cinemate.backend.domain.dto.FriendDto;
import com.cinemate.backend.domain.enums.FriendshipStatus;

public class FriendMapper {

    public static FriendDto mapToDto(Friend friend) {
        return FriendDto.builder()
                .id(friend.getId())
                .userId(friend.getUser().getId())
                .friendUserId(friend.getFriend().getId())
                .status(friend.getStatus().toString())
                .build();
    }

    public static Friend mapToEntity(FriendDto dto) {
        return Friend.builder()
                .id(dto.getId())
                .user(UserMapper.partialFromId(dto.getUserId()))
                .friend(UserMapper.partialFromId(dto.getFriendUserId()))
                .status(FriendshipStatus.valueOf(dto.getStatus()))
                .build();
    }
}
