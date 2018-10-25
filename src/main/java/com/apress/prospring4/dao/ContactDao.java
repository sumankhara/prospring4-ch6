package com.apress.prospring4.dao;

import java.util.List;

import com.apress.prospring4.model.Contact;

public interface ContactDao {

	List<Contact> findAll();
	
	List<Contact> findByFirstName(String firstName);
	
	String findLastNameById(Long id);
	
	String findFirstNameById(Long id);
	
	void insert(Contact contact);
	
	void update(Contact contact);
	
	void delete(Long contactId);
}
