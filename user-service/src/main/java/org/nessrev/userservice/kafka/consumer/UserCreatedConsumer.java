package org.nessrev.userservice.kafka.consumer;

import lombok.AllArgsConstructor;
import org.nessrev.userservice.dto.UserCreatedEvent;
import org.nessrev.userservice.service.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserCreatedConsumer {
  private final UserService userService;

  @KafkaListener(topics = "user-created-topic", groupId = "user-service")
  public void handleUserCreated(UserCreatedEvent event) {
    userService.createUser(event);
  }
}
