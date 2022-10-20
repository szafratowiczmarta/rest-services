package src.kafka.consumers;

import com.jayway.jsonpath.JsonPath;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import src.model.Apartment;
import src.model.ApartmentsRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaEventsConsumer {

    @Autowired
    private final ApartmentsRepository apartmentsRepository;

    @KafkaListener(topics = {"${topic.apartment.sale}"})
    public void receiveApartmentSoldRecord(ConsumerRecord<String, String> consumerRecord) {
        log.info("Received apartment sold event");
        try {
            processApartmentSoldRecord(consumerRecord);
        } catch (Exception e) {
            log.error(String.format("Unable to process event with offset: %s, error: %s", consumerRecord.offset(), e.getMessage()));
            e.printStackTrace();
        }
    }

    public void processApartmentSoldRecord(ConsumerRecord<String, String> consumerRecord) {
        String apartmentId = consumerRecord.key();
        String apartmentSoldPayload = consumerRecord.value();
        Optional<Apartment> apartment = apartmentsRepository.findById(Integer.parseInt(apartmentId));
        Optional<Boolean> isSold = Optional.of(Boolean.valueOf(JsonPath.parse(apartmentSoldPayload).read("$.Sold")));

        if (apartment.isPresent() && isSold.isPresent()) {
            sell(apartment.get());
            log.info(String.format("Apartment with id %s has been sold", apartment.get().getId()));
        }
    }

    private void sell(Apartment apartment) {
        apartment.setFree(false);
        apartmentsRepository.save(apartment);
    }



}
