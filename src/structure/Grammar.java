package structure;

/**
 * Parser for cell formulas
 * 
 * @author GeStanley
 */
public class Grammar {

	/**
	 * Nested formula class
	 * 
	 * Different operators are replaced by character representations for convenience:
	 * 	p = +
	 * 	m = -
	 *  u = *
	 *  d = /
	 *  l = (
	 *  r = )
	 */
	public static class Formula{
	
		/**
		 * Helper for addition (p = +)
		 * 
		 * @param add A string representation of an addition operation
		 * @return A String representation of a double value that contains the result of the operation
		 */
		private String addition(String add){
			String[] components = add.split("p");
			Double c1 = Double.parseDouble(components[0]);
			Double c2 = Double.parseDouble(components[1]);
			
			return Double.toString(c1+c2);
		}

		/**
		 * Helper for subtraction (m = -)
		 * 
		 * @param sub A string representation of a subtraction operation
		 * @return A String representation of a double value that contains the result of the operation
		 */
		private String subtraction(String sub){
			String[] components = sub.split("m");
			Double c1 = Double.parseDouble(components[0]);
			Double c2 = Double.parseDouble(components[1]);
			
			return Double.toString(c1-c2);
		}

		/**
		 * Helper for multiplication (u = *)
		 * 
		 * @param mult A string representation of a multiplication operation
		 * @return A String representation of a double value that contains the result of the operation
		 */	
		private String multiplication(String mult){
			String[] components = mult.split("u");
			Double c1 = Double.parseDouble(components[0]);
			Double c2 = Double.parseDouble(components[1]);
			
			return Double.toString(c1*c2);
		}

		/**
		 * Helper for division (d = /)
		 * 
		 * @param div A string representation of a division operation
		 * @return A String representation of a double value that contains the result of the operation
		 */		
		private String division(String div){
			String[] components = div.split("d");
			Double c1 = Double.parseDouble(components[0]);
			Double c2 = Double.parseDouble(components[1]);
			
			return Double.toString(c1/c2);
		}
		
		/**
		 * Helper to classify a string as a particular operation
		 * 
		 * @param subString A string representation of an operation. Must contain two numbers and an operation character
		 * @return A String representation of a double
		 */		
		private String calculate(String subString){
			if(subString.contains("p"))
				return addition(subString);
			else if (subString.contains("m"))
				return subtraction(subString);
			else if (subString.contains("u"))
				return multiplication(subString);
			else if (subString.contains("d"))
				return division(subString);
			else
				return subString;
		}
		
		/**
		 * This method determines the operation priority in a mathematical expression (no parenthesis). It performs
		 * multiplications and devisions before addition and subtraction.
		 * 
		 * @param expression A string representation of an expression.
		 * @return A String representation of a double
		 */	
		private String expression(String expression){
			
			String[] operations = expression.split("p|m");
			
			for(int i=0;i<operations.length;i++)
				if(operations[i].contains("u")||operations[i].contains("d"))
					while(operations[i].contains("u")||operations[i].contains("d")){
						int opCount=0;
						int index=0;
						
						while(opCount<2 && index<operations[i].length()){
							if(operations[i].charAt(index)=='u'|| operations[i].charAt(index)=='d')
								opCount++;
								
							index++;
						}
						
						if(opCount==1)
							index=operations[i].length()+1;
						
						String result=calculate(operations[i].substring(0,index-1));
								
						expression=expression.replace(operations[i].substring(0,index-1),result);
						operations[i]=operations[i].replace(operations[i].substring(0,index-1),result);
							
					}
			
			
			
			while(expression.contains("p")||expression.contains("m")){
				int opCount=0;
				int index=0;
				
				while(opCount<2 && index<expression.length()){
					if(expression.charAt(index)=='p'|| expression.charAt(index)=='m')
						opCount++;
						
					index++;
				}
				
				if(opCount==1)
					index=expression.length()+1;
				
				expression=expression.replace(expression.substring(0,index-1),calculate(expression.substring(0,index-1)));
					
			}
			
			return expression;
		}
		
		/**
		 * This method recursively extracts a mathematical expression from a mathematical formula. In other words it determines
		 * operation priority of parenthesized expressions within a mathematical formula.
		 * 
		 * @param input A string representation of a mathematical formula.
		 * @return A String representation of a double
		 */	
		private String parseString(String input){
			
			if(!input.contains("l"))
				return expression(input);
			else{
				
				int rightBracket = input.indexOf("r");
				int leftBracket = input.lastIndexOf("l", rightBracket);
				String subString = input.substring(leftBracket+1, rightBracket);
				
				String calculation = expression(subString);
				
				String newString = input.replaceFirst("l" + subString + "r", calculation);

				return parseString(newString);
			}
		}	
		
		
		/**
		 * This method determine if the user input was valid. It also perform certain modifications to correct user input as 
		 * well as facilitate and simplify code: it replace mathematical operations by chars:
		 * 	p = +
		 * 	m = -
		 *  u = *
		 *  d = /
		 *  l = (
		 *  r = )
		 *  
		 *  It also ensures that all chars in the string are valid and interpretable.
		 * 
		 * @param input A string representation of a mathematical formula, as input by the user.
		 * @return A double
		 * @throws Exception
		 */	
		public double formula(String input) throws Exception {
			int leftParen=0;
			int rightParen=0;
			double val = 0;
			
			if(input.charAt(0)!='l'||input.charAt(input.length()-1)!='r')
				input = "l" + input + "r";
			
			input = input.replace(" ", "");
			input = input.replace('+', 'p');
			input = input.replace('-', 'm');
			input = input.replace('*', 'u');
			input = input.replace('/', 'd');
			input = input.replace('(', 'l');
			input = input.replace(')', 'r');
			
			for(int i=0;i<input.length();i++){
				if(input.charAt(i)=='l')
					leftParen++;
				if(input.charAt(i)=='r')
					rightParen++;
				if(input.charAt(i)!='0'&&
						input.charAt(i)!='1'&&
						input.charAt(i)!='2'&&
						input.charAt(i)!='3'&&
						input.charAt(i)!='4'&&
						input.charAt(i)!='5'&&
						input.charAt(i)!='6'&&
						input.charAt(i)!='7'&&
						input.charAt(i)!='8'&&
						input.charAt(i)!='9'&&
						input.charAt(i)!='.'&&
						input.charAt(i)!='p'&&
						input.charAt(i)!='m'&&
						input.charAt(i)!='u'&&
						input.charAt(i)!='d'&&
						input.charAt(i)!='l'&&
						input.charAt(i)!='r')
					throw new Exception("Invalid formula");
			}
			
			input = input.replace("mm", "m-");
			input = input.replace("pm", "p-");
			input = input.replace("dm", "d-");
			input = input.replace("um", "u-");
			input = input.replace("lm", "l-");
			
			if(leftParen-rightParen!=0)
				throw new Exception("Invalid formula");
			
			val = Double.parseDouble(parseString(input));
			return val;
		}
	}

	
	public static void main(String[] args){
		Grammar.Formula form = new Grammar.Formula();
		
		try {
			System.out.println(form.formula("(-5+1)*4"));
			System.out.println(form.formula("(6/2)-1"));
			System.out.println(form.formula("1.25+1.5"));
			System.out.println(form.formula("(2.5)"));
			System.out.println(form.formula("2*5*4*10"));
			System.out.println(form.formula("2+5+4+10"));
			System.out.println(form.formula("2+5*4+10"));
			System.out.println(form.formula("(2+5)*4+10"));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
