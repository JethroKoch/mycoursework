package Models;

public class CustomerViewSearch {
    private int customerId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    //attributes created for a customer view search object
    public CustomerViewSearch(int customerId, String firstName, String lastName, String dateOfBirth) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
        //constructor creates object when needed
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
    //getters and setters are used to allow the object to be accessed and edited

    @Override
    public String toString() {
        return customerId + ", "+ firstName +", " + lastName +", " + dateOfBirth;
    }
    //to string method will display the attributes when the object is printed or displayed.
}
