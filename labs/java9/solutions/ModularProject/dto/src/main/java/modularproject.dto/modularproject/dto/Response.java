package modularproject.dto;

public class Response implements Payload{
    private String message;

    public Response(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}