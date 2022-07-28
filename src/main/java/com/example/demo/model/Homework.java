package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "HOMEWORK")
@NamedQuery(name = "Homework.findAll", query = "SELECT h FROM Homework h order by h.clazzId")
public class Homework extends Auditable {

    @Id
    @GeneratedValue(generator = "objectid-generator")
    @GenericGenerator(name = "objectid-generator", strategy = "com.example.demo.common.ObjectIDGenerator")
    private String id;

    @Column(name = "LST_PAGE_ID")
    private String lstPageId;

    @Column(name = "CLAZZ_ID")
    private String clazzId;

    @Column(name = "ASSIGNED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignedTime;

    @Column(name = "END_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

}
