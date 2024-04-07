package qt.qr_backend.controller;


import io.swagger.v3.core.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;
import qt.qr_backend.DTO.MessageDTO;
import qt.qr_backend.DTO.OrderDTO;
import qt.qr_backend.DTO.OrderMenuDTO;
import qt.qr_backend.DTO.OrderMenuRequest;
import qt.qr_backend.controller.response.OrderResponse;
import qt.qr_backend.service.OrderMenuService;
import qt.qr_backend.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
//@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderMenuService orderMenuService;
    private final SimpMessageSendingOperations messagingTemplate;

    @PostMapping("/order/save")
    public ResponseEntity<OrderDTO> orderSave(@RequestBody OrderDTO orderDTO){
        log.info("start save order");
        return ResponseEntity.ok(orderService.saveOrder(orderDTO));
    }
    @PostMapping("/order/update")
    public ResponseEntity<OrderDTO> orderUpdate(@RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(orderService.updateOrder(orderDTO));
    }

    @MessageMapping("/order/message/{orderId}")//고객이 사장에게 주문 정보 보내기/pub/order/message/ceo
    //이때 고객쪽에서 orderId를 보내주면 이걸 기반으로 Order를 만들어주자
    public void getOrderFromCustomer(List<OrderMenuRequest> list, @DestinationVariable String orderId){
        OrderDTO waitOrder = new OrderDTO(orderId,
                list.get(0).getMenuDTO().getCategory().getStore(),
                LocalDateTime.now(),
                "WAIT", 123123);
        List<OrderMenuDTO> orderMenuDTOList = orderMenuService.saveAllOrderMenu(list,waitOrder);
        messagingTemplate.convertAndSend("/sub/order/getOrder/storeId/"+waitOrder.getStoreDTO().getId(),orderMenuDTOList);
        //사장측 구독 url
    }
    @MessageMapping("/order/table/{tableId}")//고객이 사장에게 주문 정보 보내기/pub/order/message/ceo
    //이때 고객쪽에서 orderId를 보내주면 이걸 기반으로 Order를 만들어주자
    public void patchMenuToCustomer(MessageDTO message, @DestinationVariable String tableId){

        messagingTemplate.convertAndSend("/sub/order/table/"+tableId,message);
    }
    @MessageMapping("/order/orderOXmessage/{oxMessage}")//사장이 고객에게 주문 상태 보내기/pub/order/orderOXmessage
    public void orderOXMessageToCustomer(List<OrderMenuDTO> orderMenuDTOList,@DestinationVariable String oxMessage){
        orderMenuDTOList.get(0).getOrderDTO().changeStatus(oxMessage);
        OrderDTO orderDTO = orderService.updateOrder(orderMenuDTOList.get(0).getOrderDTO());
        messagingTemplate.convertAndSend("/sub/order/OXmessage/OrderId/"+orderDTO.getId(),orderDTO);
        //고객측 구독url
    }

    @GetMapping("/order/delete/{orderId}")
    public OrderResponse orderDelete(@PathVariable String orderId){
        log.info("start delete order");
        orderService.deleteOrder(orderId);
        return new OrderResponse(200,"delete ok");
    }

    @GetMapping("/order/find/orderId/{orderId}")
    public ResponseEntity<OrderDTO> orderFindById(@PathVariable String orderId){
        log.info("start findById order");
        OrderDTO orderDTO = orderService.findOrderById(orderId);
        return ResponseEntity.ok(orderDTO);
    }
    @GetMapping("/order/find/storeId/{storeId}")
    public ResponseEntity<List<OrderDTO>> orderFindByStoreId(@PathVariable String storeId){
        log.info("start findById order");
        List<OrderDTO> orderListByStoreId = orderService.findOrderListByStoreId(storeId);
        return ResponseEntity.ok(orderListByStoreId);
    }

    @GetMapping("/order/findAll")
    public ResponseEntity<List<OrderDTO>> orderFindAll(){
        log.info("start findAll order");
        List<OrderDTO> allOrderDTO = orderService.findAllOrder();
        return ResponseEntity.ok(allOrderDTO);
    }




}
