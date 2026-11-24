package com.authservice.aplication.dto.commands;

import java.math.BigDecimal;
import java.util.List;


public record CreateDoctorCommand (
CreateGeneralUserWithNoRoleCommand generalUserData,
String personalId,
String name,
String lastName,
String phoneNumber,
List<Integer> specialtyIds,
BigDecimal appointment_price
) {
}
