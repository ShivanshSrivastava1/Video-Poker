
public class PokerTest {

 /************************************************************
 * Tester file for video poker.
 * 
 * @author Shivansh Srivastava (ss5945)
 * @date 4/27/2020
 ************************************************************/
 
    public static void main(String[] args){
        if (args.length<1){
            Game g = new Game();
            g.play();
        }
        else{
            Game g = new Game(args);
            g.play();
        }
    }

}
