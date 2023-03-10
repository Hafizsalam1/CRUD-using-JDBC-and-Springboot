package org.example.Util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RandomUUID {
    public String Random() {
        return UUID.randomUUID().toString();
    }
}


