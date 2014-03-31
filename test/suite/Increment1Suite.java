package suite;

/**
 * This testing suite was developed for the first increment of the project.
 * 
 * @author 	Ankita Mishara, Geoffrey Stanley, Michael Burkat, 
 * @author	Nicholas Reinlein, Sofiane Benaissa, Tengzhong Wen
 * 
 * Date 31-03-2014
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  structure.UnitTestCell.class,
  structure.UnitTestFormula.class,
  structure.UnitTestTable.class,
  ui.UnitTestSaveFile.class
})

public class Increment1Suite {
  // the class remains empty,
  // used only as a holder for the above annotations
}