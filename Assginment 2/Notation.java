
public class Notation {
	public static NotationQueue<String> notationQ;
	public static NotationStack<String> notationS;
	
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException{
		notationQ = new NotationQueue<String>();
		notationS = new NotationStack<String>();
		
		for(int i = 0; i < infix.length(); i++) {
			char c = infix.charAt(i);
			
			if(c == ' ') {
				continue;
			}else if(Character.isDigit(c)){
				try {
					notationQ.enqueue(String.valueOf(c));
				}catch(QueueOverflowException e) {
					e.getMessage();
				}
			}else if(c == '(') {
				try {
					//notationS.push(String.valueOf(c));
					notationS.push(Character.toString(c));
				}catch(StackOverflowException e) {
					e.getMessage();
				}
			}else if(c == '*' || c == '/' || c == '+' || c == '-') {
				try {
					while(!notationS.isEmpty()) {
						if((notationS.top().charAt(0) == '*' || notationS.top().charAt(0) == '/') && (c == '+' || c == '-')) {
							notationQ.enqueue(String.valueOf(notationS.pop()));
						}else if((notationS.top().charAt(0) == '+' || notationS.top().charAt(0) == '-') && (c != '*' || c != '/')) {
							notationQ.enqueue(String.valueOf(notationS.pop()));
						}else {
							break;
						}
					}
				notationS.push(Character.toString(c));
				}catch(QueueOverflowException e) {
					e.getMessage();
				}catch(StackUnderflowException e) {
					e.getMessage();
				}catch(StackOverflowException e) {
					e.getMessage();
				}
			}else if(c == ')') {
				try {
					char temp = notationS.pop().charAt(0);
					while(temp!= '(') {
						notationQ.enqueue(String.valueOf(temp));
						if(notationS.isEmpty()) {
							throw new InvalidNotationFormatException("Invalid Format");
						}else {
							temp = notationS.pop().charAt(0);
						}
					}
				}catch(QueueOverflowException e) {
					e.getMessage();
				}catch(StackUnderflowException e) {
					e.getMessage();
				}
			}
		}
		return notationQ.toString();
	}
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException{
		notationS = new NotationStack<String>();
		char c = ' ';
		try {
			for(int i = 0; i < postfix.length(); i++) {
				c = postfix.charAt(i);
				if(c == ' ') {
					continue;
				}else if(Character.isDigit(c)) {
					notationS.push(Character.toString(c));
				}else if(c == '*' || c == '/' || c == '+' || c == '-') {
					String str = notationS.pop().toString();
					String x = notationS.pop().toString();
					String y = "(" + x + c + str + ")";
					notationS.push(y);
				}
			}
			return notationS.pop();
		}catch(StackOverflowException e) {
			e.getMessage();
		}catch(StackUnderflowException e) {
			e.getMessage();
		}
		if(notationS.size() != 1) {
			throw new InvalidNotationFormatException("Invalid Format");
		}
		return null;
	}
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException{
		notationS = new NotationStack<String>();
		char c;
		try {
			for(int i = 0; i < postfixExpr.length(); i++) {
				c = postfixExpr.charAt(i);
				if(c == ' ') {
					continue;
				}else if(Character.isDigit(c) || c == '(') {
					notationS.push(Character.toString(c));
				}else if(c == '*' || c == '/' || c == '+' || c == '-') {
					Double x = Double.valueOf(notationS.pop().toString());
					Double y = Double.valueOf(notationS.pop().toString());
					String str = "";
					if(c == '*') {
						str = Double.toString(y*x);
					}else if(c == '/') {
						str = Double.toString(y/x);
					}else if(c == '+') {
						str = Double.toString(y+x);
					}else if(c == '-') {
						str = Double.toString(y-x);
					}
					notationS.push(str);
				}
			}
			return Double.valueOf(notationS.pop());
		}catch(StackOverflowException e) {
			e.getMessage();
		}catch(StackUnderflowException e) {
			e.getMessage();
		}
		if(notationS.size() != 1) {
			throw new InvalidNotationFormatException("Invalid Format");
		}
		return 0.0;
	}
}
