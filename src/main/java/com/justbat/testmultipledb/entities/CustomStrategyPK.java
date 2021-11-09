package com.justbat.testmultipledb.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CustomStrategyPK implements Serializable {
    private String symbol;
    @Column(name = "loginName")
    private String loginName;
}
