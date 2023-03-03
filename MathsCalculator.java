import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MathsCalculator extends JFrame implements ActionListener{
    JLabel l1;
    JButton n0,n1,n2,n3,n4,n5,n6,n7,n8,n9;
    JButton add,sub,mul,div,mod,equal,dot,clear;

    private String operator="";
    private double result=0;
    private boolean isDecimalClicked= false;
    private boolean isOperatorClicked= false;
    /*isOperatorClicked and isDecimalClicked are boolean variables used to keep track of the state of the 
    calculator.
    1) isOperatorClicked is used to indicate whether an arithmetic operator has been clicked or not. 
    This is important because when the user clicks an operator button, we need to remember the previous operand 
    and operator to perform the calculation later on.
    2) isDecimalClicked is used to indicate whether the decimal point has already been clicked or not. This 
    is important because we need to prevent the user from entering multiple decimal points for the same number.*/
    
    MathsCalculator(){
        this.setVisible(true);
        this.setSize(500,300);
        this.setTitle("Calculator");
        this.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Initializing and setting the position of the components
        l1=new JLabel("0");
        l1.setBounds(20,20,400,30);
        l1.setFont(new Font("Arial",Font.BOLD,16));

        //First Row
        n7=new JButton("7");
        n7.setBounds(20,55,90,30);
        n8=new JButton("8");
        n8.setBounds(120,55,90,30);
        n9=new JButton("9");
        n9.setBounds(220,55,90,30);
        add=new JButton("+");
        add.setBounds(320,55,90,30);

        //Second Row
        n4=new JButton("4");
        n4.setBounds(20,90,90,30);
        n5=new JButton("5");
        n5.setBounds(120,90,90,30);
        n6=new JButton("6");
        n6.setBounds(220,90,90,30);
        sub=new JButton("-");
        sub.setBounds(320,90,90,30);
        
        //Third Row
        n1=new JButton("1");
        n1.setBounds(20,125,90,30);
        n2=new JButton("2");
        n2.setBounds(120,125,90,30);
        n3=new JButton("3");
        n3.setBounds(220,125,90,30);
        mul=new JButton("*");
        mul.setBounds(320,125,90,30);
        
        //Fourth Row
        dot=new JButton(".");
        dot.setBounds(20,160,90,30);
        n0=new JButton("0");
        n0.setBounds(120,160,90,30);
        equal=new JButton("=");
        equal.setBounds(220,160,90,30);
        div=new JButton("/");
        div.setBounds(320,160,90,30);

        //Fifth Row
        clear=new JButton("C");
        clear.setBounds(20,195,190,30);
        mod=new JButton("%");
        mod.setBounds(220,195,190,30);
        
        //Adding components to the frame
        this.add(l1);
        this.add(n7);
        this.add(n8);
        this.add(n9);
        this.add(add);
        this.add(n4);
        this.add(n5);
        this.add(n6);
        this.add(sub);
        this.add(n1);
        this.add(n2);
        this.add(n3);
        this.add(mul);
        this.add(dot);
        this.add(n0);
        this.add(equal);
        this.add(div);
        this.add(clear);
        this.add(mod);

        //Adding the ActionListener to all the buttons
        n0.addActionListener(this);
        n1.addActionListener(this);
        n2.addActionListener(this);
        n3.addActionListener(this);
        n4.addActionListener(this);
        n5.addActionListener(this);
        n6.addActionListener(this);
        n7.addActionListener(this);
        n8.addActionListener(this);
        n9.addActionListener(this);
        add.addActionListener(this);
        sub.addActionListener(this);
        mul.addActionListener(this);
        div.addActionListener(this);
        mod.addActionListener(this);
        dot.addActionListener(this);
        clear.addActionListener(this);
        equal.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        Object source= e.getSource();
        String text=l1.getText();
        // String command = e.getActionCommand();
        
        //For Number Buttons
        /*if(command.matches("[0-9]")){
            if(text.equals("0")|| isOperatorClicked){
                l1.setText(command);
            }
            else{
                l1.setText(command+text);
            }
            isOperatorClicked=false;
            return;
        }*/
        
        //For Clear Button- Setting everything to the default values
        if(source== clear){
            l1.setText("0");
            operator="";
            result=0;
            isOperatorClicked= false;
            isDecimalClicked= false;
        }
        
        //For Equal Button
        else if(source== equal){
            //Checking if any operator is selected
            if(!operator.isEmpty()){
                /*The operator and the two numbers specified by the user will be passed to the calculate 
                function and the result will be set in the label*/
                result=calculate(operator, result, Double.parseDouble(text)); 
                l1.setText(String.valueOf(result));
                operator="";
                isOperatorClicked=true;
                isDecimalClicked= false;
            }
        }
        
        //For Dot Button-It appends a dot(.) at the end of the current number for floating point numbers
        else if(source== dot){
            if(!isDecimalClicked){
                l1.setText(text+".");
                isDecimalClicked=true; 
            }
        }
        
        //For Operator Buttons-Sets the operator for the calculate function
        else if(source== add || source== sub || source== mul || source== div || source== mod){
            //If no operator is selected then the number which is selected will be stored in the result
            if(operator.isEmpty()){
                result=Double.parseDouble(text);
            }
            //If operator is selected then calculate function will be called and result will be set to the label
            else{
                result=calculate(operator, result, Double.parseDouble(text));
                l1.setText(String.valueOf(result));
            }
            operator = e.getActionCommand();
            isOperatorClicked = true;
            isDecimalClicked = false;
        }
        
        //If Nothing matches then the number buttons will be selected
        else{
            String command = e.getActionCommand();
            //If label is 0 or an operator is selected then it will set the label to the number button clicked
            //by the user
            if(text.equals("0")||isOperatorClicked){
                l1.setText(command);
            }
            //If any number is present in the label the other numbers or dot(.) will be appended after it
            else{
                l1.setText(text+command);
            }
            isOperatorClicked=false;
        }
    }
    //Function to perform calculations when operator and operands are passed and return the result
    private double calculate(String operator,double op1,double op2){
        switch(operator){
            case "+":
                return op1+op2;
            case "-":
                return op1-op2;
            case "*":
                return op1*op2;
            case "/":
                return op1/op2; 
            case "%":
                return op1%op2;
            default:
                return 0;
        }
    }
    public static void main(String[] args) {
        new MathsCalculator();
    }
}
