package src.kafka.consumers;

import com.jayway.jsonpath.JsonPath;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import src.model.Apartment;
import src.model.ApartmentsRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class KafkaEventsConsumer {

    @Autowired
    private final ApartmentsRepository apartmentsRepository;
    public static final Logger log = LoggerFactory.getLogger(KafkaEventsConsumer.class);

    @KafkaListener(topics = {"${topic.apartment.sale}"})
    public void onMessage(ConsumerRecord<String, String> consumerRecord) {
        String consumedKey = consumerRecord.key();
        String consumedMessage = consumerRecord.value();
        long offset = consumerRecord.offset();
        log.info("Received apartment sold event");
        log.info(String.format("Offset -> %s || Consumer Record {} -> %s", offset, consumedMessage));
        log.info("ConsumedKey: " + consumedKey);
        try {
            processMessage(consumedKey, consumedMessage);
        } catch (Exception e) {
            log.error("Unable to process event with offset : " + offset + " || error : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void processMessage(String consumedKey, String consumedMessage) {
        Optional<Apartment> apartment = apartmentsRepository.findById(Integer.parseInt(consumedKey));
        Optional<Boolean> isSold = Optional.of(Boolean.valueOf(JsonPath.parse(consumedMessage).read("$.Sold")));

        if (apartment.isPresent() && isSold.isPresent()) {
            apartment.get().setFree(!isSold.get());
            apartmentsRepository.save(apartment.get());
            log.info(String.format("Apartment with id %s has been sold", apartment.get().getId()));
        }
    }

}
