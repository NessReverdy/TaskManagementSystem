package org.nessrev.authservice.jwt.service.hash.impl;

import org.nessrev.authservice.exception.custom.TokenHashingException;
import org.nessrev.authservice.jwt.service.hash.TokenHashService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class TokenHashServiceImpl implements TokenHashService {
  @Override
  public String hash(String token) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hashBytes = digest.digest(token.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(hashBytes);

    } catch (NoSuchAlgorithmException e) {
      throw new TokenHashingException(e);
    }
  }
}
