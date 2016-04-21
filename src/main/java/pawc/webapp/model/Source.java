package pawc.webapp.model;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Source{

    private File file;
 
    public Source(File file){
        this.file = file;
    }

    public String read() throws IOException{
        FileReader fileReader =  new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String result = "";
        while(true){
            String line = bufferedReader.readLine();
            if(line==null) break;
            result += line;
            result += "\n";
        }
        return result;
    }

}
