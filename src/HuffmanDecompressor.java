import java.util.HashMap;

public class HuffmanDecompressor {
    HashMap<String, Character> huffmanTable;
    BinReaderWriter readerWriter;
    String inputStream;
    String outputStream;
    public HuffmanDecompressor(){
        readerWriter = new BinReaderWriter();
        huffmanTable = new HashMap<>();
        inputStream = readerWriter.readBin(huffmanTable);
    }

    public void decompress(){
        String current = "";
        outputStream = "";
        for(char c: inputStream.toCharArray()){
            current += c;
            if(huffmanTable.containsKey(current)){
                outputStream += huffmanTable.get(current);
                current = "";
            }
        }
    }
}
