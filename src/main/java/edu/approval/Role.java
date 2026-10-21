package edu.approval;

public enum Role {
    EMPLOYEE("Сотрудник"),
    ACCOUNTANT("Бухгалтер"),
    LAWYER("Юрист"),
    HR("Кадровик"),
    DIRECTOR("Директор");

    private String title;

    Role(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
