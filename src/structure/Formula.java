package structure;
import java.util.LinkedList;
import structure.Table;

/**
 * This class allows the calculation of a mathematical formula
 */
public class Formula {
	private String formula;
	private double result;
	private Table table;
	// Debug = true will print status messages to the console
	private static boolean debug = false;
	

	/**
	 * Constructor
	 * 
	 * @param form The formula
	 * @param tbl The linked table, for cell references
	 */
	public Formula(String form, Table tbl) {
		formula = form;
		table = tbl;
		result = 0;
	}
	
	/**
	 * Constructor using just a formula
	 * 
	 * @param form The formula
	 */
	public Formula(String form) {
		this(form, null);
	}
	
	/**
	 * Default Constructor
	 */
	public Formula() {
		this("", null);
	}

	/**
	 * Get the result
	 *
	 * @return A double
	 */
	public double result() {
		return result;
	}

	/**
	 * Get the formula
	 * 
	 * @return A string
	 */
	public String formula() {
		return formula;
	}
	
	/**
	 * Helper to parse and push a number
	 *
	 * @param list List of numbers to push the number onto
	 * @param num A string representation of what we hope is a number
	 * @throws NumberFormatException
	 * @return An empty string to clear the num, unless there was a problem
	 */
	private String pushDouble(LinkedList<Double> list, String num) throws NumberFormatException {
		if (num != "")
			list.add(Double.parseDouble(num));
		return "";
	}

	/**
	 * Helper to parse and push a reference
	 *
	 * @param list List of numbers to push the evaluated reference onto
	 * @param ref A string representation of what we hope is a reference
	 * @throws NumberFormatException
	 * @throws Exception
	 * @return An empty string to clear the ref, unless there was a problem
	 */
	private String pushRef(LinkedList<Double> list, String ref) throws Exception, NumberFormatException {
		Cell select;
		if (debug) System.out.println("Try to eval reference: " + ref + "; ");
		if (ref != "") {
			select = table.selectCell(ref);
			if (select == null)
				throw new Exception("Cell " + ref + " could not be referenced!");
			else {
				list.add(select.getValue(true));
			}
		}
		return "";
	}
	
	/**
	 * Set the formula and parse it in one go
	 */
	public double evaluate(String form) throws Exception, NumberFormatException {
		formula = form;
		result = 0;
		return evaluate();
	}
	
	/**
	 * Detects an allowed character for a number
	 * 
	 * @param test The character to test
	 * @return True if the character is part of a number
	 */
	private boolean inNum(char test) {
		int val = (int) test;
		if (val >= ((int) '0') && val <= ((int) '9'))
			return true;
		if (test == '.')
			return true;
		else
			return false;
	}
	
	/**
	 * Determines allowed operations
	 * 
	 * @param test The character to test
	 * @return True if the character is an allowed operation
	 */
	private boolean isOp(char test) {
		switch (test) {
			case '+':
			case '-':
			case '*':
			case '/':
				return true;
			default:
				return false;
		}
	}
	
	/**
	 * Detects letters
	 * 
	 * @param test The character to test
	 * @return True if the character is part of a number
	 */
	private boolean isLetter(char test) {
		int val = (int) test;
		if (val >= ((int) 'A') && val <= ((int) 'Z'))
			return true;
		else if (val >= ((int) 'a') && val <= ((int) 'z'))
			return true;
		else
			return false;
	}	
	
	/**
	 * Detects a number boundary
	 * 
	 * @param test The character to test
	 * @return True if the character can be a number boundary
	 */
	private boolean isNumBound(char test) {
		if (isOp(test))
			return true;
		else if (test == ' ' || test == '(' || test == ')')
			return true;
		else
			return false;
	}
		
