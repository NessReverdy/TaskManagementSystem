package org.nessrev.authservice.jwt.service.hash;

public interface TokenHashService {
  String hash(String token);
}
