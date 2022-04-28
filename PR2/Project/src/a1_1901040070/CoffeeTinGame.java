package Hai;

import Hai.utils.TextIO;

/**
 * @overview A program that performs the coffee tin game on a
 *    tin of beans and display result on the standard output.
 *
 */
public class CoffeeTinGame {
    /** constant value for the green bean*/
    private static final char GREEN = 'G';
    /** constant value for the blue bean*/
    private static final char BLUE = 'B';
    /** constant for removed beans */
    private static final char REMOVED = '-';
    /** the null character*/
    private static final char NULL = '\u0000';

    /**
     * the main procedure
     * @effects
     *    initialise a coffee tin
     *    {@link TextIO#putf(String, Object...)}: print the tin content
     *    {@link @tinGame(char[])}: perform the coffee tin game on tin
     *    {@link TextIO#putf(String, Object...)}: print the tin content again
     *    if last bean is correct
     *      {@link TextIO#putf(String, Object...)}: print its colour
     *    else
     *      {@link TextIO#putf(String, Object...)}: print an error message
     */
    public static void main(String[] args) {

    }

    /**
     * Performs the coffee tin game to determine the colour of the last bean
     *
     * @requires tin is not null /\ tin.length > 0
     * @modifies tin
     * @effects <pre>
     *   take out two beans from tin
     *   if same colour
     *     throw both away, put one blue bean back
     *   else
     *     put green bean back
     *   let p0 = initial number of green beans
     *   if p0 = 1
     *     result = `G'
     *   else
     *     result = `B'
     *   </pre>
     */
    private static char tinGame(char[] tin) {
        return 0;
    }

    /**
     * @effects
     *  if tin has at least two beans
     *    return true
     *  else
     *    return false
     */
    private static boolean hasAtLeastTwoBeans(char[] tin) {
        return false;
    }

    /**
     * @requires tin has at least 2 beans left
     * @modifies tin
     * @effects
     *  remove any two beans from tin and return them
     */
    private static char[] takeTwo(char[] tin) {
        return null;
    }

    /**
     * @requires tin has at least one bean
     * @modifies tin
     * @effects
     *   remove any bean from tin and return it
     */
    private static char takeOne(char[] tin) {
        return 0;
    }

    /**
     * Update tin according to the game moves.
     *
     * @requires <tt>tin != null /\
     *  twoBeans != null /\ twoBeans.length=2 /\
     * twoBeans[0], twoBeans[1] in { BLUE, GREEN } </tt>
     * @modifies <tt>tin</tt>
     * @effects <tt>let b1 = twoBeans[0], b2 = twoBeans[1]
     *    if b1 = b2
     *      throw both away
     *      put a blue bean back
     *    else
     *      throw away the blue bean
     *      put the green one back
     *  </tt>
     */
    private static void updateTin(char[] tin, char[] twoBeans) {

    }

    /**
     * @requires tin has vacant positions for new beans
     * @modifies tin
     * @effects
     *   place bean into any vacant position in tin
     */
    private static void putIn(char[] tin, char bean) {

    }

    /**
     * @effects
     *  if there are beans in tin
     *    return any such bean
     *  else
     *    return '\u0000' (null character)
     */
    private static char anyBean(char[] tin) {
        return 0;
    }
}
