package com.example.transactionalroutingtraining.controller;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {
    private Long id;
    private String name;
    private boolean deleted;
    @JsonAlias("national_id")
    private String nationalId;
}
