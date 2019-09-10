package model;

import java.math.BigDecimal;

/**
 * @author Hagen Trinter (h.trinter@micromata.de)
 */
public class InvoiceItem {

  private String itemDescription = "";
  private BigDecimal price = BigDecimal.ZERO;

  public InvoiceItem() {
  }

  public InvoiceItem(String itemDescription, BigDecimal price) {
    this.itemDescription = itemDescription;
    this.price = price;
  }

  public String getItemDescription() {
    return itemDescription;
  }

  public void setItemDescription(String itemDescription) {
    this.itemDescription = itemDescription;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

}
