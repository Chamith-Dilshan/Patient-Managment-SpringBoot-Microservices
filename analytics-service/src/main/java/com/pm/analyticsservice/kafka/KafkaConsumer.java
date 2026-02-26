package com.pm.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.event.PatientEvent;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "patient",groupId = "analytics-service")
    public void consumeEvent(byte[] event){
        try {
            log.info("Here is the analytics kafka consumer");
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            //Perform any business logics related to analytics

            log.info("Received event from patient: [PatientId={}, PatientName={}, PatientEmail={}]",
                    patientEvent.getPatientId(),patientEvent.getName(),patientEvent.getEmail());
        } catch (InvalidProtocolBufferException e) {
            log.error("Error deserializing event : {}",e.getMessage());
        }
    }
}
