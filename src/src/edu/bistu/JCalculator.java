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
	// 创建数字和符号的按钮
	private final String[] str = { "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", ".", "0", "=", "+" };
	JButton[] buttons = new JButton[str.length];
	// 创建清除的按钮
	JButton reset = new JButton("CE");

	JTextField display = new JTextField("0");

	public JCalculator() {
		super("Calculator");
		// 添加框体
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
		// 添加“reset”按钮
		reset.addActionListener(this);
		// 添加"display"按钮
		display.addActionListener(this);

		addWindowListener(new WindowCloser());
		// 设置窗口的大小
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

	// 判断第一个数字有没有被按下去
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