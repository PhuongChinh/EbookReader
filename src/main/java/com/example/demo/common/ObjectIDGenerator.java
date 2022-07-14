package com.example.demo.common;

import java.io.Serializable;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//https://www.baeldung.com/hibernate-identifiers
@Slf4j
public class ObjectIDGenerator implements IdentifierGenerator, Configurable {

	//static final Logger logger = LoggerFactory.getLogger(ObjectIDGenerator.class);

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		// TODO Auto-generated method stub

	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		ObjectId id = new ObjectId();
		String sid = id.toHexString();// UUID.randomUUID().toString().replaceAll("-", "").substring(0, 24);
		log.info("Generate an ID: " + sid);
		return sid;
	}
}
