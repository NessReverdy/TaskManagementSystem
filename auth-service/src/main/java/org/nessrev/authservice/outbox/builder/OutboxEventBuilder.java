package org.nessrev.authservice.outbox.builder;

import org.nessrev.authservice.enums.OutboxEventType;
import org.nessrev.authservice.outbox.entity.OutboxEvent;

import java.time.LocalDateTime;

public interface OutboxEventBuilder {
  OutboxEventBuilder eventType(OutboxEventType eventType);
  OutboxEventBuilder payload(String payload);
  OutboxEventBuilder createdAt(LocalDateTime createdAt);
  OutboxEventBuilder processed(boolean processed);

  OutboxEvent build();

}
