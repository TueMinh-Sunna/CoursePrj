/*
 * TestCourse.java     1.0     Wed, 28/08/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package edu.iuh.fit;

/*
 * @desc:
 * @author:     Tue Minh
 * @date:       Wed, 28/08/2024
 * @version:    1.0
 */

import java.util.Scanner;

public class TestCourse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CourseList courseList = new CourseList(10);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Thêm khóa học");
            System.out.println("2. Xóa khóa học");
            System.out.println("3. Tìm khóa học theo mã");
            System.out.println("4. Tìm khóa học theo tên");
            System.out.println("5. Tìm khóa học theo khoa");
            System.out.println("6. Sắp xếp khóa học theo tên");
            System.out.println("7. Tìm khóa học có số tín chỉ lớn nhất");
            System.out.println("8. Tìm khoa có nhiều khóa học nhất");
            System.out.println("9. Hiển thị danh sách khóa học");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Nhập mã khóa học: ");
                    String id = scanner.nextLine();
                    System.out.print("Nhập tên khóa học: ");
                    String title = scanner.nextLine();
                    System.out.print("Nhập số tín chỉ: ");
                    int credit = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Nhập khoa phụ trách: ");
                    String department = scanner.nextLine();
                    Course course = new Course(id, title, credit, department);
                    if (courseList.addCourse(course)) {
                        System.out.println("Thêm khóa học thành công.");
                    } else {
                        System.out.println("Thêm khóa học thất bại.");
                    }
                    break;
                case 2:
                    System.out.print("Nhập mã khóa học cần xóa: ");
                    String removeId = scanner.nextLine();
                    if (courseList.removeCourse(removeId)) {
                        System.out.println("Xóa khóa học thành công.");
                    } else {
                        System.out.println("Xóa khóa học thất bại.");
                    }
                    break;
                case 3:
                    System.out.print("Nhập mã khóa học cần tìm: ");
                    String searchId = scanner.nextLine();
                    Course foundCourse = courseList.searchCourseById(searchId);
                    if (foundCourse != null) {
                        System.out.println("Khóa học tìm thấy: " + foundCourse);
                    } else {
                        System.out.println("Không tìm thấy khóa học.");
                    }
                    break;
                case 4:
                    System.out.print("Nhập tên khóa học cần tìm: ");
                    String searchTitle = scanner.nextLine();
                    Course[] coursesByTitle = courseList.searchCourse(searchTitle);
                    if (coursesByTitle.length > 0) {
                        printCourses(coursesByTitle);
                    } else {
                        System.out.println("Không tìm thấy khóa học.");
                    }
                    break;
                case 5:
                    System.out.print("Nhập khoa cần tìm: ");
                    String searchDepartment = scanner.nextLine();
                    Course[] coursesByDepartment = courseList.searchCourseByDepartment(searchDepartment);
                    if (coursesByDepartment.length > 0) {
                        printCourses(coursesByDepartment);
                    } else {
                        System.out.println("Không tìm thấy khóa học.");
                    }
                    break;
                case 6:
                    Course[] sortedCourses = courseList.sortCourses();
                    printCourses(sortedCourses);
                    break;
                case 7:
                    Course[] coursesWithMostCredits = courseList.findMaxCreditCourses();
                    printCourses(coursesWithMostCredits);
                    break;
                case 8:
                    String departmentWithMostCourses = courseList.findDepartmentWithMostCourses();
                    if (departmentWithMostCourses != null) {
                        System.out.println("Khoa có nhiều khóa học nhất: " + departmentWithMostCourses);
                    } else {
                        System.out.println("Không có khóa học nào.");
                    }
                    break;
                case 9:
                    printCourses(courseList.getCourses());
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void printCourses(Course[] courses) {
        System.out.printf("%-10s %-20s %-10s %-10s%n", "Mã KH", "Tên KH", "Số TC", "Khoa");
        for (Course course : courses) {
            if (course != null) {
                System.out.printf("%-10s %-20s %-10d %-10s%n", course.getId(), course.getTitle(), course.getCredit(), course.getDepartment());
            }
        }
    }
}

