import java.util.ArrayList;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 * -------------------------------- representa una subasta. --------------------
 * @author David J. Barnes and Michael Kölling.
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
     * Este m�todo debe mostrar por pantalla los detalles de todos los items que se est�n subastando actualmente.
     * De aquellos por los que haya habido pujas se debe indicar el nombre de la persona que ha hecho la puja m�s alta y
     * el valor de dicha puja; del resto debe indicar que no ha habido pujas.
     * -------------------------------------------------------------------------------------- 0060
     */
    public void close(){
        System.out.println("---     ITEMS FOR AUCTION    ----" ); 
        System.out.println("---                   ----" ); 
        for(Lot lot: lots){
            if(lot.getHighestBid() != null){
                System.out.println("---Detalle del item por los que se ha pujado:  ----" );                
                System.out.println("Posici�n del objeto subastado: " +lot.getNumber()+ "� ");
                System.out.println("Descripci�n del objeto: " +lot.getDescription());
                System.out.println("La puja m�s alta ha sido de: " +lot.getHighestBid().getValue()+ " �");
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
     *devuelva una colecci�n de todos los items por los que no habido ninguna puja en este momento; este m�todo no debe 
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

    /**
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    public Lot getLot(int lotNumber)
    {
        if((lotNumber >= 1) && (lotNumber < nextLotNumber)) {
            // The number seems to be reasonable.
            Lot selectedLot = lots.get(lotNumber - 1);
            // Include a confidence check to be sure we have the
            // right lot.
            if(selectedLot.getNumber() != lotNumber) {
                System.out.println("Internal error: Lot number " +
                    selectedLot.getNumber() +
                    " was returned instead of " +
                    lotNumber);
                // Don't return an invalid lot.
                selectedLot = null;
            }
            return selectedLot;
        }
        else {
            System.out.println("Lot number: " + lotNumber +
                " does not exist.");
            return null;
        }
    }
}
