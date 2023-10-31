package com.picpaysimplificado.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserType {
    @JsonProperty("comum")
    COMMON,
    @JsonProperty("lojista")
    SHOPKEEPER
}
