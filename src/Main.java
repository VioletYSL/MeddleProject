import meddle.Controller.Table;
import meddle.Controller.TexasHoldem;
import meddle.Controller.starttest01;
import meddle.Entity.HandEvaluator;
import meddle.Entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {


//        TexasHoldem game =new TexasHoldem();
        ArrayList<Player> ps = new ArrayList<>();
        Table game = new Table();
        Player p1 = new Player("brad01");
        Player p2 = new Player("brad02");
        Player p3 = new Player("brad03");
        Player p4 = new Player("brad04");


        ps.add(p1);ps.add(p2);ps.add(p3);ps.add(p4);
        game.addPlayers(ps);

        game.startGame();




    }
}