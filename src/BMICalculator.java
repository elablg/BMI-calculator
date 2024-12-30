import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BMICalculator extends JFrame implements ActionListener{
	
	
	private JTextField txtOne, txtTwo, txtResult, txtMessage;
	private JLabel lblOne, lblTwo, lblResult, lblOutcome;
	private JButton btnCalculate, btnReset;
	private Calculator cal;

	public BMICalculator() {
		// our constructor
		
		//calling the method for GUI items:
		gui();
		
		// initializing the calculator:
		cal = new Calculator();
		
		// Our frame parameters:
		setSize(600,800);
		setTitle("BMI Calculator");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void gui() {
		
		setLayout(new GridLayout(5,4));
		
		lblOne = new JLabel("Please enter weight:");
		lblTwo = new JLabel("Please enter height:");
		lblResult = new JLabel("Result:");
		lblOutcome = new JLabel("The outcome is:");
		
		txtOne = new JTextField();
		txtTwo = new JTextField();
		txtResult = new JTextField();
		txtResult.setEditable(false);
		txtMessage = new JTextField();
		txtMessage.setEditable(false);
		
		
		btnCalculate = new JButton("Calculate");
		btnReset = new JButton("Reset");
		
		
		// adding the items to the frame (sıra önemliydi)
		add(lblOne);
		add(txtOne);
		add(lblTwo);
		add(txtTwo);
		add(lblResult);
		add(txtResult);
		add(lblOutcome);
		add(txtMessage);
		
		// adding buttons to the panel
		JPanel pnlButtons = new JPanel();
		pnlButtons.add(btnCalculate);
		pnlButtons.add(btnReset);
		// adding panel to frame
		add(pnlButtons);
		
		// registering buttons to respond actions:
		btnCalculate.addActionListener(this);
		btnReset.addActionListener(this);
		
		
		
	}
	
	public static void main(String[] args) {
		new BMICalculator();
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnCalculate)) {
			calculate();
		}
		else if (e.getSource().equals(btnReset)) {
			reset();
		}
		else {
			exit();
		}
			
		
	}
	
	
	private void calculate() {
		
		double sum = 0;
		
		
		try {
			double weight = Integer.parseInt(txtOne.getText().trim());
			double height = Integer.parseInt(txtTwo.getText().trim());
		
			
			sum = cal.result(weight, height);
			txtResult.setText(String.valueOf(sum));
			
			if (sum < 18.5)
				txtMessage.setText("Underweight");
			else if (sum >= 18.5 && sum <= 24.9)
				txtMessage.setText("Healthy");
			else if(sum >= 25 && sum <= 29.9)
				txtMessage.setText("Overweight");
			else
				txtMessage.setText("Obese");
		}
		
		catch(Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	private void reset() {
		
		txtOne.setText("");
		txtTwo.setText("");
		txtResult.setText("");
		txtMessage.setText("");
		
		
	}
	
	private void exit() {
		
		int n = JOptionPane.showConfirmDialog(this, "Are you sure?", "Exit Page", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_NO_OPTION)
			System.exit(n);
	}
	
	
	

}
