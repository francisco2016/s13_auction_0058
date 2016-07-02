import java.util.ArrayList;
import java.util.Iterator;
/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 * -------------------------------- representa una subasta. --------------------
 * @author David J. Barnes and Michael KÃ¶lling.
 * @version 2011.07.31
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;
    // The list of Lots in this auction sin puja.----------------------------------------- 0061
    private ArrayList<Lot> lotsSinPuja;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<Lot>();
        nextLotNumber = 1;
        lotsSinPuja = new ArrayList<>(); 
    }

    /**
     * Este método debe mostrar por pantalla los detalles de todos los items que se estén subastando actualmente.
     * De aquellos por los que haya habido pujas se debe indicar el nombre de la persona que ha hecho la puja más alta y
     * el valor de dicha puja; del resto debe indicar que no ha habido pujas.
     * -------------------------------------------------------------------------------------- 0060
     */
    public void close(){
        System.out.println("---     ITEMS FOR AUCTION    ----" ); 
        System.out.println("---                   ----" ); 
        for(Lot lot: lots){
            if(lot.getHighestBid() != null){
                System.out.println("---Detalle del item por los que se ha pujado:  ----" );                
                System.out.println("Posición del objeto subastado: " +lot.getNumber()+ "º ");
                System.out.println("Descripción del objeto: " +lot.getDescription());
                System.out.println("La puja más alta ha sido de: " +lot.getHighestBid().getValue()+ " €");
                System.out.println("Nombre del pujador: " +lot.getHighestBid().getBidder().getName() );
                System.out.println("    ---         ---         ---");
            }
            else
            {
                System.out.println("------  Objeto/s sin puja en la subasta :  ----" );
                System.out.println(lot.toString());
            }
        }
    }

    /**
     *devuelva una colección de todos los items por los que no habido ninguna puja en este momento; este método no debe 
     *imprimir nada por pantalla.------------------------------------------------------------------------- 0061
     */
    public ArrayList<Lot> getUnsold (){
        ArrayList<Lot> lotSinP = new ArrayList<>();
        for(Lot lot: lots){
            if(lot.getHighestBid() == null){
                lotSinP.add(lot);
            }
        }
        return lotSinP;
    }

    /**
     * 
     * Return the lot with the given number. Return null* 
     * reescribir este mt- para que, en el caso de que implementaramos un imaginario método que eliminara objetosLot de la 
     * colección de objetos Lot de la clase Auction, dicho método getLot siguiera funcionando correctamente.
     * ----------------------------------------------------------------------------------------------  0062
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    public Lot getLot(int lotNumber)
    {
        Lot selectedLot = null;
        boolean encontrado = false;
        int index = 0;
        while(index < lots.size() && !encontrado) {          
            if(lots.get(index).getNumber() == lotNumber) {
                selectedLot = lots.get(index);
                encontrado = true;
            } 
            index ++;
        }
        if(!encontrado) {
            System.out.println("Lot number: " + lotNumber +
                " does not exist.");
            selectedLot = null;
        }
        return selectedLot; 
    }

    /**
     *Implementa en la clase Auction un método denominado removeLot que reciba como parámetro un entero que represente el número 
     *identificador de un item y elimine dicho item de la colección de items. No se puede asumir que un item n estará en la
     *posición n-1 por la posibilidad de que haya borrado de elementos. Este método debe devolver el elemento eliminado o null
     *en caso de que dicho elemento no exista.------------------------------------------------------------- 0062
     */
    //     public Lot removeLot(int num){
    //         Lot lotEliminado = null;
    //         if(lotEliminado.getNumber() != null){
    //             lots.remove(num);
    //         }
    //         return lotEliminado;
    //     }

    /**
     * Enter a new lot into the auction.
     * --------------------------------Introducir un nuevo lote en la subasta.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
            System.out.println( );
        }
    }

    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            boolean successful = selectedLot.bidFor(new Bid(bidder, value));
            if(successful) {
                System.out.println("The bid for lot number " +
                    lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                System.out.println("Lot number: " + lotNumber +
                    " already has a bid of: " +
                    selectedLot.getHighestBid().getValue());
            }
        }
    }

}
