package myPackage;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Депозитный калькулятор. Позволяет вычислить начисленные проценты по вкладу.
 * @author Max
 * @version 3.0
 */
public class DepositCalculator {
	
	public static void main(String[] args) {
		// Примеры того, как можно использовать класс DepositCalculator:
		DepositCalculator calc = new DepositCalculator();
		//DepositCalculator calc = new DepositCalculator("1000", "1 год", "15");
		//System.out.println(DepositCalculator.getProfit("5000", "5 лет", "7"));
	}
	
	// Константы.
	/** Массив со сроками размещения депозита. */ 
	public static final String[] PERIOD_NAMES = {"1 месяц", "3 месяца", "6 месяцев", "1 год", "2 года", "3 года", "5 лет"};
	/** Массив с количеством лет в сроках размещения. */
	public static final float[] PERIOD_VALUES = {0.0833f, 0.25f, 0.5f, 1f, 2f, 3f, 5f};
	/** Сообщение об ошибке. Текст "Проверьте введенные данные!" красного цвета. */
	public static final String ERROR_MESSAGE = "<html><span style='color:red'>Проверьте введенные данные!</span></html>"; 
	
	// Объявляем и инициализируем компоненты окна.
	private JFrame frame = new JFrame("Депозитный калькулятор");
	private JPanel windowContent = new JPanel();
	private JLabel label1 = new JLabel("Сумма вклада (руб.):");
	private JTextField field1 = new JTextField("0");
	private JLabel label2 = new JLabel("Срок размещения:");
	private JComboBox<String> comboBox = new JComboBox<String>(PERIOD_NAMES);	
	private JLabel label3 = new JLabel("Процентная ставка (%):");
	private JTextField field3 = new JTextField("10");
	private JButton batton = new JButton("Расчитать начиления");
	private JLabel results = new JLabel("", 0);
	
	/**
	 * Конструктор без аргументов. Выводит на экран окно с полями, заполненными по умолчанию:
	 * Сумма вклада (руб.): 0
	 * Срок размещения: 1 месяц
	 * Процентная ставка (%): 10 
	 */
	public DepositCalculator() {
		// Завершить работу программы при закрытии окна
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Задаем расположения GridLayout для компонентов панели.
		// Они будут располагаться в таблице размером 4 на 2.
		// Расстояние между ячейками таблицы - 5 пикселей по горизонтали и 5 по вертикали.
		GridLayout gl = new GridLayout(4,2,5,5);
		windowContent.setLayout(gl);

		// Добавляем компоненты на панель.
		windowContent.add(label1);
		windowContent.add(field1);
		windowContent.add(label2);
		windowContent.add(comboBox);
		windowContent.add(label3);
		windowContent.add(field3);
		windowContent.add(batton);
		windowContent.add(results);	
		
		// Создаем "слушатель". Это объект вложенного класса Listener, который будет "слушать". 
		// событие "Нажатие на кнопку" и вызывать вычисления.
		batton.addActionListener(new Listener());
				
		// Связываем фрейм и панель.	
		frame.setContentPane(windowContent);
		
		// Задаём размер и отображаем фрейм.
		frame.setSize(450,150);
		frame.setVisible(true);
	}
	
	/**
	 * Конструктор с аргументами. Позволяет задать значения полей по умолчанию.
	 * Если указанного срока размещения не окажется в массиве PERIOD_NAMES, 
	 * то берется срок размещения по умолчанию - "1 месяц". 
	 * @see PERIOD_NAMES 
	 */
	public DepositCalculator(String sum, String period, String percent) {
		// Вызываем другой конструктор, который без аргументов.
		this();
		
		// Проверяем, что такой период есть в константе PERIOD_NAMES.		
		for (String el : PERIOD_NAMES) {
			if (el == period) {
				comboBox.setSelectedItem(period);
				break;
			}
		}		

		// Заполняем поля на форме.
		field1.setText(sum);
		field3.setText(percent);		
	}
	
	/** Вложенный класс для обработки событий "Нажатие на кнопку". */
	private class Listener implements ActionListener {
		@Override
		// Класс реализует интерфейс ActionListener, поэтому мы должны реализовать метод actionPerformed.
		// JVM выполняет этот метод, когда пользователь нажимает на кнопку.
		public void actionPerformed(ActionEvent e) {

			// Передаем данные c полей формы в метод getProfit для вычислений.
			String result = getProfit(field1.getText(), (String) comboBox.getSelectedItem(), field3.getText());
			
			// Выводим на форму результаты вычислений.
			results.setText(result);
		}	
	}
	
	/**
	 * Метод расчитывает начисленные проценты по вкладу.
	 * Метод принимает три строки, конвертирует строки в в дробные числа, вычисляет результат по формуле.
	 * Формула: Начисленные проценты = Сумма вклада * Количество лет * Процентная ставка / 100.	
	 * @return Строка с количеством начисленных процентов. Количество округляется до двух знаком после запятой.
	 * @return Если переданные методу данные некорректны, то возвращается строка ERROR_MESSAGE.
	 * @see ERROR_MESSAGE
	 */
	public static String getProfit(String sum, String period, String percent) {
		float sumFloat;
		float periodFloat = 0;
		float percentFloat;		
			
		// Пробуем преобразовать строки sum и percent в float.
		// В случае ошибки метод возвращает строку ERROR_MESSAGE.
		try {
			sumFloat = Float.parseFloat(sum);
			percentFloat = Float.parseFloat(percent);			
		} catch(NumberFormatException e) {
			return ERROR_MESSAGE;
		}
		
		// Определяем количесто лет в периоде из константы PERIOD_VALUES.
		for (int i = 0; i < PERIOD_NAMES.length; i++) {
			if (PERIOD_NAMES[i] == period) {
				periodFloat = PERIOD_VALUES[i];
				break;
			}
		}
		// Проверяем, что такой период есть в константе PERIOD_NAMES.
		if (periodFloat == 0f) {
			return ERROR_MESSAGE;
		}
			
		// Начисленные проценты = Сумма вклада * Количество лет * Процентная ставка / 100.
		// Сначала округляем число, умноженное на 100, с помощью Math.round(), а потом снова делим на 100.
		// Таким образом получаем округление до двух знаков поле запятой. 
		float resultFloat = Math.round(sumFloat * periodFloat * percentFloat) / 100f;
				
		// Результат форматируем и конвертируем в строку.
		return String.format("%.2f", resultFloat);
	}
}
