package com.example.Bikroy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DIVISIONADDRESS")
public class DivisionAddressModel extends BaseModel{
    private String divisionName;
}
