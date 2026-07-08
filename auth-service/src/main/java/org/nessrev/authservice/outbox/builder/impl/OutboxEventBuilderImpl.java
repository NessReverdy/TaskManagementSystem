package org.nessrev.authservice.outbox.builder.impl;

import org.nessrev.authservice.enums.OutboxEventType;
import org.nessrev.authservice.outbox.builder.OutboxEventBuilder;
import org.nessrev.authservice.outbox.entity.OutboxEvent;

import java.time.LocalDateTime;

public class OutboxEventBuilderImpl implements OutboxEventBuilder {
  private OutboxEventType eventType;
  private String payload;
  private LocalDateTime createdAt;
  private boolean processed;

  @Override
  public OutboxEventBuilder eventType(OutboxEventType eventType) {
    this.eventType = eventType;
    return this;
  }

  @Override
  public OutboxEventBuilder payload(String payload) {
    this.payload = payload;
    return this;
  }

  @Override
  public OutboxEventBuilder createdAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  @Override
  public OutboxEventBuilder processed(boolean processed) {
    this.processed = processed;
    return this;
  }

  @Override
  public OutboxEvent build() {
    OutboxEvent event = new OutboxEvent();

    event.setEventType(eventType);
    event.setPayload(payload);
    event.setCreatedAt(createdAt);
    event.setProcessed(processed);

    return event;
  }
}
