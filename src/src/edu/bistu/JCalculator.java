package edu.bistu;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JCalculator extends JFrame implements ActionListener {

	private static final long serialVersionUID = -169068472193786457L;

	private class WindowCloser extends WindowAdapter {
		public void windowClosing(WindowEvent we) {
			System.exit(0);
		}
	}

	int i;
	// �������ֺͷ��ŵİ�ť
	private final String[] str = { "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", ".", "0", "=", "+" };
	JButton[] buttons = new JButton[str.length];
	// ��������İ�ť
	JButton reset = new JButton("CE");

	JTextField display = new JTextField("0");

	public JCalculator() {
		super("Calculator");
		// ��ӿ���
		JPanel p1 = new JPanel(new GridLayout(4, 4));

		for (i = 0; i < str.length; i++) {
			buttons[i] = new JButton(str[i]);
			p1.add(buttons[i]);
		}
		JPanel p2 = new JPanel(new BorderLayout());

		p2.add("Center", display);
		p2.add("East", reset);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add("North", p2);
		getContentPane().add("Center", p1);

		for (i = 0; i < str.length; i++)
			buttons[i].addActionListener(this);
		// ��ӡ�reset����ť
		reset.addActionListener(this);
		// ���"display"��ť
		display.addActionListener(this);

		addWindowListener(new WindowCloser());
		// ���ô��ڵĴ�С
		setSize(1000, 1000);

		setVisible(true);

		pack();
	}

	public void actionPerformed(ActionEvent e) {
		Object target = e.getSource();
		String label = e.getActionCommand();
		if (target == reset)
			handleReset();
		else if ("0123456789.".indexOf(label) > 0)
			handleNumber(label);
		else
			handleOperator(label);
	}

	// �жϵ�һ��������û�б�����ȥ
	boolean isFirstDigit = true;

	public void handleNumber(String key) {
		if (isFirstDigit)
			display.setText(key);
		else if ((key.equals(".")) && (display.getText().indexOf(".") < 0))
			display.setText(display.getText() + ".");
		else if (!key.equals("."))
			display.setText(display.getText() + key);
		isFirstDigit = false;
	}

	public void handleReset() {
		display.setText("0");
		isFirstDigit = true;
		operator = "=";
	}

	double number = 0.0;
	String operator = "=";

	public void handleOperator(String key) {
		if (operator.equals("+"))
			number += Double.valueOf(display.getText());
		else if (operator.equals("-"))
			number -= Double.valueOf(display.getText());
		else if (operator.equals("*"))
			number *= Double.valueOf(display.getText());
		else if (operator.equals("/"))
			number /= Double.valueOf(display.getText());
		else if (operator.equals("="))
			number = Double.valueOf(display.getText());
		display.setText(String.valueOf(number));
		operator = key;
		isFirstDigit = true;
	}

	public static void main(String[] args) {
		new JCalculator();
	}
}