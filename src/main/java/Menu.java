public class Menu {

    public String show() {
        StringBuilder sb = new StringBuilder();
        String line = "-----------------------------------------------------|\n";
        sb.append("=====================================================\n")
                .append("|           BOOKING  APP                             |\n")
                .append("=====================================================|\n")
                .append(pad("1. ONLINE-BOARD"))
                .append(line)
                .append(pad("2. SHOW THE FLIGHT INFORMATION "))
                .append(line)
                .append(pad("3. SEARCH AND BOOK FLIGHT"))
                .append(line)
                .append(pad("4. CANCEL THE BOOKING "))
                .append(line)
                .append(pad("5. MY FLIGHTS "))
                .append(line)
                .append(pad("6. EXIT   "))
                .append(line);
        return sb.toString();
    }

    private String pad(String str) {
        String format = String.format("|" + "%-" + 52 + "s" + "|" + "\n", str);
        return format;
    }

}