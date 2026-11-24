package com.authservice.aplication.dto.commands;

import java.time.LocalDate;

public record CreatePatientCommand(
CreateGeneralUserWithNoRoleCommand generalUserData,
String personalId,
String name,
String lastName,
String phoneNumber,
LocalDate birthDate,
String gender,
String address
) {
}
