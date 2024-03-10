package qt.qr_backend.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qt.qr_backend.DTO.OrderDTO;
import qt.qr_backend.controller.response.OrderResponse;
import qt.qr_backend.service.OrderService;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<OrderDTO> orderSave(@RequestBody OrderDTO orderDTO){
        log.info("start save order");
        return ResponseEntity.ok(orderService.saveOrder(orderDTO));
    }
    @GetMapping("/delete/{orderId}")
    public OrderResponse orderDelete(@PathVariable String orderId){
        log.info("start delete order");
        orderService.deleteOrder(orderId);
        return new OrderResponse(200,"delete ok");
    }

    @GetMapping("/find/orderId/{orderId}")
    public ResponseEntity<OrderDTO> orderFindById(@PathVariable String orderId){
        log.info("start findById order");
        OrderDTO orderDTO = orderService.findOrderById(orderId);
        return ResponseEntity.ok(orderDTO);
    }
    @GetMapping("/find/storeId/{storeId}")
    public ResponseEntity<List<OrderDTO>> orderFindByStoreId(@PathVariable String storeId){
        log.info("start findById order");
        List<OrderDTO> orderListByStoreId = orderService.findOrderListByStoreId(storeId);
        return ResponseEntity.ok(orderListByStoreId);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<OrderDTO>> orderFindAll(){
        log.info("start findAll order");
        List<OrderDTO> allOrderDTO = orderService.findAllOrder();
        return ResponseEntity.ok(allOrderDTO);
    }




}
