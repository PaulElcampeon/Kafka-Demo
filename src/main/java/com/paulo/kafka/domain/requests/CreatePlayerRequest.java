package com.paulo.kafka.domain.requests;


import com.paulo.kafka.domain.enums.PlayerMood;

public record CreatePlayerRequest(String name, PlayerMood mood) {
}
