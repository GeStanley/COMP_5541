package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ui.UnitTestGuiAuto.class,
	ui.UnitTestStress.class
})
public class Increment3Suite {
	// the class remains empty,
	// used only as a holder for the above annotations
}
