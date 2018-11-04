package co.acrch.system.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoice {
    /** 請求日 */
    private Date invoiceDate;

    /** 請求No. */
    private String invoiceNo;

    /** 顧客住所 */
    private String clientAddress;

    /** 顧客郵便番号 */
    private String clientPostCode;

    /** 顧客名 */
    private String clientName;

    /** 営業担当者 */
    private String salesRep;

    /** 立替金 */
    private BigDecimal advancePaid;

    /** 請求額（税込）*/
    private BigDecimal invoiceAmtTaxin;

    /** 税額 */
    private BigDecimal taxAmt;

    /** 備考 */
    private String note;

    /** 明細 */
    private List<InvoiceDetail> details = new ArrayList<InvoiceDetail>();

    /**
     * @return invoiceDateを取得する
     */
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * @param invoiceDate
     */
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    /**
     * @return invoiceNoを取得する
     */
    public String getInvoiceNo() {
        return invoiceNo;
    }

    /**
     * @param invoiceNo
     */
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    /**
     * @return clientNameを取得する
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @param clientName
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * @return salesRepを取得する
     */
    public String getSalesRep() {
        return salesRep;
    }

    /**
     * @param salesRep
     */
    public void setSalesRep(String salesRep) {
        this.salesRep = salesRep;
    }

    /**
     * @return advancePaidを取得する
     */
    public BigDecimal getAdvancePaid() {
        return advancePaid;
    }

    /**
     * @param advancePaid
     */
    public void setAdvancePaid(BigDecimal advancePaid) {
        this.advancePaid = advancePaid;
    }

    /**
     * @return invoiceAmtTaxinを取得する
     */
    public BigDecimal getInvoiceAmtTaxin() {
        return invoiceAmtTaxin;
    }

    /**
     * @param invoiceAmtTaxin
     */
    public void setInvoiceAmtTaxin(BigDecimal invoiceAmtTaxin) {
        this.invoiceAmtTaxin = invoiceAmtTaxin;
    }

    /**
     * @return taxAmtを取得する
     */
    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    /**
     * @param taxAmt
     */
    public void setTaxAmt(BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    /**
     * @return detailsを取得する
     */
    public List<InvoiceDetail> getDetails() {
        return details;
    }

    /**
     * @param details
     */
    public void setDetails(List<InvoiceDetail> details) {
        this.details = details;
    }

    /**
     * @return clientAddressを取得する
     */
    public String getClientAddress() {
        return clientAddress;
    }

    /**
     * @param clientAddress
     */
    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    /**
     * @return clientPostCodeを取得する
     */
    public String getClientPostCode() {
        return clientPostCode;
    }

    /**
     * @param clientPostCode
     */
    public void setClientPostCode(String clientPostCode) {
        this.clientPostCode = clientPostCode;
    }

    /**
     * @return noteを取得する
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }
}
