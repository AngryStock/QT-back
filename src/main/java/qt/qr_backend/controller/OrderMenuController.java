package qt.qr_backend.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qt.qr_backend.DTO.OrderDTO;
import qt.qr_backend.DTO.OrderMenuDTO;
import qt.qr_backend.controller.response.OrderMenuResponse;
import qt.qr_backend.service.OrderMenuService;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/orderMenu")
public class OrderMenuController {

    private final OrderMenuService orderMenuService;

//    @PostMapping("/save")
//    public ResponseEntity<OrderMenuDTO> orderMenuSave(@RequestBody OrderMenuDTO orderMenuDTO){
//        log.info("start save orderMenu");
//        return ResponseEntity.ok(orderMenuService.saveOrderMenu(orderMenuDTO));
//    }
//    @PostMapping("/update")
//    public ResponseEntity<OrderMenuDTO> orderMenuUpdate(@RequestBody OrderMenuDTO orderMenuDTO){
//        return ResponseEntity.ok(orderMenuService.updateOrderMenu(orderMenuDTO));
//    }
    @GetMapping("/delete/{orderMenuId}")
    public OrderMenuResponse orderMenuDelete(@PathVariable String orderMenuId){
        log.info("start delete orderMenu");
        orderMenuService.deleteOrderMenu(orderMenuId);
        return new OrderMenuResponse(200,"delete ok");
    }

//    @GetMapping("/find/orderMenuId/{orderMenuId}")
//    public ResponseEntity<OrderMenuDTO> orderMenuFindById(@PathVariable String orderMenuId){
//        log.info("start findById orderMenu");
//        OrderMenuDTO orderMenuDTO = orderMenuService.findOrderMenuById(orderMenuId);
//        return ResponseEntity.ok(orderMenuDTO);
//    }
//    @GetMapping("/find/menuId/{menuId}")
//    public ResponseEntity<List<OrderMenuDTO>> orderMenuFindByMenuId(@PathVariable String menuId){
//        log.info("start findById orderMenu");
//        List<OrderMenuDTO> orderMenuListByMenuId = orderMenuService.findOrderMenuListByMenuId(menuId);
//        return ResponseEntity.ok(orderMenuListByMenuId);
//    }
//    @GetMapping("/find/orderId/{orderId}")
//    public ResponseEntity<List<OrderMenuDTO>> orderMenuFindByOrderId(@PathVariable String orderId){
//        log.info("start findById orderMenu");
//        List<OrderMenuDTO> orderMenuListByOrderId = orderMenuService.findOrderMenuListByOrderId(orderId);
//        return ResponseEntity.ok(orderMenuListByOrderId);
//    }

    @GetMapping("/findAll")
    public ResponseEntity<List<OrderMenuDTO>> orderMenuFindAll(){
        log.info("start findAll orderMenu");
        List<OrderMenuDTO> allOrderMenuDTO = orderMenuService.findAllOrderMenu();
        return ResponseEntity.ok(allOrderMenuDTO);
    }




}
