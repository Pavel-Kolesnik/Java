package Kyrsovaia;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Anketa extends JFrame {
    private String text;
    public static int kol_members, n = 1, i = 0;
    public static ArrayList<Anketa> list1 = new ArrayList<>();
    public static ArrayList<Anketa> list2 = new ArrayList<>();
    private JButton button1 = new JButton("Выбрать вариант ответа");
    private JButton button2 = new JButton("Выбрать свой ответ");
    public static JLabel label;
    private JRadioButton radio1 = new JRadioButton("Да");
    private static JRadioButton radio2 = new JRadioButton("Нет");
    private static JRadioButton radio3 = new JRadioButton("Скорее да, чем нет");
    private static JRadioButton radio4 = new JRadioButton("Скорее нет, чем да");
    private static JTextField input = new JTextField(3);

    public Anketa(String text) {
        this.text = text;
    }

    public Anketa() {
        super("Student application form");
        this.setBounds(0, 0, 1000, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage("Logo.png");
        this.setIconImage(icon);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(9, 1, 2, 2));
        container.add(label);
        ButtonGroup group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        group.add(radio3);
        group.add(radio4);
        container.add(radio1);
        container.add(radio2);
        container.add(radio3);
        container.add(radio4);
        container.add(input);
        container.add(button1);
        container.add(button2);
        ButtonEventListener actionListener = new ButtonEventListener();
        button1.addActionListener(actionListener);
        button2.addActionListener(actionListener);
    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button1) {
                if (radio1.isSelected()) {
                    list2.add(new Anketa(n + ". " + radio1.getText()));
                }
                else if (radio2.isSelected()) {
                    list2.add(new Anketa(n + ". " + radio2.getText()));
                }
                else if (radio3.isSelected()) {
                    list2.add(new Anketa(n + ". " + radio3.getText()));
                }
                else if (radio4.isSelected()) {
                    list2.add(new Anketa(n + ". " + radio4.getText()));
                }
                if (!radio1.isSelected() && !radio2.isSelected() && !radio3.isSelected() && !radio4.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Выберите хотя бы один ответ", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    n++;
                    if (n == list1.size() + 1) setVisible(false);
                    if (i != list1.size() - 1) i++;
                    else {
                        PrintAnswers();
                    }
                    label.setText("Вопрос " + n + ". " + list1.get(i));
                }
            }
            if (e.getSource() == button2) {
                if (input.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Поле ввода пустое!", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    list2.add(new Anketa(n + ". " + input.getText()));
                    n++;
                    if (n == list1.size()+1) setVisible(false);
                    if (i != list1.size()-1) i++;
                    else {
                        PrintAnswers();
                    }
                    label.setText("Вопрос " + n + ". " + list1.get(i));
                }
            }
        }
    }

    @Override
    public String toString() {return text;}

    public static void PrintAnswers() {
        try (FileWriter writer = new FileWriter("Ответы " + "участника " + kol_members + ".txt")) {
            for (Anketa e : list2) {
                writer.write(e.toString() + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(list2);
        System.exit(0);
    }
}
