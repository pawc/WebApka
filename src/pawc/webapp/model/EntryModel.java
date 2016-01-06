package pawc.webapp.model;

public class EntryModel {

    private String author;
    private String message;
    private String date;
       
    public EntryModel(String author, String date, String message){
        this.author = author;
        this.message = message;
        this.date = date;
    }
    
    public String getAuthor(){
        return author;
    }
    
    public String getDate(){
        return date;
    }
    
    public String getMessage(){
        return message;
    }
    
}
