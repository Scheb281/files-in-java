import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Employee extends Thread{
    private String fileName = "";
    private int taskCount = 0;
    private String employeeName = "";

    public Employee(String fileName,int taskCount, String employeeName){
        this.fileName = fileName;
        this.taskCount = taskCount;
        this.employeeName = employeeName;
    }

    public String getFileName()
    {
        return this.fileName;
    }
    public void ReadFile(){
        try{
            String content = Files.readString(Path.of(this.fileName), StandardCharsets.UTF_8);
            System.out.println(content);

        }catch(IOException e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    public void CreateReport(){
        File file = new File(this.fileName);
        try{    
            if(file.exists()){
                System.out.println("Файл уже существует");
            }
            else{
                file.createNewFile();
                System.out.println("Файл создан");
            }
        }
        catch(IOException e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    public void CompleteTask(int taskNumber){
        System.out.println(this.fileName + " " + this.employeeName + " " + taskNumber);
        try{
            FileWriter writer = new FileWriter(this.fileName, StandardCharsets.UTF_8, true);
            writer.write(this.fileName  + " " + this.employeeName + " " + taskNumber + "\n");
            writer.close();
        }
        catch(IOException e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    @Override 
    public void run(){
        try{
        CreateReport();
            for(int i = 1; i <= this.taskCount; i++){
                CompleteTask(i);
                Thread.sleep(500);
            }
        }
        catch(InterruptedException e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
