package Test;

import java.util.Arrays;
import java.util.HashSet;
import Test.utils.TextIO;
/**
 * @overview A program that performs the coffee tin game on a
 *    tin of beans and display result on the standard output.
 *
 * @author dmle
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

    private static final char[] BeansBag = new char[150];

    static {
        Arrays.fill(BeansBag, 0, 50, BLUE);
        Arrays.fill(BeansBag, 51, 100, GREEN);
        Arrays.fill(BeansBag, 101, 150, REMOVED);
    }

    public static int randInt(int n) {
        return (int) Math.floor(Math.random() * n);
    }

    public static char getBean(char[] beansBag, char bean) {
        int n = beansBag.length;
        boolean flag = false; //this use to define if beansBag is empty or no longer contains required bean
        for(int i = 0;i < n; i++) {
            if(beansBag[i] == bean)
                flag = true;
        }
        if (!flag)
            return '-';
        int randomIndex = randInt(n);
        while(beansBag[randomIndex] != bean) {
            randomIndex = randInt(n);
        }
        return beansBag[randomIndex];
    }

    public static char[] updateTin(char[] tin, char firstBean, char secondBean) {
        if(firstBean == secondBean) {
            getBean(BeansBag, BLUE);
            putIn(tin, BLUE);
        }
        else {
            getBean(BeansBag, GREEN);
            putIn(tin, GREEN);
        }
        return tin;
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
        // initialise some beans
        char[][] tins = {
                {BLUE, BLUE, BLUE, GREEN, GREEN},
                {BLUE, BLUE, BLUE, GREEN, GREEN, GREEN},
                {GREEN},
                {BLUE},
                {BLUE,GREEN},
                {GREEN, BLUE, BLUE, GREEN, REMOVED}
        };

        for (int i = 0; i < tins.length; i++) {
            char[] tin = tins[i];

            // expected last bean
            // p0 = green parity /\
            // (p0=1 -> last=GREEN) /\ (p0=0 -> last=BLUE)
            // count number of greens
            int greens = 0;
            for (char bean : tin) {
                if (bean == GREEN)
                    greens++;
            }
            final char last = (greens % 2 == 1) ? GREEN : BLUE;

            // print the content of tin before the game
            System.out.printf("%nTin (%d Gs): %s %n", greens, Arrays.toString(tin));

            // perform the game
            // lastBean = last \/ lastBean != last
            char lastBean = tinGame(tin);

            // print the content of tin and last bean
            System.out.printf("tin after: %s %n", Arrays.toString(tin));

            // check if last bean as expected and print
            if (lastBean == last) {
                System.out.printf("last bean: %c%n", lastBean);
            } else {
                System.out.printf("Something wrong in last bean: %c (expected: %c)%n",lastBean,last);
            }
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
    public static char tinGame(char[] tin) {
        while (hasAtLeastTwoBeans(tin)) {
            // take two beans from tin
            char[] twoBeans = takeTwo(tin);
            // process beans to update tin
            char b1 = twoBeans[0];
            char b2 = twoBeans[1];
            // process beans to update tin
            tin = updateTin(tin, b1, b2);
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

        // not enough beans
        return false;
    }

    /**
     * @requires tin has at least 2 beans left
     * @modifies tin
     * @effects
     *  remove any two beans from tin and return them
     */
    private static char[] takeTwo(char[] tin) {
        char first  = takeOne(tin);
        char second = takeOne(tin);

        return new char[] {first, second};
    }

    /**
     * @requires tin has at least one bean
     * @modifies tin
     * @effects
     *   remove any bean from tin and return it
     */

    //updated takeOne method, using randint
    public static char takeOne(char[] tin) {
        int i = randInt(tin.length);
        char bean = tin[i];
        HashSet index = new HashSet();

        //re-random
        while(bean == REMOVED) {
            // no beans left
            if (index.size() == tin.length) {
                return NULL;
            }

            index.add(i);
            i = randInt(tin.length);
            bean = tin[i];
        }

        // found one
        tin[i] = REMOVED;
        return bean;
    }

    /**
     * @requires tin has vacant positions for new beans
     * @modifies tin
     * @effects
     *   place bean into any vacant position in tin
     */
    private static void putIn(char[] tin, char bean) {
        for (int i = 0; i < tin.length; i++) {
            // vacant position
            if (tin[i] == REMOVED) {
                tin[i] = bean;
                break;
            }
        }
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

        // no beans left
        return NULL;
    }
}
