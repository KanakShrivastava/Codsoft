import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }
}

class Student {
    String studentID;
    String name;
    List<String> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}

public class CourseRegistration {
    static Map<String, Course> courseDatabase = new HashMap<>();
    static Map<String, Student> studentDatabase = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Course Listing");
            System.out.println("2. Student Registration");
            System.out.println("3. Course Removal");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayCourseListing();
                    break;
                case 2:
                    performStudentRegistration(scanner);
                    break;
                case 3:
                    performCourseRemoval(scanner);
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void displayCourseListing() {
        System.out.println("Course Listing:");
        for (Course course : courseDatabase.values()) {
            int availableSlots = course.capacity - studentCountForCourse(course.code);
            System.out.println(course.code + " - " + course.title + " | Available Slots: " + availableSlots);
        }
        System.out.println();
    }

    private static void performStudentRegistration(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();

        Student student = new Student(studentID, studentName);
        studentDatabase.put(studentID, student);

        displayCourseListing();

        System.out.print("Enter the course code to register: ");
        String courseCode = scanner.nextLine();

        if (courseDatabase.containsKey(courseCode)) {
            Course course = courseDatabase.get(courseCode);
            if (student.registeredCourses.size() < course.capacity) {
                student.registeredCourses.add(courseCode);
                System.out.println("Registration successful!");
            } else {
                System.out.println("Registration failed. Course is full.");
            }
        } else {
            System.out.println("Invalid course code. Registration failed.");
        }
    }

    private static void performCourseRemoval(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();

        if (studentDatabase.containsKey(studentID)) {
            Student student = studentDatabase.get(studentID);

            System.out.println("Registered courses for " + student.name + ": " + student.registeredCourses);

            System.out.print("Enter the course code to remove: ");
            String courseCodeToRemove = scanner.nextLine();

            if (student.registeredCourses.contains(courseCodeToRemove)) {
                student.registeredCourses.remove(courseCodeToRemove);
                System.out.println("Course removal successful!");
            } else {
                System.out.println("Invalid course code. Removal failed.");
            }
        } else {
            System.out.println("Student not found. Removal failed.");
        }
    }

    private static int studentCountForCourse(String courseCode) {
        int count = 0;
        for (Student student : studentDatabase.values()) {
            if (student.registeredCourses.contains(courseCode)) {
                count++;
            }
        }
        return count;
    }
}
