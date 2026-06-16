package org.nessrev.authservice.client;

import org.nessrev.authservice.dto.CreateUserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
  name = "user-service",
  url = "${services.user-service.url}"
)
public interface UserServiceClient {
  @PostMapping("/users")
  void createUser(
    @RequestBody CreateUserRequest request
  );
}
