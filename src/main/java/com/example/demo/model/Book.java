package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "Book")
//@NamedQuery(name = "Homework.findAll", query = "SELECT h FROM Homework h order by")
public class Book extends Auditable {

    @Id
    @GeneratedValue(generator = "objectid-generator")
    @GenericGenerator(name = "objectid-generator", strategy = "com.example.demo.common.ObjectIDGenerator")
    private String id;
    
    @Column(name = "BOOK_NAME")
    private String bookName;
    
    @Column(name = "AUTHOR")
    private String author;
    
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "NUMBER_OF_PAGE")
    private Integer numberOfPage;
    
    @Column(name = "PATH")
    private String pathBook;
    
}
