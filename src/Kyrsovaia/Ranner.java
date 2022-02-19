package Kyrsovaia;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ranner extends Anketa {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(new FileInputStream("Вопросы.txt"), "windows-1251")) {
            while (in.hasNextLine()) {
                String question = in.nextLine();
                list1.add(new Anketa(question));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (kol_members > 50) kol_members = 0;
        try (Scanner in = new Scanner(new FileInputStream("Количество опрошенных.txt"))) {
            while (in.hasNextInt()) {
                kol_members = in.nextInt() + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (kol_members == 0) kol_members++;
        try (FileWriter writer = new FileWriter("Количество опрошенных.txt")) {
                writer.write(String.valueOf(kol_members));
        } catch (IOException e) {
            e.printStackTrace();
        }
        label = new JLabel("Вопрос " + n + ". " + list1.get(0));
        Anketa form = new Anketa();
        form.setVisible(true);
    }
}
