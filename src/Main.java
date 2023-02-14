import meddle.Controller.TexasHoldem;
import meddle.Entity.Player;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {


//        TexasHoldem game =new TexasHoldem();
        ArrayList<Player> ps = new ArrayList<>();
        TexasHoldem game = new TexasHoldem();
        Player p1 = new Player("brad01");
        Player p2 = new Player("brad02");
        Player p3 = new Player("brad03");
        Player p4 = new Player("brad04");
        Player p5 = new Player("brad05");

        ps.add(p1);ps.add(p2);ps.add(p3);ps.add(p4);
        ps.add(p5);
        game.addPlayers(ps);

        game.startGame();




    }
}