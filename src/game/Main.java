package game;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeyException {
        if (args.length % 2 != 1 || args.length < 3 ) {
            System.out.println("Incorrect input!");
            System.out.println("Correct input is [unique name] [unique name] [unique name] ...");
            System.out.println("The number of unique names must be more than 3 and odd.");
        } else {
            Table table = new Table(args);
            Rules rules = new Rules(args);
            Key key = new Key(args);
            table.TableCalculate();
            int n;
            while (true) {

                key.KeyGen();
                System.out.println("HMAC: " + key.getHMAC().toUpperCase());
                System.out.println("Available moves: ");
                for (int e =0; e<args.length;e++) {
                    System.out.println(e+1+" - "+args[e]);
                }
                System.out.println(0 + " - exit");
                System.out.println("? - help");
                Reader r = new InputStreamReader(System.in);
                BufferedReader bre = new BufferedReader(r);
                System.out.print("Enter your move: ");
                String input = bre.readLine();

                if (input.equals("?")) table.showTable();
                if (input.equals("0")) break;
                else {
                    try {
                        n = Integer.parseInt(input)-1;
                    } catch (NumberFormatException e){
                        continue;
                    }
                    if (n >= 0 && n<args.length) {
                        System.out.println("You move: " + args[n]);
                        System.out.println("Comuter move: " + args[key.getMove()]);
                        if (key.getMove()==n) System.out.println("Draw!");
                        else if (rules.CheckWin(n,key.getMove())) System.out.println("You win!");
                        else System.out.println("You loose!");

                        System.out.println("HMAC key: " + key.getKey().toUpperCase());
                    }

                }

            }
        }
    }
}
