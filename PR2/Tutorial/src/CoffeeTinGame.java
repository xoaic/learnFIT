package package Tut09.a;

import java.util.Arrays;
import utils.TextIO;

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
  /** the char array for beanBags*/
  private static final char [] BeanBags = {
		  BLUE,BLUE,BLUE,BLUE,BLUE,BLUE,BLUE,BLUE,BLUE,BLUE,
		  GREEN,GREEN,GREEN,GREEN,GREEN,GREEN,GREEN,GREEN,GREEN,GREEN,
		  REMOVED,REMOVED,REMOVED,REMOVED,REMOVED,REMOVED,REMOVED,REMOVED,REMOVED,REMOVED,
  };

 /**
  * the main procedure
  * @effects 
  *    Initialize a coffee tin
  *    {@link TextIO#putf(String, Object...)}: print the tin content
  *    {@link @tinGame(char[])}: perform the coffee tin game on tin
  *    {@link TextIO#putf(String, Object...)}: print the tin content again
  *    if last bean is correct
  *      {@link TextIO#putf(String, Object...)}: print its color 
  *    else
  *      {@link TextIO#putf(String, Object...)}: print an error message
  */
	public static void main(String[] args) {
		// initialise some beans
		 char [] tin = {BLUE,BLUE,BLUE,BLUE,BLUE,BLUE,GREEN,GREEN,GREEN,GREEN,GREEN,GREEN};
		// initialise bean bag
//		beanBags = new char[30];
//		for (int i = 0; i < 30; i++) {
//			if (i < 10) {
//				BeanBags[i] = BLUE;
//			} else if (i < 20) {
//				BeanBags[i] = GREEN;
//			}
//		}
	
		// count number of greens
		int greens = 0;
		for (char b : tin) {
			if (b == GREEN)
				greens++;
		}
		// Find out the expected bean
		final char expectedBean = (greens % 2 == 1) ? GREEN : BLUE;
		// print the tin content before involving the tinGame
		TextIO.putf("tin before: %s %n", Arrays.toString(tin));

		// Involving the tinGame
		char lastBean = tinGame(tin);

		// print the tin content after finishing the tinGame
		TextIO.putf("tin after: %s %n", Arrays.toString(tin));

		// check if the expected bean is equal to the real last bean
		if (lastBean == expectedBean) {
			TextIO.putf("last bean: %c %n %n", lastBean);
		} else {
			TextIO.putf("Oops, wrong last bean: %c (expected: %c)%n", expectedBean, lastBean);
		}
	}
  

  /**
   * Performs the coffee tin game to determine the color of the last bean
   * 
   * @requires tin is not null /\ tin.length > 0
   * @modifies tin
   * @effects <pre>
   *   take out two beans from tin
   *   if same color
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

		// invoke updateTin()
		char[] a = updateTin(tin);
		// Initialize the last bean is removed
		char lastBean = REMOVED;
		int l = tin.length;

		for (int i = 0; i < l; i++) {
			if (a[i] != REMOVED) {
				// a[i] is not remove
				// lastBean is a[i]
				lastBean = a[i];
				break;
			}
		}
		// return color of the lastBean
		return lastBean;

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
   * @requires <tt>tin != null /\ tin.length >= 1</tt>
   * @modifies <tt>tin</tt>
   * @effects 
   * 	<tt>select randomly a bean from tin 
   *   	remove it from tin and return it
   *	</tt>
   */
	public static char takeOne(char[] tin) {
		int ranNum;
		char ranBean;
		do {
			ranNum = randInt(tin.length);
			ranBean = tin[ranNum];

		} while (ranBean == REMOVED);
		tin[ranNum] = REMOVED;
		return ranBean;
	}
	
	
	
  /**
   * @requires <tt>n : integer /\ n > 0</tt>
   * @effects
   * 	<tt>
   * 	input an integer n and return a random integer from the range [0,n)
   *	</tt>
   */
  public static int randInt(int n) {
	return (int)(Math.random()*n);  
  }
  
  
  
  
  /**
   * @requires <tt> beanBags != null /\ 
   * 		  		beanBags.length > 0 /\
   * 		   		beanType != null 
   * 		   </tt>
   * @modifies <tt>beanBags /\ beanType</tt>
   * @effects
   * 	<tt>
   * 	let ranBean = beanBags[i]| 0 < i < beanBags.length
   * 	if beanType == ranBean
   * 		return ranBean 
   * 	</tt>
   */

  public static char getBean(char[] beanBags, char beanType) {
	  int i = 0;
	  //create an array to contain only beanType from the beanBags 
	  char[] beanTypes =  new char[beanBags.length];
	  char ranBean;
	  int ranBeanPos;
	  for(char b : beanBags) {
		  if(b == beanType) {
			  beanTypes[i] = b;
			  i++;
		  }else {
			  beanTypes[i] = NULL;
			  i++;
		  }
	  }do {
		  ranBeanPos = randInt(beanTypes.length);
		  ranBean = beanTypes[ranBeanPos]; 
	} while (ranBean == NULL);
	  beanBags[ranBeanPos] = REMOVED;
	  return ranBean;
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
   *      put the green one backrandInt
   *  </tt>
   */
  private static char[] updateTin(char[] tin) {
	  char bean1;
	  char bean2;
	  int count = tin.length;
	  while(count >= 2) {
		  char[] takeTwo = takeTwo(tin);
		  bean1 = takeTwo[0];
		  bean2 = takeTwo[1];
		  if(bean1 == bean2) {
			 char beanTemp = getBean(BeanBags, BLUE);
			  for(int i = 0; i < tin.length; i++) {
				  if(tin[i] == REMOVED) {
					  tin[i] = beanTemp;
					  break;
				  }
			  }
		  }else {
			  if(bean1 == BLUE || bean2 == BLUE) {
				  char beanTemp = getBean(BeanBags, GREEN);
				  for(int i = 0; i < tin.length; i++) {
					  if(tin[i] == REMOVED) {
						  tin[i] = beanTemp;
						  break;
					  }
				  }
			  }
		  }
		  count--;
	  }
	  return tin;
  }
}
