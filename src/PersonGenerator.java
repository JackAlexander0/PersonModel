import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {

    String ID = "";
    String FirstName = "";
    String LastName = "";
    String Title = "";
    Integer YearOfBirth = 0;

    public static void main(String[] args) {
// Test data the lines of the file we will write
        Scanner in = new Scanner(System.in);

        ArrayList<String> recs = new ArrayList<>();

        boolean done = false;
        do {
            String ID = SafeInput.getRegExString(in, "What is the person's ID? ", "^00000\\d$");
            String FirstName = SafeInput.getNonZeroLenString(in, "What is the person's first name? ");
            String LastName = SafeInput.getNonZeroLenString(in, "What is the person's last name? ");
            String Title = SafeInput.getNonZeroLenString(in, "What is the person's title? ");
            Integer YearOfBirth = SafeInput.getRangedInt(in, "What is the person's year of birth?", 1920, 2020);

            //after collecting 5 variables, concatnate into csv string.
            //then add to array list
            done = SafeInput.getYNConfirm(in, "Are you done?");
        } while (!done);

        // uses a fixed known path:
        //  Path file = Paths.get("c:\\My Documents\\data.txt");

        // use the toolkit to get the current working directory of the IDE
        // will create the file within the Netbeans project src folder
        // (may need to adjust for other IDE)
        // Not sure if the toolkit is thread safe...
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt");

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String rec : recs)
            {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
