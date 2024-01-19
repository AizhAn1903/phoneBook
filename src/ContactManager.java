import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ContactManager {
    private Contact[] contacts;
    private int size;


    public ContactManager() {
        contacts = new Contact[100];
        size = 0;

    }

    public void createContact() {
       Scanner scanner = new Scanner(System.in);
        if (size < contacts.length) {
            System.out.println("Введите имя контакта:");
           String firstName = scanner.nextLine();
            System.out.println("Введите фамилию контакта:");
            String sureName = scanner.nextLine();

            String phoneNumber;
            do {
                System.out.println("Введите номер контакта:");
                phoneNumber = scanner.nextLine();
            } while (!isNumeric(phoneNumber));

            System.out.println("Введите Email контакта:");
           String email = scanner.nextLine();
            Contact newContact = new Contact(firstName,sureName,phoneNumber,email);
            contacts[size] = newContact;
            size++;
            System.out.println("Контакт успешно создан");
        } else {
            System.out.println("Максимальное число контактов достигнуто." +
                    " Невозможно создать контакт");
        }

    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Введите числовое значение для номера телефона.");
            return false;
        }
    }

    public void readContact() {
        try {


            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите имя  для просмотра контакта:");
            String search = scanner.nextLine();
            boolean contactFound = false;
            for (Contact contact : contacts) {
                if (contact.getFirstName().toLowerCase().contains(search.toLowerCase()))
                {
                    System.out.println("Информация о контакте:");
                    System.out.println(contact);
                    contactFound = true;
                }
            }
            if (!contactFound) {
                System.out.println("Контакт с указанным именем  не найден.");
            }
        } catch (NullPointerException e) {
            System.out.println("----------------------");
        }
    }
    public void updateContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите индексный номер для обновления:");
        int contactIndex = scanner.nextInt();
        scanner.nextLine();



        if (contactIndex >= 1 && contactIndex <= size) {
            Contact contact = contacts[contactIndex - 1];

            System.out.println("Введите  новое имя контакта:");
            String newFirstName = scanner.nextLine();
            contact.setFirstName(newFirstName);

            System.out.println("Введите новую фамилию контакта:");
            String newSureName = scanner.nextLine();
            contact.setSureName(newSureName);

            System.out.println("Введите новый номер контакта:");
            String newPhoneNumber = scanner.nextLine();
            contact.setPhoneNumber(newPhoneNumber);


            System.out.println("Введите Email контакта:");
            String newEmail = scanner.nextLine();
            contact.setEmail(newEmail);
            System.out.println("Информация о контакте успешно обновлена ");
        } else {
            System.out.println("Контакт с указанным индексом не существует.");
        }

    }

    public void deleteContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите индексный номер контакта:");
        int contactIndex = scanner.nextInt();
        scanner.nextLine();

        if (contactIndex >= 1 && contactIndex <= size) {
            Contact contact = contacts[contactIndex - 1];
            System.out.println("Вы уверены что хотите удалить контакт?");
            System.out.println("Имя: " + contact.getFirstName());
            System.out.println("Фамилия: " + contact.getSureName());
            System.out.println("Номер телефона: " + contact.getPhoneNumber());
            System.out.println("Email: " + contact.getEmail());
            System.out.println("Введите 'да' для подтверждения удаления или 'нет' для отмены");

            String action = scanner.nextLine();

            if (action.equalsIgnoreCase("да")) {

                for (int i = contactIndex - 1 ; i < size - 1; i++) {
                    contacts[i] = contacts[i + 1];
                }
                contacts[size - 1] = null;
                size--;
                System.out.println("Контакт успешно удален ");
            } else if (action.equalsIgnoreCase("нет")) {
                System.out.println("Удаление отменено");
            } else {
                System.out.println("Введенное значение не распознано");
            }
        } else {
            System.out.println("Контакт с указанным индексом не существует");
        }
    }

    public void searchContacts() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите имя  для поиска контакта:");
            String search = scanner.nextLine();
            boolean contactFound = false;
            for (Contact contact : contacts) {
                if (contact.getFirstName().toLowerCase().contains(search.toLowerCase()) ||
                contact.getPhoneNumber().toLowerCase().contains(search.toLowerCase()))
               {
                    System.out.println("Найден контакт:");
                    System.out.println(contact);
                    contactFound = true;
                }
            }
            if (!contactFound) {
                System.out.println("Контакт с указанным именем или номером не найден.");
            }
        } catch (NullPointerException e) {
            System.out.println("----------------------");
        }
    }



    public void displayAllContacts() {

            System.out.println("Список контактов:");

            if (contacts.length == 0 || contacts[0] == null) {
            System.out.println("Список контактов пуст.");
        }
            for (int i = 0; i < contacts.length;i++) {
                Contact contact = contacts[i];
                if (contact != null) {

                    System.out.println(  (i + 1) + "|" + contact.getFirstName());
                    System.out.println("---------------------------");
                }
            }

    }

    public void saveContactsToFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название для файла:");
        String fileName = scanner.nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Contact contact : contacts) {
                if (contact != null) {
                    writer.write(contact.getFirstName() + "," + contact.getSureName() + "," + contact.getPhoneNumber() + "," + contact.getEmail());
                    writer.newLine();
                }
            }
            System.out.println("Контакты успешно сохранены в файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении контактов в файл: " + e.getMessage());
        }
    }


    public void loadContactsFromFile(  int startingIndex) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название  файла:");
        String fileName = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int currentIndex = startingIndex;
            int arrayLength = contacts.length;
            while ((line = reader.readLine()) != null && currentIndex < arrayLength) {
                String[] contactInfo = line.split(",");
                if (contactInfo.length >= 4) {
                    String firstName = contactInfo[0];
                    String lastName = contactInfo[1];
                    String phoneNumber = contactInfo[2];
                    String email = contactInfo[3];

                    Contact contact = new Contact(firstName, lastName, phoneNumber, email);
                    contacts[currentIndex] = contact;
                    currentIndex++;
                } else {
                    System.out.println("Неверный формат строки: " + line);
                }
            }
            System.out.println("Контакты успешно загружены из файла в существующий массив.");
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке контактов из файла: " + e.getMessage());
        }
    }





}


