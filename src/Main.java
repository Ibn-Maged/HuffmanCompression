import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        File file = new File("input.txt");
//        String input = "";
//        try {
//            Scanner scanner = new Scanner(file);
//            while(scanner.hasNextLine()){
//                input += scanner.nextLine();
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        HuffmanCompressor hc = new HuffmanCompressor();
//        hc.compress("AAAABABBBCC");
        hc.compress("AAABBCD");
//        hc.compress(input);
        hc.huffmanTable.forEach((key, value)->{
            System.out.println(key + " " + value);
        });

//        try {
//            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("output.bin"));
//            oos.writeObject(hc.huffmanTable);
//        } catch (IOException e){
//            e.printStackTrace();
//        }

//        HashMap<String, Character> hm = new HashMap<>();
//        try {
//            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("output.bin"));
//            hm = (HashMap<String, Character>) ois.readObject();
//        }catch (IOException e){
//            e.printStackTrace();
//        } catch (ClassNotFoundException e){
//            e.printStackTrace();
//        }
//
//        System.out.println();
//
//        hm.forEach((key, value)->{
//            System.out.println(key + " " + value);
//        });
//    }
    }
}