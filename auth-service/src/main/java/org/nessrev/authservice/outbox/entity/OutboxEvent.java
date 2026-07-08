package org.nessrev.authservice.outbox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.nessrev.authservice.enums.OutboxEventType;
import org.nessrev.authservice.outbox.builder.OutboxEventBuilder;
import org.nessrev.authservice.outbox.builder.impl.OutboxEventBuilderImpl;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name ="outbox_events")
public class OutboxEvent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private OutboxEventType eventType;

  @Column(columnDefinition = "TEXT")
  private String payload;

  private LocalDateTime createdAt;

  private boolean processed;

  public static OutboxEventBuilder build() {
    return new OutboxEventBuilderImpl();
  }
}
