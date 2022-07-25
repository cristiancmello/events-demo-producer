package com.kafka.eventsdemoproducer;

import lombok.Data;

@Data
public class EmployeeDto implements CommonDto {
    private String firstName;
    private String lastName;
}
