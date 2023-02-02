package CSC370_HW1;
public class Test {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        int [][] tiles = new int[3][3];
        for (int i =0; i<3; i++) {
            for (int j=0; j<3; j++) {
                tiles[i][j] = i*3 + j; 
            }
        }

        Board b = new Board(tiles);

        System.out.println(b.toString());
    }

}