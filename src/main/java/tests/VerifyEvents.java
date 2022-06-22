package tests;

import static org.junit.Assert.fail;
import org.junit.Test;

import delivery.SimpleConsumerDemo;

/*
 *  class initiates a web search from two different search engines (google, bing) and then compares their results.
 *  browser, keyword and other parameters are retrieved from the web_search.properties file thus ensure its validity
 *  before executing.
 *  
 *	Test case: 
 *	1. Run <browser> 
 *	2. Clear <browser> cookies 
 *	3. Go to the 1st search engine 
 *	4. Search using the <keyword> 
 *	5. Parse the first 10 search result items (e.g. url, title, short description): store parsed info as structured data of any chosen by you type (i.e. hash of hashes or array of hashes, whatever structure handy to be parsed). Exclude (don’t count on): nested search items, search engine suggestions, embedded videos, etc while working with search results. 
 *	6. Make sure at least one attribute of each item from parsed search results contains <keyword> 
 *	7. Log in stdout which search results items and their attributes contain <keyword> and which do not. 
 *	8. Repeat steps #3 - #7 for the 2nd search engine 
 *	9. Compare stored results for both engines and list out the most popular items (the ones which were found in both search engines) 
 */

public class VerifyEvents {

	private final String testDescription = " \r\n" + "  * Test case description: \r\n" + "  *	1. Run <browser> \r\n"
			+ "  *	2. Clear <browser> cookies \r\n" + "  *	3. Go to the 1st search engine \r\n"
			+ "  *	4. Search using the <keyword> \r\n"
			+ "  *	5. Parse the first 10 search result items (e.g. url, title, short description): store parsed info as structured data of any chosen by you type (i.e. hash of hashes or array of hashes, whatever structure handy to be parsed). Exclude (don’t count on): nested search items, search engine suggestions, embedded videos, etc while working with search results. \r\n"
			+ "  *	6. Make sure at least one attribute of each item from parsed search results contains <keyword> \r\n"
			+ "  *	7. Log in stdout which search results items and their attributes contain <keyword> and which do not. \r\n"
			+ "  *	8. Repeat steps #3 - #7 for the 2nd search engine \r\n"
			+ "  *	9. Compare stored results for both engines and list out the most popular items (the ones which were found in both search engines) \r\n"
			+ "  */";

	@Test
	public void CompareSearchEngineResultsTest() {

		try {

			System.out.println("publishing");
			SimpleConsumerDemo.verifyEvents();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			fail(e.getMessage());

		} finally {
			System.out.println("goodbye!");
		}
	}

}
