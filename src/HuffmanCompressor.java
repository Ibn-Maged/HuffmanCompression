import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.PriorityQueue;

class HuffmanNode implements Comparable<HuffmanNode>{
    Character data;
    int frequency;
    HuffmanNode left = null, right = null;
    HuffmanNode(char data, int frequency){
        this.data = data;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}


public class HuffmanCompressor {
    HashMap<String, Character> huffmanTable;
    public void compress(String stream){
        huffmanTable = new HashMap<>();
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        HashMap<Character, Integer> freq = new HashMap<>();
        for(int i = 0; i < stream.length(); i++){
            if(freq.containsKey(stream.charAt(i))){
                freq.replace(stream.charAt(i), freq.get(stream.charAt(i)) + 1);
            } else {
                freq.put(stream.charAt(i), 1);
            }
        }

        freq.forEach((key, value) ->{
            pq.add(new HuffmanNode(key, value));
        });

        while(pq.size() > 1){
            HuffmanNode node1 = pq.remove();
            HuffmanNode node2 = pq.remove();
            HuffmanNode node3 = new HuffmanNode('0', node1.frequency + node2.frequency);
            node3.data = null;
            node3.left = node1;
            node3.right = node2;
            pq.add(node3);
        }

        traverse(pq.peek(),"");
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("output.bin"));
            oos.writeObject(huffmanTable);
        } catch (IOException e){
            e.printStackTrace();
        }

        StringBuilder outStream = new StringBuilder();
        for(char c: stream.toCharArray()){
            outStream.append(huffmanTable.get(c));
        }
        try {
            oos.writeObject(outStream.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void traverse(HuffmanNode node, String code){
        if(node.left == null && node.right == null){
            huffmanTable.put(code, node.data);
            return;
        }

        traverse(node.left, code + "0");
        traverse(node.right, code + "1");
    }
}