	/**
	 * Recursive parsing method
	 *
	 * @throws Exception
	 * @throws NumberFormatException
	 * @return The result of the operation
	 */
	public double evaluate() throws Exception, NumberFormatException {
		int pos, brackets = 0, startNested = 0, endNested = 0;
		char last = '+'; // Ensures leading minus signs will be properly parsed
		byte pass;
		String number = "", reference = "";
		LinkedList<Double> vals = new LinkedList<Double>();
		LinkedList<Character> ops = new LinkedList<Character>();
		
		if (debug) System.out.println("Evaluation of " + this.formula);
		
		for (char op : ops) {
			System.out.println(op);
		}

		// Do not evaluate anything when formula is empty
		if (formula == "")
			return 0.0;
		
		// Main loop through the formula
		for (pos=0; pos<formula.length(); pos++) {

			char current = formula.charAt(pos);
			if (debug) System.out.println("\tProcess " + current + " at " + pos);
			Formula nested;
			boolean supported = false; // used to detect an unsupported character

			// First handle detection of sub clauses
			switch (current) {

				// Open a bracket
				case '(':
					if (number != "") number = pushDouble(vals, number); 
					if (brackets++ == 0)
						startNested = pos+1;
					if (debug) System.out.println("\t\tOpen a bracket at position " + startNested);
					supported = true;
					break;

				// Close a bracket
				case ')':
					if (brackets-- == 1)
						endNested = pos;
					if (debug) System.out.println("\t\tClose a bracket at position " + endNested);
					supported = true;
					break;
			}
			
			// Now process equations at the current level
			if (brackets == 0 && current != ')') { 
								
				// First test if we have a value to push
				if (isNumBound(current)) {
					if (debug) System.out.println("\t\tTerm boundary detected");
					if (reference != "")
						reference = pushRef(vals, reference);
					else
						number = pushDouble(vals, number);
					supported = true;
				}
					
				// Now handle different character cases
				// First check for letters and treat as a reference
				if (isLetter(current)) 
					reference += current;
				
					
				// If it is a number it may belong to a reference or a number
				else if (inNum(current)) {
					if (debug) System.out.println("\t\tDigit or decimal point detected");
					if (reference != "")
						reference += current;
					else
						number += current;
				}
					
				// If it is a minus it may be an operation or the negative sign
				else if (current == '-' && isOp(last)) {
					if (debug) System.out.println("\t\tNegative number detected.");
					if (number == "")
						number += current;
					else
						throw new Exception("Invalid negative sign at position " + pos);
				}
				
				// If it is an operation do some validation
				else if (isOp(current)) {
					if (debug) System.out.println("\t\tOperation detected.");
					if (isOp(last))
						throw new Exception("Invalid operation at position " + pos);
					else
						ops.add(current);
				} 
				
				// If it is not already flagged as supported and none of the conditions
				//   in this block applied, throw an exception
				else if (supported != true)
					throw new Exception("Unsupported character at position " + pos + " : \"" + current + "\"");
			}

			// Check if we've reached the outside of a nested formula and parse it
			if (endNested != 0 && brackets == 0 && startNested != endNested) {
				nested = new Formula(formula.substring(startNested, endNested), table);
				vals.add(nested.evaluate());
				startNested = endNested = 0;
				if (debug) System.out.println("\tEvaluated clause " + nested);
			}
			
			last = (current != ' ') ? current : last;

		} // END main loop

		// Push any unparsed number onto the end of the vals
		if (number != "")
			number = pushDouble(vals, number);
		
		// Push any unparsed reference onto the end of the vals
		if (reference != "")
			reference = pushRef(vals, reference);

		// After the loop, check for error conditions
		if (brackets != 0)
			throw new Exception("Open sub-condition in " + formula);
		

		// Pass through the values once for each order of operations
		// each iteration will remove one of the operands and replace
		// the other with a calculated value, or move forward
		pass = 1;
		pos = 0;
		
		// Skip everything if there is one value
		if (vals.size() == 1) {
			result = vals.remove(0);
			if (debug) System.out.println("Evaluated " + this);
			return result;
		}

		// Check for an exception involving an incompatible number of ops and values
		if (vals.size()-1 != ops.size()) {
			throw new Exception("Unexpected values for operations and values during formula evaluation.");
		}
		
		// If we made it here we can iterate through ops and values
		while (pass < 3 && ops.size() > 0) {
			double opResult = 0;
			boolean calcDone = false;
			// Move to next pass if necessary
			if (pos >= ops.size()) {
				pass++;
				if (debug) System.out.println("\tSwitch to pass " + pass);
				pos = 0;
			}
			else {
				// Try to construct an expression
				if (debug) System.out.print("\t" + ops.size() + " ops left to do on pass " + pass + ", check position " + pos);
				char op = ops.get(pos);
				if (debug) System.out.println(" (operation is \"" + op + "\")");
				if (pass == 1 && (op == '*' || op == '/')) {
					ops.remove(pos);
					opResult = calc(vals.get(pos), op, vals.remove(pos+1));
					calcDone = true;
				}
				else if (pass == 2) {
					if (!(op == '+' || op == '-'))
						throw new Exception("Unsupported operation in final pass: " + op);
					ops.remove(pos);
					opResult = calc(vals.get(pos), op, vals.remove(pos+1));
					calcDone = true;
				}
				else { // Keep moving forward
					pos++;
				}
			}
			
			// Evaluate the expression if we can
			if (calcDone)
				vals.set(pos,opResult);
		}

		result = vals.peek();
		if (debug) System.out.println("Evaluated " + this);
		return result;
	}
	
	/**
	 * Calculate the results of a single operation
	 *
	 * @return True if the expression was evaluated
	 */
	private double calc(double left, char op, double right) {
		double result = 0;
		if (debug) System.out.print("Evaluating " + left + op + right + ": ");
		switch (op) {
			case '+':
				result = left + right;
				break;
			case '-':
				result = left - right;
				break;
			case '*':
				result = left * right;
				break;
			case '/':
				result = left / right;
				break;
		}
		return result;
	}
	
	/**
	 * Override toString
	 */
	public String toString() {
		return formula + " = " + result;
	}

	/**
	 * Command-line functionality
	 *
	 * Will evaluate any formula provided as the first argument
	 */
	public static void main(String args[]) {
		if (args.length > 0) {
			Formula form = new Formula(args[0]);
			try {
				form.evaluate();
				System.out.println(form);
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		else
			System.out.println("Call this with\n\tjava Formula \"<A formula enclosed by quotes>\"");
	}

}