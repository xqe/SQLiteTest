package com.example.sqltest.sqlitetest;

public class DataBean {

    private String name;
    private String address;
    private int age;

    private DataBean(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static class Builder {
        private String name;
        private String address;
        private int age;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public DataBean build(){
            return new DataBean(name,address,age);
        }
    }
}
