import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int availableSlots;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.availableSlots = capacity;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void registerStudent() {
        if (availableSlots > 0) {
            availableSlots--;
            System.out.println("Student registered for " + title + " (" + code + ")");
        } else {
            System.out.println("No available slots for " + title + " (" + code + ")");
        }
    }

    public void removeStudent() {
        if (availableSlots < capacity) {
            availableSlots++;
            System.out.println("Student removed from " + title + " (" + code + ")");
        } else {
            System.out.println("No students registered for " + title + " (" + code + ")");
        }
    }

    public String toString() {
        return code + " - " + title + "\nDescription: " + description +
                "\nSchedule: " + schedule + "\nAvailable Slots: " + availableSlots + "/" + capacity;
    }
}

class Student {
    private int studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            registeredCourses.add(course);
            course.registerStudent();
            System.out.println(name + " registered for " + course.getTitle());
        } else {
            System.out.println(name + " is already registered for " + course.getTitle());
        }
    }

    public void removeCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.removeStudent();
            System.out.println(name + " removed from " + course.getTitle());
        } else {
            System.out.println(name + " is not registered for " + course.getTitle());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Course course1 = new Course("CSCI101", "Introduction to Computer Science", "Fundamental concepts of programming.", 30, "MWF 10:00 AM");
        Course course2 = new Course("MATH202", "Linear Algebra", "Study of linear equations and matrices.", 25, "TTh 2:00 PM");

        List<Course> availableCourses = new ArrayList<>();
        availableCourses.add(course1);
        availableCourses.add(course2);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student's name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();
        Student student = new Student(studentID, studentName);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Course Listing");
            System.out.println("2. Register for Course");
            System.out.println("3. Remove Course");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Available Courses:");
                    for (Course course : availableCourses) {
                        System.out.println(course);
                    }
                    break;
                case 2:
                    System.out.println("Available Courses:");
                    for (int i = 0; i < availableCourses.size(); i++) {
                        System.out.println((i + 1) + ". " + availableCourses.get(i).getTitle());
                    }
                    System.out.print("Select a course to register (Enter course number): ");
                    int courseNumber = scanner.nextInt();
                    if (courseNumber >= 1 && courseNumber <= availableCourses.size()) {
                        student.registerCourse(availableCourses.get(courseNumber - 1));
                    } else {
                        System.out.println("Invalid course number.");
                    }
                    break;
                case 3:
                    System.out.println(student.getName() + "'s Registered Courses:");
                    List<Course> registeredCourses = student.getRegisteredCourses();
                    for (int i = 0; i < registeredCourses.size(); i++) {
                        System.out.println((i + 1) + ". " + registeredCourses.get(i).getTitle());
                    }
                    System.out.print("Select a course to remove (Enter course number): ");
                    int removeCourseNumber = scanner.nextInt();
                    if (removeCourseNumber >= 1 && removeCourseNumber <= registeredCourses.size()) {
                        student.removeCourse(registeredCourses.get(removeCourseNumber - 1));
                    } else {
                        System.out.println("Invalid course number.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }
}
