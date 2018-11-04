package co.acrch.system.dto;

import java.math.BigDecimal;

public class InvoiceDetail {
    /** 品名 */
    private String itemName;

    /** 単価 */
    private BigDecimal unitCost;

    /** 数量 */
    private Double quantity;

    /** 金額 */
    private BigDecimal amt;

    /**
     * @return itemNameを取得する
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return unitCostを取得する
     */
    public BigDecimal getUnitCost() {
        return unitCost;
    }

    /**
     * @param unitCost
     */
    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    /**
     * @return quantityを取得する
     */
    public Double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     */
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return amtを取得する
     */
    public BigDecimal getAmt() {
        return amt;
    }

    /**
     * @param amt
     */
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }
}
