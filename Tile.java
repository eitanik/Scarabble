package test;

import java.util.Random;
import java.util.Objects;

public class Tile {
    final public char letter;
    final public int score;

    private Tile(char letter, int score)//לשלוט בכמות העריכים
    {
        this.letter = letter;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return letter == tile.letter && score == tile.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, score);
    }

    public static class Bag {

        public static Bag b = null;
        int[] Quantity = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
        int[] LegalQuantity = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
        Tile[] TileArr = {new Tile('A', 1), new Tile('B', 3), new Tile('C', 3), new Tile('D', 2), new Tile('E', 1), new Tile('F', 4), new Tile('G', 2), new Tile('H', 4), new Tile('I', 1), new Tile('J', 8), new Tile('K', 5), new Tile('L', 1), new Tile('M', 3), new Tile('N', 1), new Tile('O', 1), new Tile('P', 3), new Tile('Q', 10), new Tile('R', 1), new Tile('S', 1), new Tile('T', 1), new Tile('U', 1), new Tile('V', 4), new Tile('W', 4), new Tile('X', 8), new Tile('Y', 4), new Tile('Z', 10)};
        Tile arr;

        private Bag(int[] quantity, Tile[] tileArr) {
            for (int i = 0; i < quantity.length; i++) {
                this.Quantity[i] = quantity[i];
                this.TileArr[i] = tileArr[i];
            }
        }

        public Bag() {

        }

        public static Bag getBag() {

            int[] Quantity = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
            Tile[] TileArr = {new Tile('A', 1), new Tile('B', 3), new Tile('C', 3), new Tile('D', 2), new Tile('E', 1), new Tile('F', 4), new Tile('G', 2), new Tile('H', 4), new Tile('I', 1), new Tile('J', 8), new Tile('K', 5), new Tile('L', 1), new Tile('M', 3), new Tile('N', 1), new Tile('O', 1), new Tile('P', 3), new Tile('Q', 10), new Tile('R', 1), new Tile('S', 1), new Tile('T', 1), new Tile('U', 1), new Tile('V', 4), new Tile('W', 4), new Tile('X', 8), new Tile('Y', 4), new Tile('Z', 10)};

            if (b == null) {

                b = new Bag(Quantity, TileArr);

            }
            return b;
        }


        public Tile getRand() {
            int rnd = (int)(Math.random()*26);
            int x = size();
            int z;
            int count=0;

            if (x == 0) {
                return null;
            }

            if (Quantity[rnd] == 0) {
                z = rnd;
                while (Quantity[z] != 0) {
                    z++;
                    count++;
                    if (z == 26) {
                        z = 0;
                    }
                    if(count==26)
                    {
                        return null;
                    }
                }
            }
            Quantity[rnd] = Quantity[rnd] - 1;

            return TileArr[rnd];

        }

        public Tile getTile(char c) {
            int x = size();
            int t = -1;

            for (int i = 0; i < 26; i++) {
                if (TileArr[i].letter == c) {
                    t = i;
                }
            }
            if (t == -1) {
                return null;
            }
            if (Quantity[t] == 0) {
                return null;
            }
            Quantity[t] = Quantity[t] - 1;

            if (x == 0) {
                return null;
            }
            return TileArr[t];
        }

        public void put(Tile x) {
            for (int i = 0; i < 26; i++) {
                if (TileArr[i] == x) {
                    if (Quantity[i] + 1 <= LegalQuantity[i]) {
                        Quantity[i]++;
                    }
                }
            }
        }

        public int size() {
            int sum = 0;

            for (int i = 0; i < 26; i++) {
                sum += Quantity[i];
            }
            return sum;
        }

        public int[] getQuantities() {
            int[] copy = new int[26];

            for (int i = 0; i < copy.length; i++) {
                copy[i] = Quantity[i];
            }
            return copy;
        }


    }
}

