package com.paulo.kafka.domain.requests;


import com.paulo.kafka.domain.enums.GameType;

public record AddGameRequest(String name, GameType gameType) {
}
