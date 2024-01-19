import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
      ContactManager contactManager = new ContactManager();

        Scanner scanner = new Scanner(System.in);
        int answer = 1;

        while (answer != 0) {
            System.out.println("========Телефонная книжка========" +
                    "\n1.Просмотреть контакты " +
                    "\n2.Создать новый контакт" +
                    "\n3.Информация о контакте" +
                    "\n4.Удалить контакт" +
                    "\n5.Сохранить контакты в файл" +
                    "\n6.Загрузить контакты из файла" +
                    "\n7.Обновить контакты" +
                    "\n8.Поиск контакта по номеру или по телефону" +
                    "\n0.Выход" +
                            "\n============================================" +
                    "\nВыберите действие: "
                    );

            answer = scanner.nextInt();
            switch (answer) {
                case 0:
                    System.out.println("Выход из программы.....");
                    break;
                case 1:
                    System.out.println("========Ваши контакты========");

                    contactManager.displayAllContacts();
                    break;
                case 2:
                    System.out.println("========Создание нового контакта========");

                    contactManager.createContact();
                    break;
                case 3:
                    System.out.println("========Информация о контакте========");
                    contactManager.displayAllContacts();
                    contactManager.readContact();
                    break;
                case 4:
                    System.out.println("========Удаление контакта========");
                    contactManager.displayAllContacts();
                    contactManager.deleteContact();
                    break;
                case 5:
                    System.out.println("========Сахранение контакта в файл========");
                    contactManager.saveContactsToFile();
                    break;
                case 6:
                    System.out.println("========Загрузка контакта из файла========");
                    contactManager.loadContactsFromFile(0);
                    break;
                case 7:
                    System.out.println("========Обновление контакта=======");
                    contactManager.displayAllContacts();
                    contactManager.updateContact();
                    break;
                case 8:
                    System.out.println("========Поиск контакта========");
                    contactManager.searchContacts();
                    break;
                default:
                    System.out.println("Ошибка ввода.....");
                    break;


            }
        }
    }
}