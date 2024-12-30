import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;
import java.util.StringTokenizer;

public class CSVSorter {
    public static void main(String[] args) throws FileNotFoundException {
        Vector<Reader> students = new Vector<Reader>();
        Vector<String> students2 = new Vector<String>();
        try (Scanner stu = new Scanner(new FileInputStream(args[1]));) {
            while (stu.hasNextLine()) {
                String line = stu.nextLine();
                StringTokenizer st = new StringTokenizer(line, ",");
                if (line.contains("2115")) {
                    while (st.hasMoreTokens()) {
                        students2.add(st.nextToken());
                    }
                }
            }
            addStudent(students2, students);//System.out.println(students);

            stu.close();

                switch (args[0]) {
                    case "-n":
                        System.out.println("Sort by studentID: ");
                        Sorting.byStudentID(students);
                        break;
                    case "-f":
                        System.out.println("Sort by first name: ");
                        Sorting.byfirstname(students);
                        break;
                    case "-l":
                        System.out.println("Sort by last name: ");
                        Sorting.bylastname(students);
                        break;
                    case "-s":
                        if(args.length>= 2) {
                            System.out.println("Search first name: " + args[2]);
                            Sorting.bySearchName(students, args[2]);
                        }else{
                            System.out.println("please provide a name to search for.");
                        }
                        break;
                    default:
                        System.out.println("invalid command");
                        break;


        }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            System.out.println("File not found: " + e.getMessage());
        }

    }

    public static void addStudent(Vector<String> students2, Vector<Reader> stu) { // Start addStudent method
        for (int i = 0; i < students2.size(); i += 4) {
            Reader st = new Reader(null, null, null);
            st.setNumber(students2.elementAt(i + 1));
            st.setFirstName(students2.elementAt(i + 2));
            st.setLastName(students2.elementAt(i + 3));
            stu.add(st);
        }


    }
}
class Sorting {
    public static void byStudentID(Vector<Reader> students) {
        Collections.sort(students, Comparator.comparing(Reader::getNumber));
        students.forEach(System.out::println);
    }

    public static void byfirstname(Vector<Reader> students) {
        Collections.sort(students, Comparator.comparing(Reader::getFirstName));
        students.forEach(System.out::println);
    }

    public static void bylastname(Vector<Reader> students) {
        Collections.sort(students, Comparator.comparing(Reader::getLastName));
        students.forEach(System.out::println);
    }

    public static void bySearchName(Vector<Reader> students, String searchName) {
        for (int i = 0; i < students.size(); i++) {
            Reader student = students.get(i);
            if (searchName.toLowerCase().equals(student.getFirstName().toLowerCase().trim())) {
               System.out.println("Index: " + i + " - " + student);
            }
        }
    }
}


