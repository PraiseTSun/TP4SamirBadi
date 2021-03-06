package javatown.controllers;

import javatown.DTO.*;
import javatown.service.AdminService;
import javatown.service.ClientService;
import javatown.service.EmployeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/react")
@CrossOrigin(origins = "http://localhost:3000/")
public class ReactController {
    private AdminService adminService;
    private ClientService clientService;
    private EmployeService employeService;

    public ReactController(AdminService adminService, ClientService clientService, EmployeService employeService) {
        this.adminService = adminService;
        this.clientService = clientService;
        this.employeService = employeService;
    }

    @GetMapping("/documents")
    public List<AbstractDocumentFormDTO> getDocuments(){
        return clientService.getDocuments();
    }

    @GetMapping("/documents_title_{value}")
    public List<AbstractDocumentFormDTO> getDocumentsByName(@PathVariable String value){
        return clientService.getDocumentsByTitle(value);
    }

    @GetMapping("/documents_author_{value}")
    public List<AbstractDocumentFormDTO> getDocumentsByAuthor(@PathVariable String value){
        return clientService.getDocumentsByAuthor(value);
    }

    @GetMapping("/documents_editor_{value}")
    public List<AbstractDocumentFormDTO> getDocumentsByEditor(@PathVariable String value){
        return clientService.getDocumentsByEditor(value);
    }

    @GetMapping("/documents_genre_{value}")
    public List<AbstractDocumentFormDTO> getDocumentsByGenre(@PathVariable String value){
        return clientService.getDocumentsByGenre(value);
    }

    @GetMapping("/documents_year_{value}")
    public List<AbstractDocumentFormDTO> getDocumentsByYear(@PathVariable String value){
        return clientService.getDocumentsByYear(value);
    }

    @GetMapping("/create_book_{title}_{author}_{editor}_{genre}_{year}_{page}")
    public AbstractDocumentFormDTO createBook(
            @PathVariable String title,
            @PathVariable String author,
            @PathVariable String editor,
            @PathVariable String genre,
            @PathVariable String year,
            @PathVariable Integer page
    ){
        return employeService.createBook(title, author, editor, year, page, genre);
    }

    @GetMapping("/create_cd_{title}_{author}_{editor}_{genre}_{year}")
    public AbstractDocumentFormDTO createCD(
            @PathVariable String title,
            @PathVariable String author,
            @PathVariable String editor,
            @PathVariable String genre,
            @PathVariable String year
    ){
        return employeService.createCD(title, author, editor, year, genre);
    }

    @GetMapping("/create_dvd_{title}_{author}_{editor}_{genre}_{year}")
    public AbstractDocumentFormDTO createDVD(
            @PathVariable String title,
            @PathVariable String author,
            @PathVariable String editor,
            @PathVariable String genre,
            @PathVariable String year
    ){
        return employeService.createDVD(title, author, editor, year, genre);
    }

    @GetMapping("/loans/{id}")
    public List<LoanFormDTO> getLoansOfMonth(@PathVariable int id){
        return adminService.getLoansOfMonth(id);
    }

    @GetMapping("/loan/{client}_{id}")
    public LoanFormDTO setLoan(@PathVariable String client, @PathVariable String id){
        return employeService.createLoan(client, id);
    }

    @GetMapping("/debts/{id}")
    public List<DebtFormDTO> getDebtOfMonth(@PathVariable Integer id){
        return adminService.getDebtsOfMonth(id);
    }

    @GetMapping("/clients")
    public List<ClientFormDTO> getClients(){
        return employeService.getClients();
    }

    @GetMapping("/client_{id}")
    public ClientFormDTO getClient(@PathVariable String id ){
        return clientService.getClientById(id);
    }

    @GetMapping("/client/{firstName}_{lastName}_{password}")
    public ClientFormDTO getClient(@PathVariable String firstName, @PathVariable String lastName, @PathVariable String password){
        return clientService.getClientByPassword(firstName,lastName, password);
    }

    @GetMapping("/create_client_{firstName}_{lastName}_{password}_{resident}")
    public ClientFormDTO createClient(
            @PathVariable String firstName,
            @PathVariable String lastName,
            @PathVariable String password,
            @PathVariable String resident){
        return clientService.createClient(firstName,lastName, password, resident);
    }

    @GetMapping("/return/{client}_{doc}")
    public DebtFormDTO returnDocument(@PathVariable String client, @PathVariable String doc){
        return employeService.returnDocument(client, doc);
    }

    @GetMapping("/pay/{client}_{debt}")
    public DebtFormDTO payDebt(@PathVariable String client, @PathVariable String debt){
        return employeService.payeDebt(client, debt);
    }

    @GetMapping("/create_employe_{firstName}_{lastName}_{password}")
    public EmployeeFormDTO createEmploye(
            @PathVariable String firstName,
            @PathVariable String lastName,
            @PathVariable String password){
        return employeService.createEmploye(firstName, lastName, password);
    }

    @GetMapping("/create_admin_{firstName}_{lastName}_{password}")
    public AdminFormDTO createAdmin(
            @PathVariable String firstName,
            @PathVariable String lastName,
            @PathVariable String password){
        return adminService.createAdmin(firstName, lastName, password);
    }
}
