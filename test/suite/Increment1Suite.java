package suite;

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