package sv.mycompany.exception;


public class ErrorDTO {

    private String errorMessage;

    public ErrorDTO() {
        //public empty constructor for ErrorDTO
    }

    public ErrorDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    
}
