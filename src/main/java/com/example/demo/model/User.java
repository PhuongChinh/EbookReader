package com.example.demo.model;

import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Table(name = "USER", schema = "public")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u order by u.username")
public class User extends Auditable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "objectid-generator")
    @GenericGenerator(name = "objectid-generator", strategy = "com.example.demo.common.ObjectIDGenerator")
    @Column(unique = true, nullable = false, length = 24)
    private String id;

    @Column(length = 256, name = "USERNAME")
    private String username;

    @Column(length = 256)
    private String password;

    @Column(name = "FULL_NAME", length = 256, insertable = false, updatable = false)
    private String fullName;

    @Column(name = "EMAIL", length = 256)
    private String email;

    @Column(name = "STATUS")
    private String status;

    @ManyToMany
    @JoinTable(name = "USER_ROLE", joinColumns = {
            @JoinColumn(name = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "id", nullable = false)})
    private Set<Role> roles;

}
