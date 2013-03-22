/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package broadcast;
import java.io.File;

/**>
 *
 * @author uzielgl
 */
public class Broadcast {

    /** >
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MainWindow main1 = new MainWindow( new File( "p1.txt" ), "p1"  );
        main1.setVisible(true);
        
        MainWindow main2 = new MainWindow( new File( "p2.txt" ), "p2"  );
        main2.setVisible(true);
        
        MainWindow main3 = new MainWindow( new File( "p3.txt" ), "p3"  );
        main3.setVisible(true);
        
        MainWindow main4 = new MainWindow( new File( "p4.txt" ), "p4"  );
        main4.setVisible(true);
        
    }
}
