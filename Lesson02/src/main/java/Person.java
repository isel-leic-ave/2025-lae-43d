class Person {
    private String name;
    private int age;
    private String country;

    public Person(String name, int age, String country) {
        this.name = name;
        this.age = age;
        this.country = country;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getCountry() {
        return country;
    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
}