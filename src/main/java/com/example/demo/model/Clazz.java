package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "CLAZZ")
@NamedQuery(name = "Clazz.findAll", query = "SELECT c FROM Clazz c order by c.className")
public class Clazz extends Auditable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "objectid-generator")
    @GenericGenerator(name = "objectid-generator", strategy = "com.example.demo.common.ObjectIDGenerator")
    private String id;

    @Column(name = "CLASS_NAME", length = 128)
    private String className;

    @Column(name = "DESCRIPTION", length = 512)
    private String description;

    @Column(name = "NUMBER_OF_STUDENT")
    private Integer numberOfStudent;

    @Column(name = "TEACHER_ID", length = 24)
    private String teacherId;
}
