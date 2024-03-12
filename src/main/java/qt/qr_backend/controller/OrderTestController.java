package qt.qr_backend.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderTestController {
    //Test

    @GetMapping("/orderTest/customer")
    public String customer(Model model){
        return "customer";
    }
    @GetMapping("/orderTest/ceo")
    public String ceo(Model model){
        return "ceo";
    }

}
