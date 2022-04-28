package Cuc;

import java.util.Arrays;
import Cuc.utils.TextIO;

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
    /** the bean array*/
    private static final char[] beansBag = new char[90];

    static {
        Arrays.fill(beansBag, 0, 30, BLUE);
        Arrays.fill(beansBag, 31, 60, GREEN);
        Arrays.fill(beansBag, 61, 90, REMOVED);
    }

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
        // Initialise demo beans
        char[][] tins = {{BLUE, BLUE, BLUE, GREEN, GREEN}, {BLUE, BLUE, GREEN, GREEN}, {BLUE, GREEN}, {GREEN},
                {BLUE}};

        for (char[] tin : tins) {
            int greens = 0;

            for (char bean : tin) {
                if (bean == GREEN) {
                    greens++;
                }
            }

            final char expected = (greens % 2 == 1) ? GREEN : BLUE;

            // Print bean tin first
            TextIO.putf("%ntin with %d Greens: %s %n", greens, Arrays.toString(tin));

            char lastBean = tinGame(tin);

            // Print bean tin after
            TextIO.putf("tin after: %s %n", Arrays.toString(tin));

            // Check and print result
            if (lastBean == expected) TextIO.putf("Last bean: %c%n", lastBean);
            else TextIO.putf("Oops, wrong last bean: %c (expected: %c)%n", lastBean, expected);
        }
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
        while (hasAtLeastTwoBeans(tin)) {
            // Take two beans from tin
            char[] twoBeans = takeTwo(tin);

            updateTin(tin, twoBeans);
        }
        return anyBean(tin);
    }

    /**
     * @effects
     *  if tin has at least two beans
     *    return true
     *  else
     *    return false
     */
    private static boolean hasAtLeastTwoBeans(char[] tin) {
        int count = 0;

        for (char bean : tin) {
            if (bean != REMOVED) {
                count++;
            }

            if (count >= 2) // enough beans
                return true;
        }

        return false;
    }

    /**
     * @requires tin has at least 2 beans left
     * @modifies tin
     * @effects
     *  remove any two beans from tin and return them
     */
    private static char[] takeTwo(char[] tin) {
        char[] takeTwo = new char[2];
        takeTwo[0] = takeOne(tin);
        takeTwo[1] = takeOne(tin);
        return takeTwo;
    }

    /**
     * @requires tin has at least one bean
     * @modifies tin
     * @effects
     *   remove any bean from tin and return it
     */
    private static char takeOne(char[] tin) {
        int count = 0;

        for (char bean : tin) {
            if (bean != REMOVED) {
                count++;
            }
        }

        char bean;

        if (count >= 1) {
            do {
                int i = randInt(tin.length);
                bean = tin[i];
                if (bean != REMOVED) {
                    tin[i] = REMOVED;
                    return bean;
                }
            } while (true);
        }
        return NULL;
    }

    /**
     * @effects
     *   if beanType == ranBean
     *   	return ranBean
     */
    public static char getBean(char[] beansBag, char beanType) {
        if (anyBean(beansBag) != NULL) {
            char bean;
            int i;

            do {
                i = randInt(beansBag.length);
                bean = beansBag[i];
            } while (bean == REMOVED && bean != beanType);

            beansBag[i] = REMOVED;
            return bean;
        }
        return NULL;
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
        char bean1 = twoBeans[0], bean2 = twoBeans[1];

        if (bean1 == bean2) {
            if (bean1 != BLUE) {
                getBean(beansBag, BLUE);
            }
            putIn(tin, BLUE);
        } else {
            putIn(tin, GREEN);
        }
    }

    /**
     * @requires tin has vacant positions for new beans
     * @modifies tin
     * @effects
     *   place bean into any vacant position in tin
     */
    private static void putIn(char[] tin, char bean) {
        for (int i = 0; i < tin.length; i++) {
            if (tin[i] == REMOVED) {
                tin[i] = bean;
                break;
            }
        }
    }

    /**
     * @effects
     *   return random number
     */
    public static int randInt(int n) {
        return (int) (Math.random() * n);
    }

    /**
     * @effects
     *  if there are beans in tin
     *    return any such bean
     *  else
     *    return '\u0000' (null character)
     */
    private static char anyBean(char[] tin) {
        for (char bean : tin) {
            if (bean != REMOVED) {
                return bean;
            }
        }
        return NULL;
    }
}
