package javatown.controllers;

import javatown.DTO.*;
import javatown.modele.DVD;
import javatown.service.AdminService;
import javatown.service.ClientService;
import javatown.service.EmployeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RootController {
    Logger logger = LoggerFactory.getLogger(RootController.class);

    private ClientService clientService;
    private EmployeService employeService;
    private AdminService adminService;

    public RootController(ClientService clientService, EmployeService employeService, AdminService adminService) {
        this.clientService = clientService;
        this.employeService = employeService;
        this.adminService = adminService;
    }

    @GetMapping("/")
    public String getRootRequest(Model model){
        model.addAttribute("pageTitle", "TP3 Samir Badi");
        model.addAttribute("mainTitle", "Users Sections");
        return "index";
    }

    @GetMapping("/client")
    public String getClient(Model model){
        model.addAttribute("clientTitle", "Client Section");
        return "client";
    }

    @GetMapping("/clientcreate")
    public String getClientCreate(@ModelAttribute ClientFormDTO clientForm, Model model, RedirectAttributes redirectAttributes){
        clientForm = new ClientFormDTO();
        model.addAttribute("clientForm", clientForm);
        return "clientedit";
    }

    @PostMapping("/clientcreate")
    public String clientPost(@ModelAttribute ClientFormDTO clientForm, Model model, BindingResult errors, RedirectAttributes redirectAttributes){
        logger.info("client:" + clientForm);
        clientService.createClient(clientForm.toModel());
        redirectAttributes.addFlashAttribute("clientForm", clientForm);
        return "clientresult";
    }

    @GetMapping("/clientconnectloan")
    public String getClientConnectLoans(@ModelAttribute ClientFormDTO clientForm, Model model, RedirectAttributes redirectAttributes){
        clientForm = new ClientFormDTO();
        model.addAttribute("clientForm", clientForm);
        return "clientconnectloan";
    }

    @PostMapping("/clientconnectloan")
    public String clientConnectLoans(@ModelAttribute ClientFormDTO clientForm, Model model, BindingResult errors, RedirectAttributes redirectAttributes){
        logger.info("client:" + clientForm);
        clientForm = clientService.getClientByPassword(clientForm);
        model.addAttribute("loans", clientForm.getLoans());
        return "clientloan";
    }

    @GetMapping("/clientconnectdebt")
    public String getClientConnectDebts(@ModelAttribute ClientFormDTO clientForm, Model model, RedirectAttributes redirectAttributes){
        clientForm = new ClientFormDTO();
        model.addAttribute("clientForm", clientForm);
        return "clientconnectdebt";
    }

    @PostMapping("/clientconnectdebt")
    public String clientConnectDebts(@ModelAttribute ClientFormDTO clientForm, Model model, BindingResult errors, RedirectAttributes redirectAttributes){
        logger.info("client:" + clientForm);
        clientForm = clientService.getClientByPassword(clientForm);
        redirectAttributes.addFlashAttribute("clientForm", clientForm);
        return "clientdebt";
    }

    @GetMapping("/employee")
    public String getEmployee(Model model){
        model.addAttribute("employeeTitle", "Employee Section");
        return "employee";
    }

    @GetMapping("/bookcreate")
    public String getBookCreate (@ModelAttribute BookFormDTO bookForm, Model model, RedirectAttributes redirectAttributes) {
        bookForm = new BookFormDTO();
        model.addAttribute("bookForm", bookForm);
        return "bookedit";
    }

    @PostMapping("/bookcreate")
    public String bookPost(@ModelAttribute BookFormDTO bookForm, Model model, BindingResult errors, RedirectAttributes redirectAttributes){
        logger.info("book:" + bookForm);
        employeService.createBook(bookForm.toModel());
        redirectAttributes.addFlashAttribute("bookForm", bookForm);
        return "bookresult";
    }

    @GetMapping("/cdcreate")
    public String getCdCreate(@ModelAttribute CDFormDTO cdForm, Model model, RedirectAttributes redirectAttributes){
        cdForm = new CDFormDTO();
        model.addAttribute("cdForm", cdForm);
        return "cdedit";
    }

    @PostMapping("/cdcreate")
    public String cdPost(@ModelAttribute CDFormDTO cdForm, Model model, BindingResult errors, RedirectAttributes redirectAttributes){
        logger.info("cd:" + cdForm);
        employeService.createCD(cdForm.toCD());
        redirectAttributes.addFlashAttribute("cdForm", cdForm);
        return "cdresult";
    }

    @GetMapping("/dvdcreate")
    public String getDvdCreate(@ModelAttribute DVDFormDTO dvdForm, Model model, RedirectAttributes redirectAttributes){
        dvdForm = new DVDFormDTO(new DVD());
        model.addAttribute("dvdForm", dvdForm);
        return "dvdedit";
    }

    @PostMapping("/dvdcreate")
    public String dvdPost(@ModelAttribute DVDFormDTO dvdForm, Model model, BindingResult errors, RedirectAttributes redirectAttributes){
        logger.info("dvd:" + dvdForm);
        employeService.createDVD(dvdForm.toDVD());
        redirectAttributes.addFlashAttribute("dvdForm", dvdForm);
        return "dvdresult";
    }

    @GetMapping("/loancreate")
    public String getLoanCreate(@ModelAttribute CreateLoanFormDTO loanDTO, Model model, RedirectAttributes redirectAttributes){
        loanDTO = new CreateLoanFormDTO();
        model.addAttribute("loanForm", loanDTO);
        return "loanedit";
    }

    @PostMapping("/loancreate")
    public String loanPost(@ModelAttribute CreateLoanFormDTO loanDTO, Model model, BindingResult errors, RedirectAttributes redirectAttributes){
        logger.info("loan:" + loanDTO);
        LoanFormDTO loanFormDTO = employeService.createLoan(loanDTO);
        redirectAttributes.addFlashAttribute("loanForm", loanFormDTO);
        return "loanresult";
    }

    @GetMapping("/admin")
    public String getAdmin(Model model){
        model.addAttribute("adminTitle", "Administrator Section");
        return "administrator";
    }

    @GetMapping("/notimplemented")
    public String getNotImplemented(Model model){
        model.addAttribute("title", "Function is not implemented yet.");
        return "notimplemented";
    }
}
