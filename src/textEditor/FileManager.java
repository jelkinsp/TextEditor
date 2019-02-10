package textEditor;

import java.io.*;

public class FileManager {

    private File file;

    public FileManager() {
        this.file = new File(System.getProperty("user.home"));
    }

    public String readFile() {
        String line;
        String aux = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.file))) {
            while ((line = bufferedReader.readLine()) != null) {
                aux += line + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aux;
    }

    public void writeFile(String text) {
        try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(this.file))) {
            bufferWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
