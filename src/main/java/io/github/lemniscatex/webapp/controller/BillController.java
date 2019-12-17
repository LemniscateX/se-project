package io.github.lemniscatex.webapp.controller;

import io.github.lemniscatex.webapp.model.Bill;
import io.github.lemniscatex.webapp.model.Product;
import io.github.lemniscatex.webapp.repository.BillRepository;
import io.github.lemniscatex.webapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BillController {
    Integer currentProductId = null;
    Integer currentUserId = null;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/admin/bills")
    public String getAllBills(Model model) {
        model.addAttribute("bills", billRepository.findAll());
        return "admin-bills";
    }

    @PostMapping("/admin/bill/ship")
    public String shipBill(@RequestParam(name = "id") Integer id) {
        Bill bill = billRepository.getBillById(id);
        bill.setStatus("shipped");
        billRepository.save(bill);
        return "redirect:/admin/bills";
    }

    @PostMapping("/user/makeBill")
    public String makeBill(@RequestParam(name = "productId") Integer productId,
                           @RequestParam(name = "userId") Integer userId) {
        currentProductId = productId;
        currentUserId = userId;
        return "user-submit-bill";
    }

    @PostMapping("/user/submitBill")
    public String submitBill(@RequestParam(name = "productCount") Integer productCount,
                             @RequestParam(name = "receiverName") String receiverName,
                             @RequestParam(name = "receiverTel") String receiverTel,
                             @RequestParam(name = "receiverAddress") String receiverAddress) {
        Product p = productRepository.getProductById(currentProductId);
        Double productPrice = p.price;
        Double totalPrice = productCount * productPrice;
        String status = "submitted";
        Bill bill = new Bill(p, productPrice, productCount, totalPrice, status, currentUserId,
                receiverName, receiverTel, receiverAddress);
        billRepository.save(bill);
        return "user-submit-success";
    }

    @PostMapping("/user/bill/receive")
    public String receiveBill(@RequestParam(name = "id") Integer id) {
        Bill bill = billRepository.getBillById(id);
        bill.setStatus("finished");
        billRepository.save(bill);
        return "redirect:/user/bills";
    }
}
