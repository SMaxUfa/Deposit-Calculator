package myPackage;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ���������� �����������. ��������� ��������� ����������� �������� �� ������.
 * @author Max
 * @version 3.0
 */
public class DepositCalculator {
	
	public static void main(String[] args) {
		// ������� ����, ��� ����� ������������ ����� DepositCalculator:
		DepositCalculator calc = new DepositCalculator();
		//DepositCalculator calc = new DepositCalculator("1000", "1 ���", "15");
		//System.out.println(DepositCalculator.getProfit("5000", "5 ���", "7"));
	}
	
	// ���������.
	/** ������ �� ������� ���������� ��������. */ 
	public static final String[] PERIOD_NAMES = {"1 �����", "3 ������", "6 �������", "1 ���", "2 ����", "3 ����", "5 ���"};
	/** ������ � ����������� ��� � ������ ����������. */
	public static final float[] PERIOD_VALUES = {0.0833f, 0.25f, 0.5f, 1f, 2f, 3f, 5f};
	/** ��������� �� ������. ����� "��������� ��������� ������!" �������� �����. */
	public static final String ERROR_MESSAGE = "<html><span style='color:red'>��������� ��������� ������!</span></html>"; 
	
	// ��������� � �������������� ���������� ����.
	private JFrame frame = new JFrame("���������� �����������");
	private JPanel windowContent = new JPanel();
	private JLabel label1 = new JLabel("����� ������ (���.):");
	private JTextField field1 = new JTextField("0");
	private JLabel label2 = new JLabel("���� ����������:");
	private JComboBox<String> comboBox = new JComboBox<String>(PERIOD_NAMES);	
	private JLabel label3 = new JLabel("���������� ������ (%):");
	private JTextField field3 = new JTextField("10");
	private JButton batton = new JButton("��������� ���������");
	private JLabel results = new JLabel("", 0);
	
	/**
	 * ����������� ��� ����������. ������� �� ����� ���� � ������, ������������ �� ���������:
	 * ����� ������ (���.): 0
	 * ���� ����������: 1 �����
	 * ���������� ������ (%): 10 
	 */
	public DepositCalculator() {
		// ��������� ������ ��������� ��� �������� ����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ������ ������������ GridLayout ��� ����������� ������.
		// ��� ����� ������������� � ������� �������� 4 �� 2.
		// ���������� ����� �������� ������� - 5 �������� �� ����������� � 5 �� ���������.
		GridLayout gl = new GridLayout(4,2,5,5);
		windowContent.setLayout(gl);

		// ��������� ���������� �� ������.
		windowContent.add(label1);
		windowContent.add(field1);
		windowContent.add(label2);
		windowContent.add(comboBox);
		windowContent.add(label3);
		windowContent.add(field3);
		windowContent.add(batton);
		windowContent.add(results);	
		
		// ������� "���������". ��� ������ ���������� ������ Listener, ������� ����� "�������". 
		// ������� "������� �� ������" � �������� ����������.
		batton.addActionListener(new Listener());
				
		// ��������� ����� � ������.	
		frame.setContentPane(windowContent);
		
		// ����� ������ � ���������� �����.
		frame.setSize(450,150);
		frame.setVisible(true);
	}
	
	/**
	 * ����������� � �����������. ��������� ������ �������� ����� �� ���������.
	 * ���� ���������� ����� ���������� �� �������� � ������� PERIOD_NAMES, 
	 * �� ������� ���� ���������� �� ��������� - "1 �����". 
	 * @see PERIOD_NAMES 
	 */
	public DepositCalculator(String sum, String period, String percent) {
		// �������� ������ �����������, ������� ��� ����������.
		this();
		
		// ���������, ��� ����� ������ ���� � ��������� PERIOD_NAMES.		
		for (String el : PERIOD_NAMES) {
			if (el == period) {
				comboBox.setSelectedItem(period);
				break;
			}
		}		

		// ��������� ���� �� �����.
		field1.setText(sum);
		field3.setText(percent);		
	}
	
	/** ��������� ����� ��� ��������� ������� "������� �� ������". */
	private class Listener implements ActionListener {
		@Override
		// ����� ��������� ��������� ActionListener, ������� �� ������ ����������� ����� actionPerformed.
		// JVM ��������� ���� �����, ����� ������������ �������� �� ������.
		public void actionPerformed(ActionEvent e) {

			// �������� ������ c ����� ����� � ����� getProfit ��� ����������.
			String result = getProfit(field1.getText(), (String) comboBox.getSelectedItem(), field3.getText());
			
			// ������� �� ����� ���������� ����������.
			results.setText(result);
		}	
	}
	
	/**
	 * ����� ����������� ����������� �������� �� ������.
	 * ����� ��������� ��� ������, ������������ ������ � � ������� �����, ��������� ��������� �� �������.
	 * �������: ����������� �������� = ����� ������ * ���������� ��� * ���������� ������ / 100.	
	 * @return ������ � ����������� ����������� ���������. ���������� ����������� �� ���� ������ ����� �������.
	 * @return ���� ���������� ������ ������ �����������, �� ������������ ������ ERROR_MESSAGE.
	 * @see ERROR_MESSAGE
	 */
	public static String getProfit(String sum, String period, String percent) {
		float sumFloat;
		float periodFloat = 0;
		float percentFloat;		
			
		// ������� ������������� ������ sum � percent � float.
		// � ������ ������ ����� ���������� ������ ERROR_MESSAGE.
		try {
			sumFloat = Float.parseFloat(sum);
			percentFloat = Float.parseFloat(percent);			
		} catch(NumberFormatException e) {
			return ERROR_MESSAGE;
		}
		
		// ���������� ��������� ��� � ������� �� ��������� PERIOD_VALUES.
		for (int i = 0; i < PERIOD_NAMES.length; i++) {
			if (PERIOD_NAMES[i] == period) {
				periodFloat = PERIOD_VALUES[i];
				break;
			}
		}
		// ���������, ��� ����� ������ ���� � ��������� PERIOD_NAMES.
		if (periodFloat == 0f) {
			return ERROR_MESSAGE;
		}
			
		// ����������� �������� = ����� ������ * ���������� ��� * ���������� ������ / 100.
		// ������� ��������� �����, ���������� �� 100, � ������� Math.round(), � ����� ����� ����� �� 100.
		// ����� ������� �������� ���������� �� ���� ������ ���� �������. 
		float resultFloat = Math.round(sumFloat * periodFloat * percentFloat) / 100f;
				
		// ��������� ����������� � ������������ � ������.
		return String.format("%.2f", resultFloat);
	}
}
