import java.text.Normalizer;
import java.util.*;

public class App {

    
    private static int readInt(Scanner sc, String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.print("Vui lòng nhập số: ");
            sc.next();
        }
        int v = sc.nextInt();
        sc.nextLine(); 
        return v;
    }

    
    private static String readLineNonEmpty(Scanner sc, String prompt) {
        String s;
        do {
            System.out.print(prompt);
            s = sc.nextLine().trim();
        } while (s.isEmpty());
        return s;
    }

   
    private static String normalizeText(String s) {
        if (s == null) return "";
        String n = Normalizer.normalize(s, Normalizer.Form.NFD);
        n = n.replaceAll("\\p{M}+", ""); // bỏ dấu
        return n.toLowerCase(Locale.ROOT).trim();
    }

    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        Scanner x = new Scanner(System.in);

        String menu = """
            ===== Chương trình quản lý sách =====
            1. Thêm 1 cuốn sách
            2. Xóa 1 cuốn sách
            3. Thay đổi sách
            4. Xuất thông tin
            5. Tìm sách lập trình
            6. Lấy sách tối đa theo giá
            7. Tìm kiếm theo tác giả
            0. Thoát
            Chọn chức năng: """;

        int chon;
        do {
            chon = readInt(x, menu);

            switch (chon) {
                case 1 -> {
                    Book newBook = new Book();
                    newBook.input(x); 
                    listBook.add(newBook);
                    System.out.println("Đã thêm sách.");
                }

                case 2 -> {
                    if (listBook.isEmpty()) {
                        System.out.println("Danh sách rỗng, không thể xóa.");
                        break;
                    }

                    int bookId = readInt(x, "Nhập vào mã sách cần xóa: ");

                    Optional<Book> opt = listBook.stream()
                            .filter(p -> p.getId() == bookId)
                            .findFirst();

                    if (opt.isEmpty()) {
                        System.out.println("Không tìm thấy sách có mã: " + bookId);
                    } else {
                        listBook.remove(opt.get());
                        System.out.println("Đã xóa sách thành công.");
                    }
                }

                case 3 -> {
                    if (listBook.isEmpty()) {
                        System.out.println("Danh sách rỗng, không thể chỉnh sửa.");
                        break;
                    }

                    int bookId = readInt(x, "Nhập vào mã sách cần điều chỉnh: ");

                    Optional<Book> opt = listBook.stream()
                            .filter(p -> p.getId() == bookId)
                            .findFirst();

                    if (opt.isEmpty()) {
                        System.out.println("Không tìm thấy sách có mã: " + bookId);
                    } else {
                        opt.get().input(x);
                        System.out.println("Đã cập nhật sách.");
                    }
                }

                case 4 -> {
                    System.out.println("\nXuất thông tin danh sách:");
                    if (listBook.isEmpty()) System.out.println("(Danh sách rỗng)");
                    else listBook.forEach(Book::output);
                }

                case 5 -> {
                    if (listBook.isEmpty()) {
                        System.out.println("Danh sách rỗng.");
                        break;
                    }

              
                    String keyword = normalizeText("lập trình");

                    List<Book> list5 = listBook.stream()
                            .filter(b -> normalizeText(b.getTitle()).contains(keyword))
                            .toList();

                    if (list5.isEmpty()) System.out.println("Không tìm thấy sách lập trình.");
                    else list5.forEach(Book::output);
                }

                case 6 -> {
                    if (listBook.isEmpty()) {
                        System.out.println("Danh sách rỗng.");
                        break;
                    }

                    long maxPrice = listBook.stream()
                            .mapToLong(Book::getPrice)
                            .max()
                            .orElse(0);

                    List<Book> maxBooks = listBook.stream()
                            .filter(b -> b.getPrice() == maxPrice)
                            .toList();

                    System.out.println("Sách có giá cao nhất = " + maxPrice);
                    maxBooks.forEach(Book::output);
                }

                case 7 -> {
                    if (listBook.isEmpty()) {
                        System.out.println("Danh sách rỗng.");
                        break;
                    }

                    String authorKey = readLineNonEmpty(x, "Nhập tên tác giả cần tìm: ");
                    String key = normalizeText(authorKey);

                    List<Book> rs = listBook.stream()
                            .filter(b -> normalizeText(b.getAuthor()).contains(key))
                            .toList();

                    if (rs.isEmpty()) System.out.println("Không tìm thấy sách của tác giả: " + authorKey);
                    else rs.forEach(Book::output);
                }

                case 0 -> System.out.println("Thoát chương trình.");

                default -> System.out.println("Chọn sai chức năng!");
            }

            System.out.println(); 
        } while (chon != 0);

        
    }
}
