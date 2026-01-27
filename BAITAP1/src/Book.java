import java.util.Scanner;

public class Book {
    private int id;
    private String title;
    private String author;
    private long price;

    public Book() {}

    public Book(int id, String title, String author, long price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public long getPrice() { return price; }
    public void setPrice(long price) { this.price = price; }

    public void input(Scanner x) {
        System.out.print("Nhập mã sách: ");
        while (!x.hasNextInt()) {
            System.out.print("Mã sách phải là số, nhập lại: ");
            x.next();
        }
        this.id = x.nextInt();
        x.nextLine(); 

        
        do {
            System.out.print("Nhập tên sách: ");
            this.title = x.nextLine().trim();
        } while (this.title.isEmpty());

        
        do {
            System.out.print("Nhập tác giả: ");
            this.author = x.nextLine().trim();
        } while (this.author.isEmpty());

        
        System.out.print("Nhập đơn giá: ");
        while (!x.hasNextLong()) {
            System.out.print("Đơn giá phải là số, nhập lại: ");
            x.next();
        }
        long p = x.nextLong();
        x.nextLine(); 
        while (p < 0) {
            System.out.print("Đơn giá không được âm, nhập lại: ");
            while (!x.hasNextLong()) {
                System.out.print("Đơn giá phải là số, nhập lại: ");
                x.next();
            }
            p = x.nextLong();
            x.nextLine();
        }
        this.price = p;
    }

    public void output() {
        String msg = "BOOK: id=%d, title=%s, author=%s, price=%d"
                .formatted(id, title, author, price);
        System.out.println(msg);
    }
}
