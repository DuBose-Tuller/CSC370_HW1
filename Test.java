package CSC370_HW1;
public class Test {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        int [][] tiles = new int[3][3];
        tiles[0][0] = 0;    
        tiles[0][1] = 3;
        tiles[0][2] = 2;
        tiles[1][0] = 1;
        tiles[1][1] = 4;
        tiles[1][2] = 7;
        tiles[2][0] = 6;
        tiles[2][1] = 5;
        tiles[2][2] = 8;


        Board b = new Board(tiles);

        System.out.println(b.toString());
        System.out.println(b.total_displaced());
    }

}