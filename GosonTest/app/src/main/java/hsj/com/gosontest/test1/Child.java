package hsj.com.gosontest.test1;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hsj on 2018/1/3.
 * {
 * "age":10,
 * "books":[
 *    {
 *    "name":"格林童话0",
 *    "price":"价格：0$"
 *    },
 *    {
 *    "name":"格林童话1",
 *    "price":"价格：1$"
 *    },
 *    {
 *    "name":"格林童话2",
 *    "price":"价格：2$"
 *    }
 *   ],
 *    "id":1,
 *    "name":"小孩A",
 *    "sex":"男",
 *    "toys":[
 *    "小车",
 *    "皮卡丘",
 *    "奥特曼",
 *    "火影忍者"
 *    ],
 *    "toysMap":{
 *    "4":"火影忍者2",
 *    "1":"小车2",
 *    "2":"皮卡丘2",
 *    "3":"奥特曼2"
 *    }
 * }
 */

public class Child {

    /**
     * age : 10
     * books : [{"name":"格林童话0","price":"价格：0$"},{"name":"格林童话1","price":"价格：1$"},{"name":"格林童话2","price":"价格：2$"}]
     * id : 1
     * name : 小孩A
     * sex : 男
     * toys : ["小车","皮卡丘","奥特曼","火影忍者"]
     * toysMap : {"4":"火影忍者2","1":"小车2","2":"皮卡丘2","3":"奥特曼2"}
     */

    private int age;
    private int id;
    private String name;
    private String sex;
    private ToysMapBean toysMap;
    private List<BooksBean> books;
    private List<String> toys;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public ToysMapBean getToysMap() {
        return toysMap;
    }

    public void setToysMap(ToysMapBean toysMap) {
        this.toysMap = toysMap;
    }

    public List<BooksBean> getBooks() {
        return books;
    }

    public void setBooks(List<BooksBean> books) {
        this.books = books;
    }

    public List<String> getToys() {
        return toys;
    }

    public void setToys(List<String> toys) {
        this.toys = toys;
    }

    public static class ToysMapBean {
        /**
         * 4 : 火影忍者2
         * 1 : 小车2
         * 2 : 皮卡丘2
         * 3 : 奥特曼2
         */

        @SerializedName("4")
        private String _$4;
        @SerializedName("1")
        private String _$1;
        @SerializedName("2")
        private String _$2;
        @SerializedName("3")
        private String _$3;

        public String get_$4() {
            return _$4;
        }

        public void set_$4(String _$4) {
            this._$4 = _$4;
        }

        public String get_$1() {
            return _$1;
        }

        public void set_$1(String _$1) {
            this._$1 = _$1;
        }

        public String get_$2() {
            return _$2;
        }

        public void set_$2(String _$2) {
            this._$2 = _$2;
        }

        public String get_$3() {
            return _$3;
        }

        public void set_$3(String _$3) {
            this._$3 = _$3;
        }
    }

    public static class BooksBean {
        /**
         * name : 格林童话0
         * price : 价格：0$
         */

        private String name;
        private String price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
