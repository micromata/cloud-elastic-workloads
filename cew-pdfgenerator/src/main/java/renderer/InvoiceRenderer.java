package renderer;

import model.Invoice;
import model.InvoiceItem;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.InvoiceDataGenerator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Hagen Trinter (h.trinter@micromata.de)
 */
public class InvoiceRenderer {

  private static final Logger LOG = LoggerFactory.getLogger(InvoiceRenderer.class);

  private static final int MAX_INVOICE_ITEMS_PER_PAGE = 20;
  private static final float FONT_SIZE = 14f;
  private static final float LINE_HEIGHT = 20f;

  private static final String LOCALE_LANG = "en";
  private static final String LOCALE_COUNTRY = "US";

  public byte[] render(final Invoice invoice) throws IOException {
    final Locale locale = new Locale(LOCALE_LANG, LOCALE_COUNTRY);
    final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);

    final List<InvoiceItem> invoiceItems = invoice.getInvoiceItems() != null ? invoice.getInvoiceItems() : new ArrayList<>();
    if (invoiceItems.size() == 0) {
      invoiceItems.add(new InvoiceItem("-- No invoice items -- ", BigDecimal.ZERO));
    }
    final List<List<InvoiceItem>> invoiceItemPages = ListUtils.partition(invoiceItems, MAX_INVOICE_ITEMS_PER_PAGE);

    final ByteArrayOutputStream bao = new ByteArrayOutputStream();

    final String invoiceId = StringUtils.isNotEmpty(invoice.getInvoiceId()) ? invoice.getInvoiceId() : InvoiceDataGenerator.generateInvoiceId();
    BigDecimal total = BigDecimal.ZERO;

    PDDocument doc = null;
    try {
      doc = new PDDocument();
      final PDFont font = getFont(doc);

      for (int pageIndex = 0; pageIndex < invoiceItemPages.size(); pageIndex++) {
        final List<InvoiceItem> invoiceItemPage = invoiceItemPages.get(pageIndex);

        final PDPage page = new PDPage();
        doc.addPage(page);

        final PDPageContentStream contentStream = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true, true);
        contentStream.setFont(font, FONT_SIZE);
        contentStream.setLeading(LINE_HEIGHT);

        contentStream.beginText();
        contentStream.newLineAtOffset(25, 700);

        renderLine("Tycho Manufacturing and Engineering Ltd.", font, contentStream);
        contentStream.newLine();
        contentStream.newLine();
        renderLine(String.format("Invoice ID %s", invoiceId), font, contentStream);
        renderLine(String.format("Billed to %s %s", invoice.getCustomerFirstName(), invoice.getCustomerLastName()), font, contentStream);
        contentStream.newLine();
        renderLine(StringUtils.leftPad(String.format("Page %s/%s", pageIndex + 1, invoiceItemPages.size()), 63), font, contentStream);
        contentStream.newLine();

        for (final InvoiceItem invoiceItem : invoiceItemPage) {
          final BigDecimal price = invoiceItem.getPrice() != null ? invoiceItem.getPrice() : BigDecimal.ZERO;
          total = total.add(price);

          renderLine(StringUtils.rightPad(StringUtils.abbreviate(invoiceItem.getItemDescription(), 40), 45) + StringUtils.leftPad(currencyFormat.format(price), 18), font, contentStream);
        }

        if (pageIndex + 1 == invoiceItemPages.size()) {
          contentStream.newLine();
          renderLine(StringUtils.rightPad("Total", 45) + StringUtils.leftPad(currencyFormat.format(total), 18), font, contentStream);
        }

        contentStream.endText();
        contentStream.close();
      }

      doc.save(bao);
    } catch (Exception e) {
      LOG.error("Error rendering invoice, id=" + invoiceId, e);
    } finally {
      if (doc != null) {
        doc.close();
      }
    }

    LOG.info("Invoice rendered, id=" + invoiceId);
    return bao.toByteArray();
  }

  private PDFont getFont(final PDDocument doc) throws IOException {
    final InputStream fontFileIs = InvoiceRenderer.class.getResourceAsStream("/IBMPlexMono-Regular.ttf");
    return PDType0Font.load(doc, fontFileIs);
  }

  private void renderLine(final String text, final PDFont font, final PDPageContentStream contentStream) throws IOException {
    // Replace characters that are not supported by the font with a "?".
    final StringBuilder cleanText = new StringBuilder();
    for (final char tchar : StringUtils.defaultString(text).toCharArray()) {
      try {
        font.encode(Character.toString(tchar));
        cleanText.append(tchar);
      } catch (IllegalArgumentException e) {
        cleanText.append("?");
      }
    }

    contentStream.showText(cleanText.toString());
    contentStream.newLine();
  }

}
