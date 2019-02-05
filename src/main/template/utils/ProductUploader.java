package template.utils;

import org.springframework.web.multipart.MultipartFile;

public class ProductUploader {
    private String title;
    private String description;
    private int price;
    private MultipartFile photo;

    public ProductUploader() {
    }

    public ProductUploader(String title, String description, int price, MultipartFile photo) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
}
