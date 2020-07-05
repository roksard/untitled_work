package roksard.textfile.reverse;

import roksard.util.TextFile;

import java.util.Collections;

public class Reverse {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("usage: \nreverse <inputfile> <outputfile> \n (without angle brackets)");
            return;
        }
        TextFile file = new TextFile(args[0], "\n");
        Collections.reverse(file);
        file.write(args[1]);
    }
}
