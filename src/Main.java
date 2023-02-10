import meddle.Controller.TexasHoldem;
import meddle.Controller.starttest01;
import meddle.Entity.HandEvaluator;
import meddle.Entity.Player;

public class Main {
    public static void main(String[] args) {
//        TexasHoldem game =new TexasHoldem();
        starttest01 game =new starttest01();
        Player p1 = new Player("brad01");
        Player p2 = new Player("brad02");
        Player p3 = new Player("brad03");
        game.addPlayer(p1);
        game.addPlayer(p2);
        game.addPlayer(p3);

        game.startGame();



    }
}