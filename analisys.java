public class analisys {
    public static void main(String[] args){
        FileAnalyzer file1 = new FileAnalyzer("file1.txt", "as1.txt");
        FileAnalyzer file2 = new FileAnalyzer("file2.txt", "as2.txt");
        FileAnalyzer file3 = new FileAnalyzer("file3.txt", "as3.txt");

        try{
            file1.start();
            file1.join();
        
            file2.start();
            file2.join();
        
            file3.start();
            file3.join();
        }
        catch(Exception e){
            System.out.println("Ошибка: " + e.getMessage());
        }
        
    }
}
