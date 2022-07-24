package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "RECORD_FILE")
//@NamedQuery(name = "Homework.findAll", query = "SELECT h FROM Homework h order by")
public class RecordFile extends Auditable {

    @Id
    @GeneratedValue(generator = "objectid-generator")
    @GenericGenerator(name = "objectid-generator", strategy = "com.example.demo.common.ObjectIDGenerator")
    private String id;
    
    @Column(name = "USER_ID")
    private String userId;
    
    @Column(name = "FILED")
    private String field;
    
    @Column(name = "FILE_PATH")
    private String filePath;
    
    
    @Column(name = "RECORD_TYPE")
    private String record_type;
    
}
