package mx.edu.utng.orders.model;

/**
 * Created by jony on 23/02/17.
 */

public class Category {
    private String idCategory;
    private String CategoryName;
    private String listPosition;


    public Category(String idCategory, String categoryName, String listPosition) {
        this.idCategory = idCategory;
        this.CategoryName = categoryName;
        this.listPosition = listPosition;
    }

    public Category() {
        this("","","");
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getListPosition() {
        return listPosition;
    }

    public void setListPosition(String listPosition) {
        this.listPosition = listPosition;
    }

    @Override
    public String toString() {
        return "Category{" +
                "idCategory='" + idCategory + '\'' +
                ", CategoryName='" + CategoryName + '\'' +
                ", listPosition='" + listPosition + '\'' +
                '}';
    }
}
