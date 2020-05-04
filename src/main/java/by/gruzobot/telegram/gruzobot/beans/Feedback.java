package by.gruzobot.telegram.gruzobot.beans;
/*
  Здесь навешай логгер при обращении к классу из api
 */
public class Feedback {
    private String name;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
