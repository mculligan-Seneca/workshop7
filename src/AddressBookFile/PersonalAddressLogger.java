package AddressBookFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.Period;

public class PersonalAddressLogger {
    public static final int NAME_SIZE=25;//for now
    public static final int CITY_SIZE=20;
    public static final int PROV_SIZE=20;
    public static final int POSTAL_SIZE=6;
    private static final int RECORD_SIZE=(2*PersonalAddressLogger.NAME_SIZE)+
            PersonalAddressLogger.CITY_SIZE+PersonalAddressLogger.POSTAL_SIZE+PersonalAddressLogger.PROV_SIZE+4+
            +1;
    private static final char DELIM='|';
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
        }catch(FileNotFoundException fnf){
            fnf.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }



    //assumes data is valid
    public void add(PersonalAddress personalAddress)throws IOException {
        this.randf.seek(this.randf.length());

       /* int pos=0;
        pos = this.insertToken(pos,personalAddress.getFirstName(),PersonalAddressLogger.NAME_SIZE);
        this.sb.insert(pos++,PersonalAddressLogger.DELIM);
        pos = this.insertToken(pos,personalAddress.getLastName(),PersonalAddressLogger.NAME_SIZE);
        this.sb.insert(pos++,PersonalAddressLogger.DELIM);
        pos = this.insertToken(pos,personalAddress.getAddress().getCity(),PersonalAddressLogger.CITY_SIZE);
        this.sb.insert(pos++,PersonalAddressLogger.DELIM);
        pos = this.insertToken(pos,personalAddress.getAddress().getProv(),PersonalAddressLogger.PROV_SIZE);
        this.sb.insert(pos++,PersonalAddressLogger.DELIM);
        pos = this.insertToken(pos,personalAddress.getAddress().getPostalCode(),PersonalAddressLogger.POSTAL_SIZE);
*/
            this.sb.append(personalAddress.getFirstName()+"|"+personalAddress.getLastName()+"|");
            this.sb.append(personalAddress.getAddress().getCity()+"|"+personalAddress.getAddress().getProv()+"|"+
                    personalAddress.getAddress().getPostalCode());
            this.sb.setLength(RECORD_SIZE-1);
            this.randf.write(this.sb.toString().getBytes());
            this.randf.write(System.getProperty("line.separator").getBytes());
            System.out.println(this.sb.length());
        this.recordNum++;
        sb.delete(0,this.sb.length());


    }

    private int insertToken(int pos, String token, int tokenSize){
        sb.insert(pos,token);
        pos+=tokenSize;
        
        return pos;
    }

    public PersonalAddress getLast(){
        PersonalAddress pa = null;
        this.currentRecord=this.recordNum;
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
        byte[] b = new byte[RECORD_SIZE];
        Address add;
        PersonalAddress personAdd=null;
        String firstName,lastName;
        if(this.recordNum>0) {
            try {
                this.randf.read(b, RECORD_SIZE * this.currentRecord, RECORD_SIZE);
                sb.append(b);
                firstName = sb.delete(0, sb.indexOf("|", 0)).toString();
                lastName = sb.delete(0, sb.indexOf("|", 0)).toString();
                add = new Address(sb.delete(0, CITY_SIZE).toString(),
                        sb.delete(0, PROV_SIZE).toString(),
                        sb.delete(0, POSTAL_SIZE).toString());
                personAdd = new PersonalAddress(firstName, lastName, add);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return personAdd;
    }

    public void close(){
       try {
           this.randf.close();
       }catch(IOException ioe){
           ioe.printStackTrace();
       }
    }

}
