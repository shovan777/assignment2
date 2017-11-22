package fusemachines.backend.assignments.assignment2;

import java.util.Arrays;

public class Company {
	private String company;
	private String industry;
	private String subIndustry;
	private String sector;
	private String description;
	private String phone;
	private String fax;
	private String weburl;
	private Person[] keyPeople;
	
	
	@Override
	public String toString() {
		return "Company [company=" + company + ", industry=" + industry + ", subIndustry=" + subIndustry + ", sector="
				+ sector + ", description=" + description + ", phone=" + phone + ", fax=" + fax + ", weburl=" + weburl
				+ ", keyPeople=" + Arrays.toString(keyPeople) + "]";
	}


	public String getCompany() {
		return company;
	}


	public String getDescription() {
		return description;
	}


	public Person[] getKeyPeople() {
		return keyPeople;
	}


	public String getFax() {
		return fax;
	}


	public String getWeburl() {
		return weburl;
	}
	
	
	
	
}
