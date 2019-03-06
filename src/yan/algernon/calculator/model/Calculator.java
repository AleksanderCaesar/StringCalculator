
package yan.algernon.calculator.model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


public class Calculator {
    private String strFromField;
    private Double answer;
    

    public Double getAnswer() {
        return answer;
    }

    public void setAnswer(Double answer) {
        this.answer = answer;
    }

    public String getStrFromField() {
        return strFromField;
    }

    public void setStrFromField(String strFromField) {
        this.strFromField = strFromField;
    }
    

    
    public String makeReversePolishNotation(String str) throws Exception {        
        StringBuilder sbStack = new StringBuilder(""), sbOut = new StringBuilder("");
        char charFromString, charTemp;

        for (int i = 0; i < str.length(); i++) {
            charFromString = str.charAt(i);
            if (isOp(charFromString)) {
                while (sbStack.length() > 0) {
                    charTemp = sbStack.substring(sbStack.length()-1).charAt(0);
                    if (isOp(charTemp) && (opPrior(charFromString) <= opPrior(charTemp))) {
                        sbOut.append(" ").append(charTemp).append(" ");
                        sbStack.setLength(sbStack.length()-1);
                    } else {
                        sbOut.append(" ");
                        break;
                    }
                }
                sbOut.append(" ");
                sbStack.append(charFromString);
            } else if ('(' == charFromString) {
                sbStack.append(charFromString);
            } else if (')' == charFromString) {
                charTemp = sbStack.substring(sbStack.length()-1).charAt(0);
                while ('(' != charTemp) {
                    if (sbStack.length() < 1) {
                        throw new Exception("Проверьте правильность формулы");
                    }
                    sbOut.append(" ").append(charTemp);
                    sbStack.setLength(sbStack.length()-1);
                    charTemp = sbStack.substring(sbStack.length()-1).charAt(0);
                }
                sbStack.setLength(sbStack.length()-1);
            } else {
                
                sbOut.append(charFromString);
            }
        }
       
        while (sbStack.length() > 0) {
            sbOut.append(" ").append(sbStack.substring(sbStack.length()-1));
            sbStack.setLength(sbStack.length()-1);
        }

        return  sbOut.toString();
    }

    
    private static boolean isOp(char c) {
        switch (c) {
            case '-':
            case '+':
            case '*':
            case '/':
                return true;
        }
        return false;
    }

    
    private static byte opPrior(char op) {
        switch (op) {            
            case '*':
            case '/':            
                return 2;
        }
        return 1; 
    }

    public double calculate(String str) throws Exception {
        double dA = 0, dB = 0;
        String sTmp;
        Deque<Double> stack = new ArrayDeque<Double>();
        StringTokenizer st = new StringTokenizer(str);
        while(st.hasMoreTokens()) {
            try {
                sTmp = st.nextToken().trim();
                if (1 == sTmp.length() && isOp(sTmp.charAt(0))) {
                    if (stack.size() < 2) {
                        throw new Exception("Неверное количество данных в стеке для операции " + sTmp);
                    }
                    dB = stack.pop();
                    dA = stack.pop();
                    switch (sTmp.charAt(0)) {
                        case '+':
                            dA += dB;
                            break;
                        case '-':
                            dA -= dB;
                            break;
                        case '/':
                            dA /= dB;
                            break;
                        case '*':
                            dA *= dB;
                            break;                        
                        default:
                            throw new Exception("Недопустимая операция " + sTmp);
                    }
                    stack.push(dA);
                } else {
                    dA = Double.parseDouble(sTmp);
                    stack.push(dA);
                }
            } catch (Exception e) {
                throw new Exception("Недопустимый символ в выражении");
            }
        }

        if (stack.size() > 1) {
            throw new Exception("Количество операторов не соответствует количеству операндов");
        }

        return stack.pop();
    }
    
}
