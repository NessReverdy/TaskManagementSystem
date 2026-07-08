package org.nessrev.authservice.outbox.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.nessrev.authservice.dto.UserCreatedEvent;
import org.nessrev.authservice.outbox.entity.OutboxEvent;
import org.nessrev.authservice.outbox.repo.OutboxRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class OutboxPublisher {
  private final OutboxRepository outboxRepository;
  private final KafkaTemplate<String, Object> kafkaTemplate;
  private final ObjectMapper objectMapper;

  @Scheduled(fixedDelay = 500)
  public void publishEvents() throws
    JsonProcessingException,
    ExecutionException,
    InterruptedException
  {
    List<OutboxEvent> events = outboxRepository.findAllByProcessedFalse();

    for (OutboxEvent event : events) {
      UserCreatedEvent userEvent =
        objectMapper.readValue(
          event.getPayload(),
          UserCreatedEvent.class
        );

      kafkaTemplate.send(
        "user-created-topic",
        userEvent
      ).get();

      event.setProcessed(true);
      outboxRepository.save(event);
    }
  }
}
