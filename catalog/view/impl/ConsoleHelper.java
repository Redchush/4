package catalog.view.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static catalog.view.impl.CriteriaType.*;


public class ConsoleHelper {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final String ERROR = "Произошла ошибка при попытке ввода текста. Попробуйте еще раз.";

    public static void writeMessage(String message){
        if (message != null) {
            System.out.println(message);
        }

    }
    public static String readString(int validation){
        String current = readString();
        switch (validation){
            case VALIDATION_OPERATION :
                if ( !(  (current.equalsIgnoreCase(FIND) ||
                         (current.equalsIgnoreCase(SAVE)))  ) )
            {
                writeMessage(ERROR);
                return readString(validation);
            }
                else return current.toUpperCase();
            case VALIDATION_CRITERIA_TEXT :
                break;
        }
        return current;
    }
    public static String readString(){
        String result = "";
        while (true){
            try{
                result = reader.readLine();
                if (result == null||result.length() == 0) throw new Exception();
                break;
            }catch (Exception e){
                writeMessage(ERROR);
            }
            finally {
                try {
                    if (Integer.parseInt(result) == 6) System.exit(0);
                } catch (NumberFormatException e){

                }
            }
        }
        return result;
    }

    public static int readInt(int validation){
        int result = readInt();
        switch (validation){
            case VALIDATION_CRITERIA :
                if (!((result > 0)&&(result < 6)))
                    return readInt(validation);
                }

        return result;

    }

    public static int readInt(){
        int result = 0;
        while (true){
            try{
                String line = readString();
                if ( (line == null) || (line.length() == 0))throw new NumberFormatException();
                result = Integer.parseInt(line);
            } catch (Exception e){
                writeMessage(ERROR);
            } finally {
                if (result == 6) System.exit(0);
            }
            return result;
        }

    }
}
