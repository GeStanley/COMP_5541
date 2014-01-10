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
		
		private String calculate(String subString){
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
		
		public String formula(String input){
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
			
			return parseString(input);
		}
	}

	
	public static void main(String[] args){
		Grammar.Formula form = new Grammar.Formula();

		System.out.println(form.formula("(5+1)*4"));
		System.out.println(form.formula("(6/2)-1"));
		System.out.println(form.formula("1.25+1.5"));
		System.out.println(form.formula("(2.5)"));
		System.out.println(form.formula("2*5*4*10"));
		System.out.println(form.formula("2+5+4+10"));
		System.out.println(form.formula("2+5*4+10"));
		System.out.println(form.formula("(2+5)*4+10"));
	}
}
