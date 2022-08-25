package Infocube.ReportSpese.Controller;

import Infocube.ReportSpese.Command.FilterReceiptsCommand;
import Infocube.ReportSpese.Command.ReceiptCommand;
import Infocube.ReportSpese.DTO.ReceiptDTO;
import Infocube.ReportSpese.Model.Receipt;
import Infocube.ReportSpese.Model.User;
import Infocube.ReportSpese.Service.interfaces.IAuthService;
import Infocube.ReportSpese.Service.interfaces.IReceiptService;
import Infocube.ReportSpese.Service.interfaces.IUserService;
import Infocube.ReportSpese.Utility.HTTPUtility;
import Infocube.ReportSpese.Utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(HTTPUtility.CONTROLLER_ROOT)
public class ReceiptController
{
    private final IReceiptService receiptService;
    private final IUserService userService;
    private final IAuthService authService;

    @Autowired
    public ReceiptController(IReceiptService receiptService, IUserService userService, IAuthService authService)
    {
        this.receiptService = receiptService;
        this.userService = userService;
        this.authService = authService;
    }


    @GetMapping(HTTPUtility.CONTROLLER_ARCHIVE)
    public Response allReceipts(@RequestHeader(name = "userMail", required = false)  String userMail, @RequestHeader(name = "userPassword", required = false)  String userPassword)
    {
        try
        {
            authService.checkUser(userMail, userPassword, null);
            List<Receipt>  receiptsFromDB = receiptService.getReceiptsList();
            List<ReceiptDTO> allReceipts = new ArrayList<>();

            for (int i = 0; i < receiptsFromDB.size(); i++)
            {
                allReceipts.add(new ReceiptDTO(receiptsFromDB.get(i)));
            }
            return new Response(allReceipts);
        }
        catch (Exception e)
        {
            return new Response(e);
        }
    }

    @PostMapping(HTTPUtility.CONTROLLER_NEW_RECEIPT)
    public Response createReceipt(@Valid @RequestBody ReceiptCommand command,
                                  @RequestHeader(name = "userMail", required = false)  String userMail,
                                  @RequestHeader(name = "userPassword", required = false)  String userPassword)
    {
        try
        {
            authService.checkUser(userMail, userPassword, command.getUserId());
            Receipt newReceipt = new Receipt(command);

            User u = userService.getUser(command.getUserId());
            newReceipt.setUser(u);

            Receipt r = receiptService.createReceipt(newReceipt);
            return new Response(new ReceiptDTO(r));
        }
        catch (Exception e)
        {
            return new Response(e);
        }
    }

    @PutMapping(HTTPUtility.CONTROLLER_UPDATE_RECEIPT)
    public Response updateReceipt(@Valid @RequestBody ReceiptCommand command,
                                  @RequestHeader(name = "userMail", required = false)  String userMail,
                                  @RequestHeader(name = "userPassword", required = false)  String userPassword)
    {
        return createReceipt(command, userMail, userPassword);
    }

    @DeleteMapping(HTTPUtility.CONTROLLER_DELETE_RECEIPT)
    public Response deleteReceipt(@PathVariable Integer id,
                                  @RequestHeader(name = "userMail", required = false)  String userMail,
                                  @RequestHeader(name = "userPassword", required = false)  String userPassword)
    {
        try
        {
            Receipt r = receiptService.getReceipt(id);
            authService.checkUser(userMail, userPassword, r.getUser().getId());
            receiptService.deleteReceipt(id);
            return new Response("Scontrino cancellato", 200, null);
        }
        catch (Exception e)
        {
            return new Response(e);
        }
    }

    @GetMapping(HTTPUtility.CONTROLLER_GET_RECEIPT)
    public Response getReceipt(@PathVariable Integer id,
                               @RequestHeader(name = "userMail", required = false)  String userMail,
                               @RequestHeader(name = "userPassword", required = false)  String userPassword)
    {
        try
        {
            Receipt r = receiptService.getReceipt(id);
            authService.checkUser(userMail, userPassword, r.getUser().getId());
            return new Response(new ReceiptDTO(r));
        }
        catch (Exception e)
        {
            return new Response(e);
        }
    }

    @GetMapping(HTTPUtility.CONTROLLER_ALL_RECEIPTS)
    public Response getUserReceipts(@PathVariable Integer id,
                                    @RequestHeader(name = "userMail", required = false)  String userMail,
                                    @RequestHeader(name = "userPassword", required = false)  String userPassword)
    {
        try
        {
            authService.checkUser(userMail, userPassword, id);
            List<Receipt> userReceiptsDB = receiptService.getUserReceipts(id);
            List<ReceiptDTO> userReceipts = new ArrayList<>();

            for (int i = 0; i< userReceiptsDB.size(); i++)
            {
                userReceipts.add(new ReceiptDTO(userReceiptsDB.get(i)));
            }

            return new Response(userReceipts);
        }
        catch (Exception e)
        {
            return new Response(e);
        }
    }

    @PostMapping(HTTPUtility.CONTROLLER_ARCHIVE + "/filtered")
    public Response getSpecificReceipt(@RequestBody FilterReceiptsCommand command,
                                       @RequestHeader(name = "userMail", required = false)  String userMail,
                                       @RequestHeader(name = "userPassword", required = false)  String userPassword)
    {
        try
        {
            authService.checkUser(userMail, userPassword, command.getUserId());
            List<Receipt> receiptsFromDB = receiptService.filtred(command);
            List<ReceiptDTO> specificReceipts = new ArrayList<>();

            for (int i = 0; i < receiptsFromDB.size(); i++)
            {
                specificReceipts.add(new ReceiptDTO(receiptsFromDB.get(i)));
            }
            return new Response(specificReceipts);
        }
        catch (Exception e)
        {
            return new Response(e);
        }
    }

}
