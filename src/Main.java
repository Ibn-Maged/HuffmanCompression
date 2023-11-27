import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HuffmanCompressor hc = new HuffmanCompressor();
//        hc.compress("AAAABABBBCC");
        hc.compress("AAABBCD");
        HuffmanDecompressor dc = new HuffmanDecompressor();
        dc.decompress();
        System.out.println(dc.outputStream);
//        hc.compress(input);
//        hc.huffmanTable.forEach((key, value)->{
//            System.out.println(key + " " + value);
//        });
//        hc.readBin();

//        BinReaderWriter readerWriter = new BinReaderWriter();
//        try {
//            readerWriter.write(hc.output, hc.huffmanTable);
////            readerWriter.read();
//        } catch (IOException e){
//            e.printStackTrace();
//        }
    }
}