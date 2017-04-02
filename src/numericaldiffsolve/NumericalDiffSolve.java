package numericaldiffsolve;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

public class NumericalDiffSolve {
    //Operator Table
    
    /**
     * @param args
     * String independent variable
     * String dependent variable
     * Double step size
     * Double[] [initial value,initial value,...]
     * Double initial dependent value
     * Double final dependent value
     * Boolean assignment problem?
     * String expression
     */
    public static void main(String[] args) {
        if(args.length==8) {
            String indVar = args[0];
            System.out.println("Independent variable: " + indVar);
            String depVar = args[1];
            System.out.println("Dependept variable: " + depVar);
            Double step = Double.parseDouble(args[2]);
            System.out.println("Step Size: " + step);
            ArrayList<Double> indInitials = new ArrayList<Double>();
            if(args[3].charAt(0)=='[') {
                int i = 1, j;
                j = i;
                while(i < args[3].length()) {
                    while(i < args[3].length() && args[3].charAt(i)!=',' && args[3].charAt(i)!=']') {
                        i++;
                        if(args[3].charAt(i)==']') {
                            indInitials.add(Double.parseDouble(args[3].substring(j,i)));
                            ++i;
                            break;
                        } else if (args[3].charAt(i) == ',') {
                            indInitials.add(Double.parseDouble(args[3].substring(j,i)));
                            j = ++i;
                        }
                    }
                }
            } else
                return;
            for(int i = 0; i < indInitials.size(); i++) {
                System.out.println("#" + (i+1) + " Y initial value: " + 
                        indInitials.get(i));
            }
            Double depInitial = Double.parseDouble(args[4]);
            System.out.println("Initial x value: " + depInitial);
            Double depFinal = Double.parseDouble(args[5]);
            System.out.println("Final x value: " + depFinal);
            Boolean assignProb = Boolean.parseBoolean(args[6]);
            System.out.println("Assignment Problem? " + assignProb);
            ArrayList<String> expression = new ArrayList<String>();
            try {
                for(int i = 7; i < args.length; i++) {
                    expression.add(infixToPostfix(args[i], indVar, depVar));
                    System.out.println("Expression " + i + ": " + expression.get(i-7));
                }
            } catch(SyntaxError ex) {
                
            }
                    
            eulerEstimate(indVar, depVar, step, indInitials,
                    depInitial, depFinal, expression, assignProb);
            heunEstimate(indVar, depVar, step, indInitials, 
                    depInitial, depFinal, expression, assignProb);
            rkEstimate(indVar, depVar, step, indInitials,
                    depInitial, depFinal, expression, assignProb);
        }
    }

    private static void eulerEstimate(String indVar, String depVar, 
            Double step, ArrayList<Double> indInitials, Double depInitial, 
            Double depFinal, ArrayList<String> expression, Boolean assignProb) {
        //Initialize Table and Storage
        Double depCurrent = depInitial;
        Double indCurrent = indInitials.get(0);
        while(depCurrent<=depFinal) {
            indCurrent = indCurrent + step * evaluateExpression(indVar, depVar, 
                    indCurrent, depCurrent, expression);
            depCurrent += step;
            //Store
        }
        //Plot
    }

    private static void heunEstimate(String indVar, String depVar, 
            Double step, ArrayList<Double> indInitials, Double depInitial, 
            Double depFinal, ArrayList<String> expression, Boolean assignProb) {
        //Initialize Table and Storage
        Double depCurrent = depInitial;
        Double indCurrent = indInitials.get(0);
        while(depCurrent<=depFinal) {
            indCurrent = indCurrent + step * evaluateExpression(indVar, depVar, 
                    indCurrent, depCurrent, expression);
            depCurrent += step;
            //Store
        }
        //Plot
    }

    private static void rkEstimate(String indVar, String depVar, 
            Double step, ArrayList<Double> indInitials, Double depInitial, 
            Double depFinal, ArrayList<String> expression,  Boolean assignProb) {
        //throw new UnsupportedOperationException("Not yet implemented");
    }
    private static Double evaluateExpression(String indVar, String depVar, 
            Double oldIndValue, Double oldDepValue, ArrayList<String> expression) {
        Stack<String> operators = new Stack<String>();
        Hashtable<String,Double> variables = new Hashtable<String,Double>();
        //Order of operations
            //( )
            //
        return 0.0;
    }
    
    private static String infixToPostfix(String infix, String indVar, String depVar) throws SyntaxError {
        StringBuilder postfix = new StringBuilder();
        Stack<String> operators = new Stack<String>();
        try {
            String nextToken;
            Scanner scan = new Scanner(infix);
            Pattern allowedOps = Pattern.compile("( ) = ' ^ * / % + - ! "
                    + "atanh asinh acosh e ln log | sinc cosc acoth"
                    + "asech acsch " + indVar + " " + depVar);
            System.out.println(allowedOps);
                
            
        } catch (Exception ex) {
            
        }
        return null;
    }
    
    private static void plotTable(ArrayList<Double> indValues, ArrayList<Double> depValues) {
        
    }

    private static class SyntaxError extends Exception {

        public SyntaxError() {
        }
    }
}
