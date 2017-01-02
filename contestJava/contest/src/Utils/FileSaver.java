package Utils;

import java.io.*;

/**
 * Created by Sebi on 02-Jan-17.
 */
public class FileSaver {
    /*
    Saves data to a given path
     */
    public static void save(String file, String data){
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
