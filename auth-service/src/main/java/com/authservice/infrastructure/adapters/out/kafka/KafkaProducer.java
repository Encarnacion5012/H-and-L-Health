package com.authservice.infrastructure.adapters.out.kafka;

import com.authservice.aplication.dto.commands.CreateDoctorCommand;
import com.authservice.aplication.dto.commands.CreateGenralUserWithRoleCommand;
import com.authservice.aplication.dto.commands.CreatePatientCommand;
import com.authservice.aplication.port.out.kafka.SendDoctorRegisterEventPort;
import com.authservice.aplication.port.out.kafka.SendPatientRegisteredEventPort;
import com.authservice.aplication.port.out.kafka.SendUserRegisteredEventPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import user_events.DoctorRegisteredEvent;
import user_events.PatientRegisteredEvent;
import user_events.UserEvent;
import user_events.UserRegisteredEvent;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer implements SendPatientRegisteredEventPort, SendDoctorRegisterEventPort, SendUserRegisteredEventPort {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    @Override
    public void sendDoctorRegisterEvent(UUID auth_user_id, Instant createdAt, CreateDoctorCommand createDoctorCommand) {
        DoctorRegisteredEvent doctorRegisteredEvent = DoctorRegisteredEvent.newBuilder()
                .setUsername(createDoctorCommand.generalUserData().username())
                .setEmail(createDoctorCommand.generalUserData().email())
                .setPersonalId(createDoctorCommand.personalId())
                .setName(createDoctorCommand.name())
                .setLastName(createDoctorCommand.lastName())
                .setPhoneNumber(createDoctorCommand.phoneNumber())
                .setAppointmentPrice(createDoctorCommand.appointment_price().toString())
                .addAllSpecialtyIds(createDoctorCommand.specialtyIds())
                .setCreatedAt(createdAt.toString())
                .build();

        UserEvent userEvent = UserEvent.newBuilder()
                .setEventId(UUID.randomUUID().toString())
                .setOccurredAt(Instant.now().toString())
                .setAuthUserId(auth_user_id.toString())
                .setDoctorRegister(doctorRegisteredEvent)
                .build();

        try {
            kafkaTemplate.send("user-topic", userEvent.toByteArray());

        }catch (Exception e){
            log.error("Error sending DoctorRegistered event: {} ",userEvent);
        }
    }

    @Override
    public void sendPatientRegisteredEvent(UUID auth_user_id, Instant createdAt, CreatePatientCommand createPatientCommand) {

        PatientRegisteredEvent patientRegisteredEvent = PatientRegisteredEvent.newBuilder()
                .setUsername(createPatientCommand.generalUserData().username())
                .setEmail(createPatientCommand.generalUserData().email())
                .setPersonalId(createPatientCommand.personalId())
                .setName(createPatientCommand.name())
                .setLastName(createPatientCommand.lastName())
                .setPhoneNumber(createPatientCommand.phoneNumber())
                .setBirthDate(createPatientCommand.birthDate().toString())
                .setGender(createPatientCommand.gender())
                .setAddress(createPatientCommand.address())
                .setCreatedAt(createdAt.toString())
                .build();

        UserEvent userEvent = UserEvent.newBuilder()
                .setEventId(UUID.randomUUID().toString())
                .setOccurredAt(Instant.now().toString())
                .setAuthUserId(auth_user_id.toString())
                .setPatientRegister(patientRegisteredEvent)
                .build();


        try {
            kafkaTemplate.send("user-topic", userEvent.toByteArray());

        }catch (Exception e){
             log.error("Error sending PatientRegistered event: {} ",userEvent);
        }


    }

    @Override
    public void sendUserRegisteredEvent(UUID auth_user_id, Instant created_At, CreateGenralUserWithRoleCommand command) {

        UserRegisteredEvent userRegisteredEvent = UserRegisteredEvent.newBuilder()
                .setUsername(command.username())
                .setEmail(command.email())
                .setCreatedAt(created_At.toString())
                .build();

        UserEvent userEvent = UserEvent.newBuilder()
                .setEventId(UUID.randomUUID().toString())
                .setOccurredAt(Instant.now().toString())
                .setAuthUserId(auth_user_id.toString())
                .setUserRegister(userRegisteredEvent)
                .build();

        try {
            kafkaTemplate.send("user-topic", userEvent.toByteArray());

        }catch (Exception e){
            log.error("Error sending UserRegistered event: {} ",userEvent);
        }
    }


}
