package leifu.mvvmdemo.bean;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/5 16:28
 * 描述:
 */

public class User {
    private final  String name;
    private final  String phone;

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;

    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
