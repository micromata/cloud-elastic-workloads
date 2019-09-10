package util;

import model.Invoice;
import model.InvoiceItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Hagen Trinter (h.trinter@micromata.de)
 */
public class InvoiceDataGenerator {

  public static Invoice generate(final int invoiceItemCount) {
    final List<InvoiceItem> invoiceItems = new ArrayList<>();
    for (int i = 0; i < invoiceItemCount; i++) {
      invoiceItems.add(new InvoiceItem("Part Number " + UUID.randomUUID().toString().split("-")[0], BigDecimal.valueOf(Math.random() * 100)));
    }
    return new Invoice(generateInvoiceId(), "Jane", "Doe", invoiceItems);
  }

  public static String generateInvoiceId() {
    return UUID.randomUUID().toString().split("-")[0].toUpperCase();
  }

}
