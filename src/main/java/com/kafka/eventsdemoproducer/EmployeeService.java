package com.kafka.eventsdemoproducer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeService implements MessagingPort<Employee> {
    final Producer<String, Employee> producer;

    @Autowired
    public EmployeeService(@Qualifier("employeeProducer") Producer<String, Employee> producer) {
        this.producer = producer;
    }

    @Override
    public String topic() {
        return "employee-avro";
    }

    @Override
    public ProducerRecord<String, Employee> createProducerRecord(Employee employee) {
        return new ProducerRecord<>(topic(), employee);
    }

    @Override
    public void send(CommonDto commonDto) {
        var employeeDto = (EmployeeDto) commonDto;
        Employee employee = Employee.newBuilder()
                .setFirstName(employeeDto.getFirstName())
                .setLastName(employeeDto.getLastName())
                .build();

        producer.send(createProducerRecord(employee), (rm, ex) -> {
            if (ex == null) {
                log.info("Data sent with sucess!");
            } else {
                log.error("Fail to send message", ex);
            }
        });

        producer.flush();
    }
}
