package renderer;

import model.Invoice;
import org.junit.jupiter.api.Test;
import util.InvoiceDataGenerator;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Hagen Trinter (h.trinter@micromata.de)
 */
public class InvoiceRendererTest {

  @Test
  public void render0Items() throws Exception {
    final byte[] invoiceFileContents = render(0);
    assertTrue(invoiceFileContents != null && invoiceFileContents.length > 20);
  }

  @Test
  public void render10Items() throws Exception {
    final byte[] invoiceFileContents = render(10);
    assertTrue(invoiceFileContents != null && invoiceFileContents.length > 20);
  }

  @Test
  public void render21Items() throws Exception {
    final byte[] invoiceFileContents = render(21);
    assertTrue(invoiceFileContents != null && invoiceFileContents.length > 20);
  }

//  public void renderLocal() throws Exception {
//    FileUtils.writeByteArrayToFile(new File("invoice1033.pdf"), render(1033));
//  }

  private byte[] render(int invoiceItemCount) throws IOException {
    final Invoice invoice = InvoiceDataGenerator.generate(invoiceItemCount);
    final InvoiceRenderer renderer = new InvoiceRenderer();
    return renderer.render(invoice);
  }

}
