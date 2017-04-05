package library.models;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by Shoma on 05.04.2017.
 */
public class Reader implements Externalizable{
    private String  firstname;
    private String  secondname;
    private String  lastname;
    private long    passportNumber;

    public Reader(){}

    public Reader(String firstname, String secondname, String lastname,
                  long passportNumber) {
        this.firstname = firstname;
        this.secondname = secondname;
        this.lastname = lastname;
        this.passportNumber = passportNumber;
    }

    @Override
    public int hashCode() {
    return (int)passportNumber*32;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null) return false;
        if (!(obj instanceof Reader)) return false;
        if (passportNumber!=((Reader)obj).passportNumber) return false;
        return true;

    }

    @Override
    public String toString() {
        return "Reader{" +
                "firstname='" + firstname + '\'' +
                ", secondname='" + secondname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", passportNumber=" + passportNumber +
                '}';
    }

    public long getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(long passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public  void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(firstname);
        out.writeUTF(secondname);
        out.writeUTF(lastname);
        out.writeLong(passportNumber);
     //   out.writeUTF("@soufee");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
this.firstname = in.readUTF();
this.secondname = in.readUTF();
this.lastname = in.readUTF();
this.passportNumber = in.readLong();
    }
}
