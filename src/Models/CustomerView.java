package Models;


public class CustomerView {
    private int customerId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String contactNumber;
    private String house;
    private String street;
    private String city;
    private String county;
    private String postcode;

    public CustomerView(int customerId, String firstName, String lastName, String dateOfBirth, String contactNumber, String house, String street, String city, String county, String postcode) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.contactNumber = contactNumber;
        this.house = house;
        this.street = street;
        this.city = city;
        this.county = county;
        this.postcode = postcode;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "CustomerView{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", house='" + house + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }
}
