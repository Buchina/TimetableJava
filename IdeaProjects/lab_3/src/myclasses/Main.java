package myclasses;

import myexceptions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String args[]) throws IOException {
        System.out.println("Доброго времени суток!");
        while (true) {
            System.out.println("\n1 - добавление элемента расписания\n" +
                    "2 - удаление элемента\n" +
                    "3 - измение элемента\n" +
                    "4 - вывод на экран расписания на заданный день недели\n" +
                    "5 - вывод на экран расписания для заданной группы\n" +
                    "6 - вывод на экран всего расписания, отсортированного по " +
                    "дням недели и номеру пары\n" +
                    "7 - выход из программы\n");

            BufferedReader in = new BufferedReader(new FileReader("src/resources/Timetable.txt"));
            ArrayList<String> weekdayList = new ArrayList<String>();
            Utils utils = new Utils();
            Collections.addAll(weekdayList, "ПОНЕДЕЛЬНИК", "ВТОРНИК", "СРЕДА", "ЧЕТВЕРГ", "ПЯТНИЦА", "СУББОТА");
            String weekday;
            int numOfPair;
            String nameOfLesson;
            int numOfClass;
            String group;
            ArrayList<Timetable> list = new ArrayList<Timetable>();
            String buf = in.readLine();
            if (buf == null) return;
            while (buf != null) {
                String[] array = buf.trim().split(" +");
                weekday = array[0];
                numOfPair = Integer.parseInt(array[1]);
                nameOfLesson = array[2];
                numOfClass = Integer.parseInt(array[3]);
                group = array[4];
                Timetable timetable = new Timetable(weekday, numOfPair, nameOfLesson, numOfClass, group);
                list.add(timetable);
                buf = in.readLine();
            }
            in.close();

            System.out.print("Введите выбранную цифру> ");
            Scanner scanner = new Scanner(System.in);
            try {
                int num = scanner.nextInt();
                if (num < 1 || num > 7) throw new BadAnswerException();

                if (num == 1) {
                    String weekdayNew;
                    int numOfPairNew;
                    String nameOfLessonNew;
                    int numOfClassNew;
                    String groupNew;
                    System.out.println("Введите день недели: ");
                    weekdayNew = scanner.next().trim().toUpperCase();
                    boolean correctWeekday = weekdayList.stream().anyMatch((d) -> d.equals(weekdayNew));
                    if (!correctWeekday) throw new BadWeekdayException();
                    System.out.println("Введите номер пары: ");
                    numOfPairNew = scanner.nextInt();
                    if (numOfPairNew <= 0 || numOfPairNew >= 8) throw new BadNumOfPairException();
                    System.out.println("Введите название пары: ");
                    nameOfLessonNew = scanner.next().trim();
                    System.out.println("Введите номер аудитории: ");
                    numOfClassNew = scanner.nextInt();
                    if (numOfClassNew <= 0) throw new BadNumOfClassException();
                    System.out.println("Введите название группы студентов: ");
                    groupNew = scanner.next().trim();
                    boolean notCorrect = list.stream().anyMatch((t1) -> ((t1.weekday.equals(weekdayNew) && t1.group.equals(groupNew) && t1.numOfPair == numOfPairNew)
                            || (t1.weekday.equals(weekdayNew) && t1.numOfPair == numOfPairNew && t1.numOfClass == numOfClassNew)));
                    if (!notCorrect) {
                        list.add(new Timetable(weekdayNew, numOfPairNew, nameOfLessonNew, numOfClassNew, groupNew));
                        utils.rewriteFile(list);
                    } else {
                        System.out.println("К сожалению, добавить невозможно...\n" +
                                "Хотите вернуться в меню? 1 - да, 0 - нет");
                        int answer = scanner.nextInt();
                        if (answer != 0 && answer != 1) throw new BadMenuAnswerException();
                        if (answer == 0) {
                            System.out.println("До свидания!");
                            return;
                        }
                    }
                }
                if (num == 2) {
                    System.out.println("Введите данные удаляемой записи.");
                    String weekdayNew;
                    int numOfPairNew;
                    String nameOfLessonNew;
                    int numOfClassNew;
                    String groupNew;
                    System.out.println("День недели: ");
                    weekdayNew = scanner.next().trim().toUpperCase();
                    boolean correctWeekday = weekdayList.stream().anyMatch((d) -> d.equals(weekdayNew));
                    if (!correctWeekday) throw new BadWeekdayException();
                    System.out.println("Номер пары: ");
                    numOfPairNew = scanner.nextInt();
                    if (numOfPairNew <= 0 || numOfPairNew >= 8) throw new BadNumOfPairException();
                    System.out.println("Название пары: ");
                    nameOfLessonNew = scanner.next().trim();
                    System.out.println("Номер аудитории: ");
                    numOfClassNew = scanner.nextInt();
                    if (numOfClassNew <= 0) throw new BadNumOfClassException();
                    System.out.println("Название группы студентов: ");
                    groupNew = scanner.next().trim();
                    Optional<Timetable> correct = list.stream().filter((t1) -> ((t1.weekday.equals(weekdayNew) && t1.nameOfLesson.equals(nameOfLessonNew)
                            && t1.numOfClass == numOfClassNew && t1.group.equals(groupNew) && t1.numOfPair == numOfPairNew))).findFirst();
                    if (correct.isPresent()) {
                        list.remove(correct.get());
                        utils.rewriteFile(list);
                        System.out.println("Удаление прошло успешно!");
                    } else System.out.println("Такого элемента нет...");
                    System.out.println("Хотите вернуться в меню? 1 - да, 0 - нет");
                    int answer = scanner.nextInt();
                    if (answer != 0 && answer != 1) throw new BadMenuAnswerException();
                    if (answer == 0) {
                        System.out.println("До свидания!");
                        return;
                    }
                }
                if (num == 3) {
                    System.out.println("Введите данные обновляемой записи.");
                    String weekdayNew;
                    int numOfPairNew;
                    String nameOfLessonNew;
                    int numOfClassNew;
                    String groupNew;
                    System.out.println("День недели: ");
                    weekdayNew = scanner.next().trim().toUpperCase();
                    boolean correctWeekday = weekdayList.stream().anyMatch((d) -> d.equals(weekdayNew));
                    if (!correctWeekday) throw new BadWeekdayException();
                    System.out.println("Номер пары: ");
                    numOfPairNew = scanner.nextInt();
                    if (numOfPairNew <= 0 || numOfPairNew >= 8) throw new BadNumOfPairException();
                    System.out.println("Название пары: ");
                    nameOfLessonNew = scanner.next().trim();
                    System.out.println("Номер аудитории: ");
                    numOfClassNew = scanner.nextInt();
                    if (numOfClassNew <= 0) throw new BadNumOfClassException();
                    System.out.println("Название группы студентов: ");
                    groupNew = scanner.next().trim();
                    Optional<Timetable> correct = list.stream().filter((t1) -> ((t1.weekday.equals(weekdayNew) && t1.nameOfLesson.equals(nameOfLessonNew)
                            && t1.numOfClass == numOfClassNew && t1.group.equals(groupNew) && t1.numOfPair == numOfPairNew))).findFirst();
                    if (correct.isPresent()) {
                        System.out.println("Введите новые данные:");
                        String weekdayNewUpdate;
                        int numOfPairNewUpdate;
                        String nameOfLessonNewUpdate;
                        int numOfClassNewUpdate;
                        String groupNewUpdate;
                        System.out.println("День недели: ");
                        weekdayNewUpdate = scanner.next().trim().toUpperCase();
                        boolean correctWeekdayUpdate = weekdayList.stream().anyMatch((d) -> d.equals(weekdayNewUpdate));
                        if (!correctWeekdayUpdate) throw new BadWeekdayException();
                        System.out.println("Номер пары: ");
                        numOfPairNewUpdate = scanner.nextInt();
                        if (numOfPairNewUpdate <= 0 || numOfPairNewUpdate >= 8) throw new BadNumOfPairException();
                        System.out.println("Название пары: ");
                        nameOfLessonNewUpdate = scanner.next().trim();
                        System.out.println("Номер аудитории: ");
                        numOfClassNewUpdate = scanner.nextInt();
                        if (numOfClassNewUpdate <= 0) throw new BadNumOfClassException();
                        System.out.println("Название группы студентов: ");
                        groupNewUpdate = scanner.next().trim();
                        correct.get().setWeekday(weekdayNewUpdate);
                        correct.get().setNumOfPair(numOfPairNewUpdate);
                        correct.get().setNameOfLesson(nameOfLessonNewUpdate);
                        correct.get().setNumOfClass(numOfClassNewUpdate);
                        correct.get().setGroup(groupNewUpdate);
                        utils.rewriteFile(list);
                        System.out.println("Данные успешно обновлены!");
                    } else System.out.println("Такого элемента нет...");
                    System.out.println("Хотите вернуться в меню? 1 - да, 0 - нет");
                    int answer = scanner.nextInt();
                    if (answer != 0 && answer != 1) throw new BadMenuAnswerException();
                    if (answer == 0) {
                        System.out.println("До свидания!");
                        return;
                    }

                }
                if (num == 4) {
                    System.out.println("Введите день недели: ");
                    String weekdayNew = scanner.next().trim().toUpperCase();
                    boolean correctWeekday = weekdayList.stream().anyMatch((d) -> d.equals(weekdayNew));
                    if (!correctWeekday) throw new BadWeekdayException();
                    System.out.println(list.stream().filter(w -> w.weekday.equals(weekdayNew)).collect(Collectors.toList()).toString());
                    System.out.println("Хотите вернуться в меню? 1 - да, 0 - нет");
                    int answer = scanner.nextInt();
                    if (answer != 0 && answer != 1) throw new BadMenuAnswerException();
                    if (answer == 0) {
                        System.out.println("До свидания!");
                        return;
                    }
                }
                if (num == 5) {
                    System.out.println("Введите название группы: ");
                    String groupNew = scanner.next();
                    System.out.println(list.stream().filter(w -> w.group.equals(groupNew)).collect(Collectors.toList()).toString());
                    System.out.println("Хотите вернуться в меню? 1 - да, 0 - нет");
                    int answer = scanner.nextInt();
                    if (answer != 0 && answer != 1) throw new BadMenuAnswerException();
                    if (answer == 0) {
                        System.out.println("До свидания!");
                        return;
                    }
                }
                if (num == 6) {
                    System.out.println(list.stream().sorted((i1, i2) -> {
                        int compareWeekdays = i2.weekdayInInt(i2.weekday).compareTo(i1.weekdayInInt(i1.weekday));
                        return compareWeekdays != 0 ? compareWeekdays : i1.numOfPair.compareTo(i2.numOfPair);
                    }).collect(Collectors.toList()).toString());
                }
                if (num == 7) {
                    System.out.println("До свидания!");
                    return;
                }

            } catch (BadAnswerException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Но ведь это даже не цифра...Попробуйте еще раз.");
            } catch (BadMenuAnswerException e) {
                System.out.println(e.getMessage());
            } catch (BadWeekdayException e) {
                System.out.println(e.getMessage());
            } catch (BadNumOfClassException e) {
                System.out.println(e.getMessage());
            } catch (BadNumOfPairException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
