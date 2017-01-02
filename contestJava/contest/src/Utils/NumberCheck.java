package Utils;

/**
 * Created by Sebi on 02-Jan-17.
 */
public class NumberCheck {
    public static boolean isNumber(String string){
        try {
            Integer.parseInt(string);
            return  true;
        } catch (NumberFormatException exc){
            return false;
        }
    }
}
