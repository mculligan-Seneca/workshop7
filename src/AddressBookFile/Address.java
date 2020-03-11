package AddressBookFile;

public class Address {

   private String city;//20
   private String prov;//20
   private String postalCode;//6

   Address(String city, String prov, String postal){
        this.city=city;
        this.prov = prov;
        this.setPostalCode(postal);
   }

   public void setPostalCode(String postal){
       if(postal.matches("[A-Z][0-9][A-Z][0-9][A-Z][0-9]")){
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

}
