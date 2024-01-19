public class Contact {
    private String firstName;
    private String sureName;
    private String phoneNumber;
    private String email;

    public Contact(String firstName, String sureName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.sureName = sureName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Contact() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firsName) {
        this.firstName = firsName;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return  "---------------------------" +
                "\nFirstName:" + firstName +
                "\nSureName:" + sureName +
                "\nPhoneNumber:" + phoneNumber +
                "\nEmail:" + email +
                "\n--------------------------";
    }
}


