/*
 * CourseList.java     1.0     Wed, 28/08/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package edu.iuh.fit;

import java.util.*;

/**
 * This class represents a list of courses in the system.
 * It provides methods to add, remove, and search for courses.
 * It also provides methods to sort courses and find courses with the most credits.
 *
 * @author Tue Minh
 * @version 1.0
 * @since 2024-08-28
 */
public class CourseList {

    private Course[] courses;
    private int count = 0;

    /**
     * Constructs a CourseList with the specified number of courses.
     *
     * @param n The number of courses. Must be greater than 0.
     * @throws IllegalArgumentException if the number of courses is less than or equal to 0.
     */
    public CourseList(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("The number of courses must be greater than 0");
        }
        courses = new Course[n];
    }

    /**
     * Adds a course to the list.
     *
     * @param course The course to be added. Must not be null.
     * @return true if the course was added successfully, false otherwise.
     */
    public boolean addCourse(Course course) {
        if (course == null) {
            return false;
        }

        if (exists(course)) {
            System.out.println("Course with ID " + course.getId() + " already exists.");
            return false;
        }

        if (count < courses.length) {
            courses[count++] = course;
            return true;
        }
        return false;
    }

    /**
     * Checks if a course already exists in the list.
     *
     * @param course The course to be checked.
     * @return true if the course exists in the list, false otherwise.
     */
    private boolean exists(Course course) {
        for (Course c : courses) {
            if (c != null && c.getId().equalsIgnoreCase(course.getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the array of courses.
     *
     * @return An array of Course objects.
     */
    public Course[] getCourses() {
        return Arrays.copyOf(courses, count);
    }

    /**
     * Removes a course from the list.
     *
     * @param courseId The ID of the course to be removed.
     * @return true if the course was removed successfully, false otherwise.
     */
    public boolean removeCourse(String courseId) {
        for (int i = 0; i < count; i++) {
            if (courses[i].getId().equalsIgnoreCase(courseId)) {
                courses[i] = courses[--count];
                courses[count] = null;
                return true;
            }
        }
        System.out.println("Course with ID " + courseId + " does not exist.");
        return false;
    }

    /**
     * Searches for a course by its ID.
     *
     * @param courseId The ID of the course to be searched.
     * @return The course if found, null otherwise.
     */
    public Course searchCourseById(String courseId) {
        for (Course course : courses) {
            if (course != null && course.getId().equalsIgnoreCase(courseId)) {
                return course;
            }
        }
        return null;
    }

    /**
     * Searches for courses by their title.
     *
     * @param title The title of the courses to be searched.
     * @return An array of courses with the specified title.
     */
    public Course[] searchCourse(String title) {
        Course[] result = new Course[count];
        int resultCount = 0;
        for (Course course : courses) {
            if (course != null && course.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result[resultCount++] = course;
            }
        }
        return Arrays.copyOf(result, resultCount);
    }

    /**
     * Searches for courses by their department.
     *
     * @param department The department of the courses to be searched.
     * @return An array of courses in the specified department.
     */
    public Course[] searchCourseByDepartment(String department) {
        Course[] result = new Course[count];
        int resultCount = 0;
        for (Course course : courses) {
            if (course != null && course.getDepartment().equalsIgnoreCase(department)) {
                result[resultCount++] = course;
            }
        }
        return Arrays.copyOf(result, resultCount);
    }

    /**
     * Sorts the courses by their title.
     *
     * @return A sorted array of courses by title.
     */
    public Course[] sortCourses() {
        Course[] sortedCourses = Arrays.copyOf(courses, count);
        Arrays.sort(sortedCourses, Comparator.comparing(Course::getTitle, String.CASE_INSENSITIVE_ORDER));
        return sortedCourses;
    }

    /**
     * Finds the courses with the most credits.
     *
     * @return An array of courses with the most credits.
     */
    public Course[] findMaxCreditCourses() {
        Course[] result = new Course[count];
        int resultCount = 0;
        int maxCredits = 0;
        for (Course course : courses) {
            if (course != null) {
                if (course.getCredit() > maxCredits) {
                    maxCredits = course.getCredit();
                    resultCount = 0;
                    result[resultCount++] = course;
                } else if (course.getCredit() == maxCredits) {
                    result[resultCount++] = course;
                }
            }
        }
        return Arrays.copyOf(result, resultCount);
    }

    /**
     * Finds the department with the most courses.
     *
     * @return The department with the most courses.
     */
    public String findDepartmentWithMostCourses() {
        if (count == 0) {
            return null;
        }

        Map<String, Integer> departmentCount = new HashMap<>();
        for (Course course : courses) {
            if (course != null) {
                departmentCount.put(course.getDepartment(), departmentCount.getOrDefault(course.getDepartment(), 0) + 1);
            }
        }

        return departmentCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}