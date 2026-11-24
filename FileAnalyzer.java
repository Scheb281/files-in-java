import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class FileAnalyzer extends Thread{
    private String fileName;
    private String reportName;
    private int letterCount = 0;
    private int digitCount = 0;
    private int spaceCount = 0;

    public FileAnalyzer(String fileName, String reportName){
        this.fileName = fileName;
        this.reportName = reportName;
    }

    public void AnalyzerFile(){
        try{
            File file = new File(this.fileName);
            if(file.exists()){
                String content = Files.readString(Path.of(this.fileName), StandardCharsets.UTF_8);
                for(char c : content.toCharArray()){
                    if(Character.isLetter(c)){
                        this.letterCount++;
                    } else if(Character.isDigit(c)){
                        this.digitCount++;
                    } else if (Character.isWhitespace(c)) {
                        this.spaceCount++;
                    }
                }

            }
        }
        catch(IOException e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    public void SaveReport(){
        try{
            FileWriter writer = new FileWriter(reportName, StandardCharsets.UTF_8, true);
            File file1 = new File(this.reportName);
            if (!file1.exists())    {
                file1.createNewFile();
            }
            File file = new File(this.fileName);
            
            writer.write(this.fileName + " " + file.length() + " " + this.letterCount + " " + this.digitCount + " " + this.spaceCount);

            writer.close();
        }
        catch(IOException e){
            System.out.println("Ошибка: " + e.getMessage());
        }
        
    }

    @Override
    public void run(){
        System.out.println("Начало обработки файла");
        AnalyzerFile();
        SaveReport();
        System.out.println("Завершение обработки файла");
    }
}
