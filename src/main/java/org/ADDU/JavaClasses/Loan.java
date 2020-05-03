package org.ADDU.JavaClasses;

public class Loan {

    private String desiredAmount;
    private String status;
    private String message;
    private String usernameClient;

    public String getUsernameClient() {
        return usernameClient;
    }

    public void setUsernameClient(String usernameClient) {
        this.usernameClient = usernameClient;
    }

    public String getDesiredAmount() {
        return desiredAmount;
    }

    public void setDesiredAmount(String desiredAmount) {
        this.desiredAmount = desiredAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "desiredAmount='" + desiredAmount + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", usernameClient='" + usernameClient + '\'' +
                '}';
    }
}
