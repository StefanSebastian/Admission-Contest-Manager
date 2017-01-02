package Utils.FileSaver;

/**
 * Created by Sebi on 02-Jan-17.
 */
public class HTMLSaver implements IFileSaver{

    @Override
    public void save(String path, String data) {
        System.out.println(path + " " + data);
    }
}
