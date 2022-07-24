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
@Table(name = "Page")
//@NamedQuery(name = "Homework.findAll", query = "SELECT h FROM Homework h order by")
public class PageBook extends Auditable {

    @Id
    @GeneratedValue(generator = "objectid-generator")
    @GenericGenerator(name = "objectid-generator", strategy = "com.example.demo.common.ObjectIDGenerator")
    private String id;
    
    @Column(name = "BOOK_ID")
    private String bookId;
    
    @Column(name = "PAGE_ORDER")
    private Integer pageOder;
    
    @Column(name = "FILE_PATH")
    private String filePath;
    
}
