package com.codSoft;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Contacts")
public class Contact {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		public int id;
		@Column(name="First_Name")
		private String first_Name;
		@Column(name="Last_Name")
		private String last_Name;
		@Column(name="Email")
		private String email;
		@Column(name="Phone")
		private String phone;
		@Column(name="City")
		private String city;
		@Column(name="Country")
		private String country;
		
		
		
		
		//Field_Constructor
		public Contact(int id, String first_Name, String last_Name, String email, String phone,String city, String country) {
			super();
			this.id = id;
			this.first_Name = first_Name;
			this.last_Name = last_Name;
			this.email = email;
			this.phone = phone;
			this.city = city;
			this.country = country;
		}
		
		
		//Default_Constructor
		public Contact() {
			super();
			// TODO Auto-generated constructor stub
		}
		


		//Getter Setter
		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getFirst_Name() {
			return first_Name;
		}


		public void setFirst_Name(String first_Name) {
			this.first_Name = first_Name;
		}


		public String getLast_Name() {
			return last_Name;
		}


		public void setLast_Name(String last_Name) {
			this.last_Name = last_Name;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getPhone() {
			return phone;
		}


		public void setPhone(String phone) {
			this.phone = phone;
		}


		public String getCity() {
			return city;
		}


		public void setCity(String city) {
			this.city = city;
		}


		public String getCountry() {
			return country;
		}


		public void setCountry(String country) {
			this.country = country;
		}



		//String
		@Override
		public String toString() {
			return "CustomerEntity [id=" + id + ", first_Name=" + first_Name + ", last_Name=" + last_Name + ", email="
					+ email + ", phone=" + phone +  ", city=" + city + ", country=" + country
					+ "]";
		}

		
		

}

