/*
Stduent: Mitchell Culligan
Workshop 7
id: 1616293170
email: mculligan@myseneca.ca
Professor: Mahboob Ali
Date: March 13th, 2020
 */
package AddressBookFile;

public class PersonalAddress {

    private String firstName;//25
    private String lastName;//25
   // private Address address;
    private String city;//20
    private String prov;//20
    private String postalCode;//6

    public PersonalAddress(String first, String last, String city, String prov, String postal){
        this.firstName=first;
        this.lastName = last;
      //  this.address=address;
        this.city=city;
        this.prov = prov;
        this.setPostalCode(postal);
    }

   public  PersonalAddress(){
        this("","","","","");
    }
    public void setPostalCode(String postal){
        if(postal.matches("[A-Za-z][0-9][A-Za-z][0-9][A-Za-z][0-9]")){
            this.postalCode = postal;
        }
        else
            this.postalCode = "A0A0A0";
    }

    public void setCity(String city){
        this.city = city;
    }

    public void setProv(String prov){
        this.prov = prov;
    }

    public String getProv(){
        return this.prov;
    }

    public String getPostalCode(){
        return this.postalCode;
    }

    public String getCity(){
        return this.city;
    }




    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

  /*  public Address getAddress(){
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
*/
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
