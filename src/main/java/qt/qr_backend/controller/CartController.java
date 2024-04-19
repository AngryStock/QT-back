package qt.qr_backend.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;
import qt.qr_backend.DTO.CartDTO;
import qt.qr_backend.DTO.MessageDTO;
import qt.qr_backend.controller.request.CartRequest;
import qt.qr_backend.controller.response.CartResponse;
import qt.qr_backend.service.CartService;

import java.util.List;
import java.util.Objects;


@Slf4j
@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final SimpMessageSendingOperations messagingTemplate;


    @PostMapping("/cart/save")
    public ResponseEntity<CartDTO> cartSave(@RequestBody CartDTO cartDTO){
        log.info("start save cart");
        return ResponseEntity.ok(cartService.saveCart(cartDTO));
    }

    @MessageMapping("/cart/table/{storeId}/{table}")
    public void patchMenuToCustomer(CartRequest request, @DestinationVariable String storeId, @DestinationVariable String table){
        log.info("start patchMenuTo Customer");
        if (Objects.equals(request.getType(), "add")){
            CartDTO cartDTO = cartService.saveCart(request.getCartDTO());
            messagingTemplate.convertAndSend("/sub/cart/table/"+storeId+"/"+table,new CartRequest("add",cartDTO));
        } else if (Objects.equals(request.getType(), "set")) {
            CartDTO cartDTO = cartService.setCart(request.getCartId(), request.getAmount());
            messagingTemplate.convertAndSend("/sub/cart/table/"+storeId+"/"+table,new CartRequest("set",request.getCartId(),cartDTO.getAmount()));
        } else if (Objects.equals(request.getType(), "del")){
            cartService.deleteCart(request.getCartId());
            messagingTemplate.convertAndSend("/sub/cart/table/"+storeId+"/"+table,new CartRequest("del", request.getCartId()));
        } else if (Objects.equals(request.getType(), "allDel")){
            cartService.allDelCart(storeId,table);
            messagingTemplate.convertAndSend("/sub/cart/table/"+storeId+"/"+table,new CartRequest("allDel"));
        }
        else throw new RuntimeException("error in patchMenuToCustomer");
    }

//    @PostMapping("/update")
//    public ResponseEntity<CartDTO> cartUpdate(@RequestBody CartDTO cartDTO){
//        return ResponseEntity.ok(cartService.updateCart(cartDTO));
//    }
    @GetMapping("/cart/delete/{cartId}")
    public CartResponse cartDelete(@PathVariable String cartId){
        log.info("start delete cart");
        cartService.deleteCart(cartId);
        return new CartResponse(200,"delete ok");
    }


    @GetMapping("/cart/find/cartId/{cartId}")
    public ResponseEntity<CartDTO> cartFindById(@PathVariable String cartId){
        log.info("start findById cart");
        CartDTO cartDTO = cartService.findCartById(cartId);
        return ResponseEntity.ok(cartDTO);
    }
    @GetMapping("/cart/find/storeId/{storeId}/table/{table}")
    public ResponseEntity<List<CartDTO>> cartFindByStoreId(@PathVariable String storeId, @PathVariable String table){
        log.info("start findBy cart storeId and table");
        List<CartDTO> cartList = cartService.findCartListByStoreIdAndTable(storeId,table);
        return ResponseEntity.ok(cartList);
    }

//    @GetMapping("/findAll")
//    public ResponseEntity<List<CartDTO>> cartFindAll(){
//        log.info("start findAll cart");
//        List<CartDTO> allCartDTO = cartService.findAllCart();
//        return ResponseEntity.ok(allCartDTO);
//    }




}
