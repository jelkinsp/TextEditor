package textEditor;

import java.io.*;

public class FileManager {

    private File file;
    private String text;

    public FileManager(File file) {
        this.file = file;
        this.text ="";
    }

    public void readFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.file))) {
            String line ;
            while ((line= bufferedReader.readLine())!=null){

                this.text += "\n"+line;

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(){
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(this.file))){
            printWriter.print(this.text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
