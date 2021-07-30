package com.stackroute.medicalrequestservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Requirement {

    private String requirementName;
    private String quantity;
    private String unitOfMeasure;

}
