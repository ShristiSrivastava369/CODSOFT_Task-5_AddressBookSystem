package com.codSoft;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class MainController {

	@Autowired
	private ContactService contactService;
	
	public 	RedirectView redirectView=new RedirectView();
	
	
	@GetMapping("/home")
	public String allContact(Model model) {
		List<Contact> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);
        return "index";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
        
        
	@GetMapping("/addContact")
	public String addContacts(Model model) {
		model.addAttribute("contact", new Contact());
		return "addContact";
	}
	
	@PostMapping("/contactSaved")
	public String contactsave(@ModelAttribute("contact") Contact contact,Model model,RedirectAttributes redirectAttributes) {
		if(contact.getFirst_Name().length()<=2 || contact.getPhone().length()<8 ||contact.getPhone().length()>11)
		{
			redirectAttributes.addFlashAttribute("errorMessage", "Could Not Save! Please fill Correct Credentials!");	
			return "redirect:/home#allContacts";
		}
		
		try {
            contactService.createContact(contact);
            redirectAttributes.addFlashAttribute("successMessage", "Contact Successfully Saved!");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "Could Not Save! Please fill Correct Credentials!");
		}
        return "redirect:/home#allContacts"; 
    }
	
	@GetMapping("/deleteContact/{id}")
	public RedirectView deleteContact(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
		try {
			this.contactService.deleteContact(id);
			redirectAttributes.addFlashAttribute("successMessage", "Successfully Deleted!");
		}catch(Exception e) {
			 redirectAttributes.addFlashAttribute("errorMessage", "Something Went Wrong!");
		}
		redirectView.setUrl("/home");
		return redirectView;
	}
	
	
	@GetMapping("/updateContact/{id}")
    public String updateContact(@PathVariable("id") int id, Model model) {
        Contact contact = contactService.getContactById(id);
        model.addAttribute("contact", contact);
        return "contactUpdate";
}
	
	@PostMapping("/updateContact/{id}")
	public String updateContact(@PathVariable("id") int id, @ModelAttribute Contact contact, RedirectAttributes redirectAttributes) {
	    try {
	        contactService.updateContact(contact, id);
	        redirectAttributes.addFlashAttribute("successMessage", "Contact Updated Successfully!");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("errorMessage", "Failed to Update Contact!");
	    }
	    return "redirect:/home#allContacts";
	}

	 
	 @PostMapping("/search")
	 public String search(@RequestParam ("query") String name,Model m) {
		 List<Contact> contacts=contactService.getAllContacts();
		 List<Contact>searchContacts=new ArrayList<>();
		 for(Contact s:contacts) {
			 if(s.getFirst_Name() != null && containsPartial(s.getFirst_Name(), name) 
			  ||s.getLast_Name() != null && containsPartial(s.getLast_Name(), name)
			  ||s.getPhone() != null && containsPartial(s.getPhone(), name)
			  ||s.getCity() != null && containsPartial(s.getCity(), name)
			  ||s.getCountry() != null && containsPartial(s.getCountry(), name)) { 
				 searchContacts.add(s);
			 }
		 }
		 m.addAttribute("s", searchContacts);
		   return "search";
		 
	 }
	 
	 private boolean containsPartial(String original, String partial) {
		    return original != null && original.toLowerCase().contains(partial.toLowerCase());
		}
	}
