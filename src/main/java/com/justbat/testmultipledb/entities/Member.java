package com.justbat.testmultipledb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


//@Indexed(index = "emotomy_index5")
@Embeddable
@Entity
@Table(name = "member")
@Data
public class Member {
//    @Id
//    @Column(name = "clientID")
//    private Integer clientID;

    @Id
    @Column(name = "loginName")
    private String loginName;

    @Column
    private String advisor;

    @OneToMany(mappedBy = "member")
    private List<CustomStrategy> customStrategy;
}
