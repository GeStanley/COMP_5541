package structure;

public class Grammar {
	
	public static class Formula{
		
		private String addition(String add){
			String[] components = add.split("p");
			Double c1 = Double.parseDouble(components[0]);
			Double c2 = Double.parseDouble(components[1]);
			
			return Double.toString(c1+c2);
		}
		
		private String substraction(String sub){
			String[] components = sub.split("m");
			Double c1 = Double.parseDouble(components[0]);
			Double c2 = Double.parseDouble(components[1]);
			
			return Double.toString(c1-c2);
		}
		
		private String multiplication(String mult){
			String[] components = mult.split("u");
			Double c1 = Double.parseDouble(components[0]);
			Double c2 = Double.parseDouble(components[1]);
			
			return Double.toString(c1*c2);
		}
		
		private String division(String div){
			String[] components = div.split("d");
			Double c1 = Double.parseDouble(components[0]);
			Double c2 = Double.parseDouble(components[1]);
			
			return Double.toString(c1/c2);
		}
		
		private String expression(String subString){
			if(subString.contains("p"))
				return addition(subString);
			else if (subString.contains("m"))
				return substraction(subString);
			else if (subString.contains("u"))
				return multiplication(subString);
			else if (subString.contains("d"))
				return division(subString);
			else
				return subString;
		}
		
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
		
		private String priorityRecursive(String input, char[] operations){
			int parenPairs=0;
			
			if(!input.contains(Character.toString(operations[0])) && !input.contains(Character.toString(operations[1])))
				return input;
					
			while(input.charAt(parenPairs)=='l' && input.charAt(input.length()-1-parenPairs)=='r'){
				parenPairs++;
			}
			
			input = input.substring(parenPairs,input.length()-parenPairs);
			
			if(input.contains("l")){
				
				int r=input.length()-1;
				int l=0;

				while(input.charAt(r)!='r')
					r--;

				while(input.charAt(l)!='l')
					l++;
				
				String newString = priorityRecursive(input.substring(l+1, r), operations);
				
				return input.substring(0,l+1) + newString + input.substring(r,input.length()); 
			}else{
				for(int i=0;i<input.length();i++){
					if(input.charAt(i)==operations[0] || input.charAt(i)==operations[1]){
						int leftIndex=i-1;
						int rightIndex=i+1;
						while(leftIndex>=0 && 
								(input.charAt(leftIndex)=='0'||
								input.charAt(leftIndex)=='1'||
								input.charAt(leftIndex)=='2'||
								input.charAt(leftIndex)=='3'||
								input.charAt(leftIndex)=='4'||
								input.charAt(leftIndex)=='5'||
								input.charAt(leftIndex)=='6'||
								input.charAt(leftIndex)=='7'||
								input.charAt(leftIndex)=='8'||
								input.charAt(leftIndex)=='9'||
								input.charAt(leftIndex)=='.'))
							leftIndex--;
						while(rightIndex<input.length() &&
								(input.charAt(rightIndex)=='0'||
								input.charAt(rightIndex)=='1'||
								input.charAt(rightIndex)=='2'||
								input.charAt(rightIndex)=='3'||
								input.charAt(rightIndex)=='4'||
								input.charAt(rightIndex)=='5'||
								input.charAt(rightIndex)=='6'||
								input.charAt(rightIndex)=='7'||
								input.charAt(rightIndex)=='8'||
								input.charAt(rightIndex)=='9'||
								input.charAt(rightIndex)=='.'))
							rightIndex++;
						
						String newString = input.replace(input.substring(leftIndex+1,rightIndex), expression(input.substring(leftIndex+1,rightIndex)));
						
						return priorityRecursive(newString,operations);
					}
				}
			}
			return input;

		}
		
		private String calculationPriority(String input){
			input = priorityRecursive(input, new char[]{'u','d'});
			input = priorityRecursive(input, new char[]{'p','m'});
			return input;
		}
		
		public String calculate(String input){
			int leftParen=0;
			int rightParen=0;
			
			if(input.charAt(0)!='l'||input.charAt(input.length()-1)!='r')
				input = "l" + input + "r";
			
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
					return "error";
			}
			
			if(leftParen-rightParen!=0)
				return "error";
			
			return parseString(calculationPriority(input));
		}
	}
	
	
	public static void main(String[] args){
		Grammar.Formula form = new Grammar.Formula();
		
		System.out.println(form.calculate("(5+1)*4"));
		System.out.println(form.calculate("(6/2)-1"));
		System.out.println(form.calculate("1.25+1.5"));
		System.out.println(form.calculate("(2.5)"));
		System.out.println(form.calculate("2*5*4*10"));
		System.out.println(form.calculate("2+5+4+10"));
		System.out.println(form.calculate("2+5*4+10"));
	}
}
