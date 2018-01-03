package hsj.com.gosontest.test0;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hsj on 2018/1/3.
 */
public class Child {
    private int id;
    private String name;
    private String sex;
    private int age;
    //儿童玩具
    private ArrayList<String> toys;
    //儿童玩具的map
    private HashMap<String, String> toysMap = new HashMap<String, String>();
    //小孩的书籍
    private ArrayList<Book> books = new ArrayList<Book>();

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public HashMap<String, String> getToysMap() {
        return toysMap;
    }

    public void setToysMap(HashMap<String, String> toysMap) {
        this.toysMap = toysMap;
    }

    public ArrayList<String> getToys() {
        return toys;
    }

    public void setToys(ArrayList<String> toys) {
        this.toys = toys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
