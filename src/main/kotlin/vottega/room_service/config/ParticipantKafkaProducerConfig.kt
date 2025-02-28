package vottega.room_service.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import vottega.avro.ParticipantAvro

@Configuration
class ParticipantKafkaProducerConfig(
  private val kafkaCommonConfig: KafkaCommonConfig
) {

  @Bean
  fun participantProducerFactory(): ProducerFactory<Long, ParticipantAvro> {
    return DefaultKafkaProducerFactory(kafkaCommonConfig.commonProducerConfig())
  }

  @Bean
  fun participantKafkaTemplate(): KafkaTemplate<Long, ParticipantAvro> {
    return KafkaTemplate(participantProducerFactory())
  }

  @Bean
  fun makeParticipantTopic(): NewTopic {
    return NewTopic("participant", 1, 1)
  }
}