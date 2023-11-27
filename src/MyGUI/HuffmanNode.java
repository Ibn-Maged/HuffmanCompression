package MyGUI;
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
