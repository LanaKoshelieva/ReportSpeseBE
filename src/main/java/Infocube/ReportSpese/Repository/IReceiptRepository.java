package Infocube.ReportSpese.Repository;

import Infocube.ReportSpese.Model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IReceiptRepository extends CrudRepository<Receipt, Integer>, JpaRepository<Receipt, Integer>
{
    List<Receipt> findByUserId(Integer id);


    @Query( value = "SELECT * FROM receipt r", nativeQuery = true)
    List<Receipt> userReceipts();

    @Query( value = "INSERT INTO receipt r (date, total, category, payment_type, seller, user) values (?1.date, ?1.total, ?1.category, ?1.payment_type, ?1.seller, ?1.user )", nativeQuery = true)
    Receipt createReceipt(Receipt r);

    @Query( value = "UPDATE receipt r SET r.date=?1.date, r.total=?1.total, r.category=?1.category, r.payment_type=?1.payment_type, r.seller=?1.seller, r.user=?1.user WHERE id= ?2", nativeQuery = true)
    Receipt updateReceipt(Receipt r, Integer id);

    @Query( value = "DELETE FROM receipt r WHERE id= ?1", nativeQuery = true)
    Receipt deleteReceipt(Integer id);

    //orders the 'total' column in descending order
    @Query( value = "SELECT * FROM receipt r ORDER BY total DESC", nativeQuery = true)
    List<Receipt> receiptsByHighestPrice();

    //orders the 'total' column in crescenting order
    @Query( value = "SELECT * FROM receipt r ORDER BY total ASC", nativeQuery = true)
    List<Receipt> receiptsByLowestPrice();

    @Query( value = "SELECT * FROM receipt r ORDER BY category", nativeQuery = true)
    List<Receipt> receiptsByCategory();

    @Query( value = "SELECT * FROM receipt r ORDER BY payment_type", nativeQuery = true)
    List<Receipt> receiptsByBaymentType();

    @Query( value = "SELECT * FROM receipt r ORDER BY seller", nativeQuery = true)
    List<Receipt> receiptsBySeller();

    @Query( value = "SELECT * FROM receipt r ORDER BY date ASC", nativeQuery = true)
    List<Receipt> receiptsByCrescDate();

    @Query( value = "SELECT * FROM receipt r ORDER BY date DESC", nativeQuery = true)
    List<Receipt> receiptsByDecrescDate();

}
