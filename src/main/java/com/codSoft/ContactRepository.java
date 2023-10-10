package com.codSoft;

import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer> {
	public Contact findById(int id);
}
