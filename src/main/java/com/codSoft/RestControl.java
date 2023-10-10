package com.codSoft;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestControl {
	
	    @Autowired
	    private ContactService contactService;


		//Get All Contacts
		@GetMapping("/contacts")
		public List<Contact> getContacts() {
			return this.contactService.getAllContacts();
			}
		
		
		//Get Contact By Id
		@GetMapping("/contact/{id}")
		public Contact getContact(@PathVariable("id") int id) {
			Contact Contact=this.contactService.getContactById(id);
			return Contact;
		}
		
		
	    // Create new contact
	    @PostMapping("/contact")
	    public Contact addContact(@RequestBody Contact c) {
	        Contact contact = this.contactService.createContact(c);
	        return contact;
	    }

	 // Delete contact
	    @DeleteMapping("/contact/{contactId}")
	    public void deletecontact(@PathVariable("contactId") int id) {
	        this.contactService.deleteContact(id);
	        
	    }


	    // Update contact
	    @PutMapping("/updatecontact/{contactId}")
		public Contact updateContact(@RequestBody Contact contact,@PathVariable("contactId") int contactId) {
			this.contactService.updateContact(contact,contactId);
			return contact;
	}
}
