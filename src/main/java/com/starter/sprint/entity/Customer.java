package com.starter.sprint.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String companyName;
    private String contactName;
    private String address;
    private String email;
    private Double phone;
}
