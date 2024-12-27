import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class representing a Book
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrow() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("You have borrowed: " + title);
        } else {
            System.out.println("Sorry, this book is currently not available.");
        }
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("You have returned: " + title);
        } else {
            System.out.println("This book wasn't borrowed.");
        }
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Available: " + (isAvailable ? "Yes" : "No");
    }
}

// Class representing a Library
class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Added: " + book.getTitle());
    }

    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
        System.out.println("Removed book with ISBN: " + isbn);
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }
        System.out.println("Books in the Library:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        System.out.println("No book found with ISBN: " + isbn);
        return null;
    }

    public void borrowBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book != null) {
            book.borrow();
        }
    }

    public void returnBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book != null) {
            book.returnBook();
        }
    }
}

// Main Application Class
public class LibraryManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewBooks();
                    break;
                case 2:
                    borrowBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    addBook();
                    break;
                case 5:
                    removeBook();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nLibrary Management System");
        System.out.println("1. View all books");
        System.out.println("2. Borrow a book");
        System.out.println("3. Return a book");
        System.out.println("4. Add a new book");
        System.out.println("5. Remove a book");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void viewBooks() {
        library.listBooks();
    }

    private static void borrowBook() {
        System.out.print("Enter ISBN of the book you want to borrow: ");
        String isbn = scanner.nextLine();
        library.borrowBook(isbn);
    }

    private static void returnBook() {
        System.out.print("Enter ISBN of the book you want to return: ");
        String isbn = scanner.nextLine();
        library.returnBook(isbn);
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        Book newBook = new Book(title, author, isbn);
        library.addBook(newBook);
    }

    private static void removeBook() {
        System.out.print("Enter ISBN of the book to remove: ");
        String isbn = scanner.nextLine();
        library.removeBook(isbn);
    }
}
