package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hagen Trinter (h.trinter@micromata.de)
 */
public class Invoice {

  private String invoiceId = "";
  private String customerFirstName = "";
  private String customerLastName = "";

  private List<InvoiceItem> invoiceItems = new ArrayList<>();

  public Invoice() {
  }

  public Invoice(String invoiceId, String customerFirstName, String customerLastName, List<InvoiceItem> invoiceItems) {
    this.invoiceId = invoiceId;
    this.customerFirstName = customerFirstName;
    this.customerLastName = customerLastName;
    this.invoiceItems = invoiceItems;
  }

  public String getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(String invoiceId) {
    this.invoiceId = invoiceId;
  }

  public String getCustomerFirstName() {
    return customerFirstName;
  }

  public void setCustomerFirstName(String customerFirstName) {
    this.customerFirstName = customerFirstName;
  }

  public String getCustomerLastName() {
    return customerLastName;
  }

  public void setCustomerLastName(String customerLastName) {
    this.customerLastName = customerLastName;
  }

  public List<InvoiceItem> getInvoiceItems() {
    return invoiceItems;
  }

  public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
    this.invoiceItems = invoiceItems;
  }
}
