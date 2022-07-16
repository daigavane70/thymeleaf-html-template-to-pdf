package com.starter.sprint.utils;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Utility {
    public String getRandomString() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString().replaceAll("_", "").replaceAll("-", "");
    }
}
