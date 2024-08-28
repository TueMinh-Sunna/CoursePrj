/*
 * Course.java     1.0     Wed, 28/08/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package edu.iuh.fit;

/**
 * This class represents a course in the system
 *
 * @author Tue Minh
 * @version 1.0
 * @since 2024-08-28
 */

public class Course {
    /**
     * the number of credits of the course (e.g. 3, 4)
    */
    private int credit;
    /**
     * the department of the course (e.g. FIT, FCS)
     */
    private String department;
    /**
     * the id of the course (e.g. FIT101)
     */
    private String id;
    /**
     * the title of the course (e.g. Java, Database)
     */
    private String title;

    //Contructors
    public Course() {
        this("123","title",1,"");
    }

    public Course(String id, String title, int credit, String department) {
        setId(id);
        setTitle(title);
        setCredit(credit);
        setDepartment(department);
    }

    //Setters and Getters
    public String getId() {
        return id;
    }

    /**
     * Set the id of the course
     *
     * @param id The id must have at least 3 characters
     *           The id must contain only letters or digits
     * @throws IllegalArgumentException if the id is invalid
     */
    public void setId(String id) {
        if(id.length() < 3){
            throw new IllegalArgumentException("ID must have at least 3 characters");
        }

        for(int i = 0; i < id.length(); i++){
            if(!Character.isLetterOrDigit(id.charAt(i))){
                throw new IllegalArgumentException("ID must contain only letters or digits");
            }
        }
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the course
     *
     * @param title The title must not be empty
     * @throws IllegalArgumentException if the title is empty
     */
    public void setTitle(String title) {
        if(title.isEmpty()){
            throw new IllegalArgumentException("Title must not be empty");
        }
        this.title = title;
    }

    public int getCredit() {
        if(credit <= 0){
            throw new IllegalArgumentException("Credit must be greater than 0");
        }
        return credit;
    }

    /**
     * Set the credit of the course
     *
     * @param credit The credit must be greater than 0
     * @throws IllegalArgumentException if the credit is less than or equal to 0
     */
    public void setCredit(int credit) {
        if(credit <= 0){
            throw new IllegalArgumentException("Credit must be greater than 0");
        }
        this.credit = credit;
    }

    public String getDepartment() {
        return department;
    }

    /**
     * Set the department of the course
     *
     * @param department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return String.format("%-10s%-30s%5d  %-15s", id, title, credit, department);
    }
}
