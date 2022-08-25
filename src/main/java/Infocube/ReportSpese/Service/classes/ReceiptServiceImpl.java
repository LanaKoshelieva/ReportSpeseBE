package Infocube.ReportSpese.Service.classes;

import Infocube.ReportSpese.Command.FilterReceiptsCommand;
import Infocube.ReportSpese.Model.Receipt;
import Infocube.ReportSpese.Repository.IReceiptRepository;
import Infocube.ReportSpese.Service.interfaces.IReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptServiceImpl implements IReceiptService
{
    @PersistenceContext
    private EntityManager entityManager;

    private final IReceiptRepository receiptRepository;

    @Autowired
    public ReceiptServiceImpl(IReceiptRepository receiptRepository)
    {
        this.receiptRepository = receiptRepository;
    }


    @Override
    public List<Receipt> getReceiptsList()
    {
        return (List<Receipt>) receiptRepository.findAll();
    }

    @Override
    public Receipt createReceipt(Receipt newReceipt)
    {
        return receiptRepository.save(newReceipt);
    }

    @Override
    public Receipt updateReceipt(Receipt r, Integer id)
    {
        r.setCode(id);
        return receiptRepository.save(r);
    }

    @Override
    public void deleteReceipt(Integer id)
    {
        receiptRepository.deleteById(id);
    }

    @Override
    public Receipt getReceipt(Integer id)
    {
        return receiptRepository.findById(id).get();
    }

    @Override
    public List<Receipt> receiptsByHighestPrice()
    {
        return receiptRepository.findAll(Sort.by(Sort.Direction.DESC, "total"));
    }

    @Override
    public List<Receipt> receiptsByLowestPrice()
    {
        return receiptRepository.findAll(Sort.by(Sort.Direction.ASC, "total"));
    }

    @Override
    public List<Receipt> receiptsByOldest() {
        return receiptRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
    }

    @Override
    public List<Receipt> receiptsByNewest() {
        return receiptRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    @Override
    public List<Receipt> getUserReceipts(Integer id)
    {
        return receiptRepository.findByUserId(id);
    }

    @Override
    public List<Receipt> filtred(FilterReceiptsCommand command)
    {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Receipt> cr = cb.createQuery(Receipt.class);
        Root<Receipt> root = cr.from(Receipt.class);
        cr = cr.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if(command.getUserId() != null)
        {
            predicates.add(cb.equal(root.get("user"), command.getUserId()));
        }

        if(command.getCategory() != null)
        {
            predicates.add(cb.equal(root.get("category"), command.getCategory()));
        }

        if(command.getSeller() != null)
        {
            predicates.add(cb.equal(root.get("seller"), command.getSeller()));
        }

        if(command.getPaymentType() != null)
        {
            predicates.add(cb.equal(root.get("paymentType"), command.getPaymentType()));
        }

        if(command.getProduct() != null)
        {
            predicates.add(cb.like(root.get("product"), "%" + command.getProduct() + "%"));
        }

        if(command.getPriceMin() != null)
        {
            predicates.add(cb.ge(root.get("total"), command.getPriceMin()));
        }

        if(command.getPriceMax() != null)
        {
            predicates.add(cb.le(root.get("total"), command.getPriceMax()));
        }

        if(command.getDateFrom() != null)
        {
            predicates.add(cb.greaterThanOrEqualTo(root.get("date"), command.getDateFrom()));
        }

        if(command.getDateTo() != null)
        {
            predicates.add(cb.lessThanOrEqualTo(root.get("date"), command.getDateTo()));
        }

        if(predicates.size() > 0)
        {
            cr = cr.where(predicates.toArray(Predicate[]::new));
        }

        TypedQuery<Receipt> query = entityManager.createQuery(cr);
        List<Receipt> results = query.getResultList();
        return results;
    }

}
