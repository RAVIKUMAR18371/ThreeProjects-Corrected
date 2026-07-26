package com.example.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JWTTokenResponseDTO {
   private String token;
   private String tokenType;
   private long validityDuration;
}
