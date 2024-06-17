package ra.entity;

import java.util.Scanner;

public class BookType implements IBookManagement {
    private int typeId;
    private String typeName;
    private String descriptions;
    private boolean typeStatus;

    public BookType() {
    }

    public BookType(int typeId, String typeName, String descriptions, boolean typeStatus) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.descriptions = descriptions;
        this.typeStatus = typeStatus;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(boolean typeStatus) {
        this.typeStatus = typeStatus;
    }

    @Override
    public void inputData(Scanner scanner) {
        System.out.println("Nhập vào tên loại sách:");
        this.typeName = scanner.nextLine();
        System.out.println("Nhập vào mô tả loại sách:");
        this.descriptions = scanner.nextLine();
        System.out.println("Nhập vào trạng thái loại sách:");
        this.typeStatus = Boolean.parseBoolean(scanner.nextLine());
    }

    @Override
    public void displayData() {
        System.out.printf("TypeID: %d - TypeName: %s - Descriptions: %s - TypeStatus: %s\n", this.typeId, this.typeName, this.descriptions, this.typeStatus ? "Active" : "Inactive");
    }
}
