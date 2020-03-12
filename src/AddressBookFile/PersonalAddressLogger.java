package AddressBookFile;


import java.io.IOException;
import java.io.RandomAccessFile;


public class PersonalAddressLogger {
    public static final int NAME_SIZE=25;//for now
    public static final int CITY_SIZE=20;
    public static final int PROV_SIZE=35;
    public static final int POSTAL_SIZE=6;
    private static final int RECORD_SIZE=(2*PersonalAddressLogger.NAME_SIZE)+
            PersonalAddressLogger.CITY_SIZE+PersonalAddressLogger.POSTAL_SIZE+PersonalAddressLogger.PROV_SIZE+4+
            +1;
    public static final char DELIM='|';
    private int currentRecord;
    private int recordNum;
    private RandomAccessFile randf;
    private StringBuilder sb;



    public  PersonalAddressLogger(){

        try {
            this.randf = new RandomAccessFile("AddressBook.txt", "rws");
            this.sb = new StringBuilder();
            this.currentRecord=0;
            this.recordNum=(int)randf.length()/RECORD_SIZE;
        } catch(IOException ioe){
            ioe.printStackTrace();
        }

    }


    public boolean isOnLast(){
        return this.currentRecord==this.recordNum-1;
    }

    public boolean isOnFirst(){
        return this.currentRecord==0;
    }
    //assumes data is valid
    public void add(PersonalAddress personAddress)throws IOException{
        this.loadAddress(personAddress);
        this.writeAddress(this.recordNum*RECORD_SIZE);
        this.currentRecord= this.recordNum;
        this.recordNum++;
    }

    public void update(PersonalAddress personalAddress)throws IOException{
        if(this.recordNum!=0) {
            this.loadAddress(personalAddress);
            this.writeAddress(this.currentRecord*RECORD_SIZE);
        }
    }
    private void loadAddress(PersonalAddress personalAddress){
        this.sb.append(personalAddress.getFirstName()).append(PersonalAddressLogger.DELIM).append(personalAddress.getLastName()).append(PersonalAddressLogger.DELIM)
                    .append(personalAddress.getAddress().getCity()).append(PersonalAddressLogger.DELIM).append(personalAddress.getAddress().getProv())
                    .append(PersonalAddressLogger.DELIM).append(personalAddress.getAddress().getPostalCode());
        this.sb.setLength(RECORD_SIZE-1);

    }


    private void writeAddress(int off)throws IOException{
        this.randf.seek(off);
        this.randf.write(this.sb.toString().getBytes());
        this.randf.write(System.getProperty("line.separator").getBytes());
        sb.delete(0,this.sb.length());
    }


    public PersonalAddress getLast(){
        PersonalAddress pa = null;
        this.currentRecord=this.recordNum-1;
        pa= this.readAddress();
        return pa;
    }


    public PersonalAddress getFirst(){
        PersonalAddress pa =null;
            this.currentRecord=0;
            pa=this.readAddress();

        return pa;
    }


    public PersonalAddress getNext(){
        PersonalAddress pa=null;
        if(this.currentRecord<this.recordNum-1)
            this.currentRecord++;
        pa=this.readAddress();

        return pa;
    }
    public PersonalAddress getPrevious(){
        PersonalAddress pa=null;
        if(this.currentRecord>0)
            this.currentRecord--;
        pa=this.readAddress();

        return pa;

    }
    public PersonalAddress readAddress(){
        Address add;
        PersonalAddress personAdd=null;
        String firstName,lastName;
        if(this.recordNum>0) {
            try {
                add = new Address();
                this.randf.seek( RECORD_SIZE * this.currentRecord);
                this.sb.append(this.randf.readLine());
                firstName = this.extractToken();
                lastName = this.extractToken();
                add.setCity(this.extractToken());
                add.setProv(this.extractToken());
                add.setPostalCode(this.sb.toString().trim());
                this.sb.delete(0,this.sb.length());
                personAdd = new PersonalAddress(firstName, lastName, add);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return personAdd;
    }

    public String extractToken(){
        String token = this.sb.substring(0,
                this.sb.indexOf(String.valueOf(PersonalAddressLogger.DELIM), 0)).trim();
        this.sb.delete(0, sb.indexOf(String.valueOf(PersonalAddressLogger.DELIM), 0)+1);
        return token;

    }
    public void close(){
       try {
           this.randf.close();
       }catch(IOException ioe){
           ioe.printStackTrace();
       }
    }

}
