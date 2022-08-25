package Infocube.ReportSpese.Service.interfaces;
import Infocube.ReportSpese.Command.FilterReceiptsCommand;
import Infocube.ReportSpese.Model.Receipt;
import java.util.List;

public interface IReceiptService
{
    List<Receipt> getReceiptsList();
    Receipt createReceipt(Receipt receipt);

    Receipt updateReceipt(Receipt r, Integer id);

    void deleteReceipt(Integer id);

    Receipt getReceipt(Integer id);

    List<Receipt> receiptsByHighestPrice();

    List<Receipt> receiptsByLowestPrice();

    List<Receipt> receiptsByNewest();

    List<Receipt> receiptsByOldest();

    List<Receipt> getUserReceipts(Integer id);

    List<Receipt> filtred(FilterReceiptsCommand command);

}
