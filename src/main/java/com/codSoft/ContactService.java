package com.codSoft;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class ContactService {
		
		@Autowired
		public ContactRepository contactRepository;

		
		//Get All Contacts
		public List<Contact> getAllContacts() {
		    return (List<Contact>) this.contactRepository.findAll();
		}

		
		//Get A Contact
		public Contact getContactById(int id) {
			
		    return this.contactRepository.findById(id);
		}

		
		//Create a Contact
		public Contact createContact(Contact c) {
			Contact contact=this.contactRepository.save(c);
			return contact;
		}
		
		// Delete a Contact
		public void deleteContact(int id) {
		    contactRepository.deleteById(id);
		}


		
		//Update a Contact
		public Contact updateContact(Contact contact, int contactId) {
		    Contact existingContact = this.contactRepository.findById(contactId);
		    if (existingContact != null) {
		        existingContact.setFirst_Name(contact.getFirst_Name());
		        existingContact.setLast_Name(contact.getLast_Name());
		        existingContact.setEmail(contact.getEmail());
		        existingContact.setPhone(contact.getPhone());
		        existingContact.setCity(contact.getCity());
		        existingContact.setCountry(contact.getCountry());
		        this.contactRepository.save(existingContact);
		    }
		    return existingContact;
		}

		}

