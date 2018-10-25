package com.apress.prospring4.main;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.apress.prospring4.dao.ContactDao;
import com.apress.prospring4.dao.ContactDaoJDBCImpl;
import com.apress.prospring4.model.Contact;

public class PlainJdbcSample {
	
	private static final Logger logger = LogManager.getLogger(PlainJdbcSample.class);

	private static ContactDao contactDao = new ContactDaoJDBCImpl();
	
	public static void main(String[] args) {
		logger.info("Listing initial contact data: ");
		listAllContacts();
		
		logger.info("Insert a new contact:");
		Contact contact = new Contact();
		contact.setFirstName("Jacky");
		contact.setLastName("Chan");
		contact.setBirthDate(new Date(new GregorianCalendar(1978, 11, 28).getTime().getTime()));
		
		contactDao.insert(contact);
		
		logger.info("Listing contact data after new contact created: ");
		listAllContacts();
		
		logger.info("Deleting the previous created contact: ");
		
		contactDao.delete(contact.getId());
		
		logger.info("Listing contact data after new contact deleted: ");
		listAllContacts();
	}

	private static void listAllContacts() {
		List<Contact> contacts = contactDao.findAll();
		
		for(Contact contact: contacts) {
			logger.info(contact);
		}
	}

}
