package example.raman.database;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created with IntelliJ IDEA.
 * User: DEEPAK BEDI
 * Date: 1/30/16
 * Time: 3:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class AuthDto implements Parcelable {
    private int id;
    private String firstname;
    private String lastname;
    private String email;

    public AuthDto(){
        super();
    }
    public AuthDto( String firstname, String lastname, String email) {
       super();
       // this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
    public AuthDto(int id, String firstname, String lastname, String email) {
        super();
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }


    public static final Parcelable.Creator<AuthDto> CREATOR = new Parcelable.Creator<AuthDto>() {
        public AuthDto createFromParcel(Parcel in) {
            return new AuthDto();
        }

        public AuthDto[] newArray(int size) {
            return new AuthDto[size];
        }
    };
}

