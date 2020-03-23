package myclasses;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {
    public void rewriteFile(ArrayList<Timetable> list) throws IOException {
        FileWriter reOut = new FileWriter("src/resources/Timetable.txt", false);
        for (int i = 0; i < list.size(); i++) {
            reOut.write(list.get(i) + "\n");
        }
        reOut.close();
    }
}
