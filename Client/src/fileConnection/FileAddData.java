package fileConnection;

import model.Document;

import java.io.FileWriter;
import java.io.IOException;

public class FileAddData {
   public static FileAddData fileAddData = new FileAddData();
    private FileAddData(){}

    public void addInFile(Document document) throws IOException {


        FileWriter writer = new FileWriter(document.getName()+".txt", false);
            writer.write(document.toString());
            writer.append('\n');
            writer.flush();

    }
}
