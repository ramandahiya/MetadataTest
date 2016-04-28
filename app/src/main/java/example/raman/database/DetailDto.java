package example.raman.database;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.ParcelableSpan;

/**
 * Created by Raman on 27-04-2016.
 */
public class DetailDto implements Parcelable {
    private int id;
    private String address;
    private String department;
    private String designation;
    private String phone;
    private String income;

    public DetailDto(){
     super();
    }

    public DetailDto(int id, String income, String phone, String designation, String department, String address) {
        super();
        this.id = id;
        this.income = income;
        this.phone = phone;
        this.designation = designation;
        this.department = department;
        this.address = address;
    }


    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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


    public static final Parcelable.Creator<DetailDto> CREATOR = new Parcelable.Creator<DetailDto>() {
        public DetailDto createFromParcel(Parcel in) {
            return new DetailDto();
        }

        public DetailDto[] newArray(int size) {
            return new DetailDto[size];
        }
    };

}
