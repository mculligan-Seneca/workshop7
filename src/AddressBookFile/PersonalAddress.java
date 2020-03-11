package AddressBookFile;

public class PersonalAddress {

    private String firstName;//25
    private String lastName;//25
    private Address address;


    PersonalAddress(String first, String last, Address address){
        this.firstName=first;
        this.lastName = last;
        this.address=address;
    }


    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public Address getAddress(){
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
