package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.example.demo.request.UserRequest;
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
@Table(name = "ROLE", schema = "public")
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r order by r.roleName")
public class Role extends Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "objectid-generator")
	@GenericGenerator(name = "objectid-generator", strategy = "com.example.demo.common.ObjectIDGenerator")
	@Column(unique = true, nullable = false, length = 24)
	private String id;

	@Column(name = "ROLE_CODE", length = 24)
	private String roleCode;

	@Column(name = "ROLE_NAME", length = 256)
	private String roleName;
	
	@Column(name = "DESCRIPTION")
	private String description;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

}
