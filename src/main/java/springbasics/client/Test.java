package springbasics.client;

import java.util.HashSet;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(new Contact("Ashish", null));
		contacts.add(new Contact("Ashish", "Gokhale"));
		contacts.add(new Contact("Baban", "Akdu"));
		contacts.add(new Contact("Ashwin", "Gokhale"));
		contacts.add(new Contact("Ash", "Sharma"));
		contacts.add(new Contact("BS", "Sinha"));
		
		String filter = "A".toUpperCase();
		System.out.println("Filter used: " + filter);
		for(Contact contact : contacts) {
			boolean match = contact.isMatch(filter);
			if( match) {
				System.out.println(contact);
			}
		}
		
		filter = "As".toUpperCase();
		System.out.println("Filter used: " + filter);
		for(Contact contact : contacts) {
			boolean match = contact.isMatch(filter);
			if( match) {
				System.out.println(contact);
			}
		}
		
		filter = "Ash".toUpperCase();
		System.out.println("Filter used: " + filter);
		for(Contact contact : contacts) {
			boolean match = contact.isMatch(filter);
			if( match) {
				System.out.println(contact);
			}
		}
		
		filter = "B".toUpperCase();
		System.out.println("Filter used: " + filter);
		for(Contact contact : contacts) {
			boolean match = contact.isMatch(filter);
			if( match) {
				System.out.println(contact);
			}
		}
		
		filter = "YZ".toUpperCase();
		System.out.println("Filter used: " + filter);
		for(Contact contact : contacts) {
			boolean match = contact.isMatch(filter);
			if( match) {
				System.out.println(contact);
			}
		}
		

	}

}

class Contact {
	@Override
	public String toString() {
		return "Contact [fName=" + fName + ", lName=" + lName + "]";
	}

	private String fName;
	private String lName;
	
	public Contact(String fName, String lName) {
		super();
		this.fName = fName;
		this.lName = lName;
	}

	public String getfName() {
		return fName;
	}

	public String getlName() {
		return lName;
	}
	
	public boolean isMatch(String filterString) {
		String regex = "\\A" + filterString + ".*";
		
		switch(filterString.length()) {
		case 1:
			regex = "\\A" + filterString + ".*";	
			boolean match = false;
			if (fName != null) {
				match = fName.toUpperCase().matches(regex);
			}
			
			if(lName != null) {
				match =  lName.toUpperCase().matches(regex) || match;
			}
			return match;
		case 2:
			regex = "\\A" + filterString + ".*";	
			match = false;
			if (fName != null) {
				match = fName.toUpperCase().matches(regex);
			}
			
			if(lName != null) {
				match =  lName.toUpperCase().matches(regex) || match;
			}

			if(!match) {
				regex = "\\A" + filterString.substring(1) + ".*";
				if (fName != null) {
					match = fName.toUpperCase().matches(regex);
				}
				
				if(lName != null) {
					match =  lName.toUpperCase().matches(regex) || match;
				}
			}
				return match;
			
			default:
				regex = "\\A" + filterString + ".*";	
				match = false;
				if (fName != null) {
					match = fName.toUpperCase().matches(regex);
				}
				
				if(lName != null) {
					match =  lName.toUpperCase().matches(regex) || match;
				}
				return match;
				
		}
	}
}
