package com.example.practice.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Product {

    @Min(value = 1, message = "minimal satu amgka")
    private Integer  productId;

    @NotEmpty(message = "tidak dapat dikosongkan")
    private String productName;

    @Min(value = 1, message = "minimal satu amgka")
    private Integer supplierId;

    @Min(value = 1, message = "minimal satu amgka")
    private Integer categoryId;

    @NotEmpty(message = "tidak dapat dikosongkan")
    private String quantityPerUnit;

    @Min(value = 1, message = "minimal satu amgka")
    private Integer unitPrice;

    @Min(value = 1, message = "minimal satu amgka")
    private Integer unitInStock;

    @Min(value = 1, message = "minimal satu amgka")
    private Integer unitsOnOrder;

    @Min(value = 1, message = "minimal satu amgka")
    private Integer reOrderLevel;

    @Min(value = 1, message = "minimal satu amgka")
    private Integer discontinued;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getUnitInStock() {
        return unitInStock;
    }

    public void setUnitInStock(Integer unitInStock) {
        this.unitInStock = unitInStock;
    }

    public Integer getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(Integer unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    public Integer getReOrderLevel() {
        return reOrderLevel;
    }

    public void setReOrderLevel(Integer reOrderLevel) {
        this.reOrderLevel = reOrderLevel;
    }

    public Integer getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Integer discontinued) {
        this.discontinued = discontinued;
    }
}
