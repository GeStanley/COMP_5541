package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  structure.UnitTestCell.class,
  structure.UnitTestFormula.class,
  structure.UnitTestTable.class,
  ui.UnitTestGridModel.class,
  ui.UnitTestGui.class,
  ui.UnitTestInputLine.class,
  ui.UnitTestSaveFile.class,
  ui.UnitTestSpreadSheet.class
})

public class Increment2Suite {
  // the class remains empty,
  // used only as a holder for the above annotations
}