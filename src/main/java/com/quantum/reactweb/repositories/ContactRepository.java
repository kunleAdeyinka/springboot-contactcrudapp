package com.quantum.reactweb.repositories;

import org.springframework.data.repository.CrudRepository;

import com.quantum.reactweb.models.Contact;

public interface ContactRepository extends CrudRepository<Contact, String>{

	@Override
	void delete(Contact deleted);
}
