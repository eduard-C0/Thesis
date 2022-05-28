package com.example.musicstreaming.services

import com.example.musicstreaming.commonVO.User
import com.example.musicstreaming.services.dtos.UserDto

internal fun User.toUserDto() = UserDto(
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = password
)