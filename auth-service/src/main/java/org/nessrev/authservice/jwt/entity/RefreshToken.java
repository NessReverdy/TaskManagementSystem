package org.nessrev.authservice.jwt.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "refresh_tokens", timeToLive = 604800)
@Getter
@Setter
public class RefreshToken {
  @Id
  private String tokenHash;

  @Indexed
  private Long userId;
}