package qt.qr_backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qt.qr_backend.DTO.CartDTO;
import qt.qr_backend.domain.Cart;
import qt.qr_backend.domain.Store;
import qt.qr_backend.repository.CartRepository;
import qt.qr_backend.repository.CartRepository;
import qt.qr_backend.repository.StoreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public CartDTO saveCart(CartDTO cartDTO){
        Cart cart = cartRepository.save(cartDTO.toCart());
        return CartDTO.fromCarttoCartDTO(cart);
    }
//    public CartDTO updateCart(CartDTO cartDTO){
//        Optional<Cart> targetCart = cartRepository.findById(cartDTO.getId());
//        if (targetCart.isPresent()){
//            if (cartDTO.getName()!=null){
//                targetCart.get().setName(cartDTO.getName());
//            }
//        }
//        Cart savedCart = cartRepository.save(targetCart.get());
//        return CartDTO.fromCarttoCartDTO(savedCart);
//    }


    public void deleteCart(String id){
        cartRepository.deleteById(id);
    }
    public CartDTO findCartById(String id){
        Optional<Cart> findedCart = cartRepository.findById(id);
        if(findedCart.isPresent()){
            return CartDTO.fromCarttoCartDTO(findedCart.get());
        }
        else throw new RuntimeException("cant find cart");
    }
    public List<CartDTO> findCartListByStoreIdAndTable(String storeId, String table){
        List<Cart> findedCartList = cartRepository.findByTableNoAndStoreId(table,storeId);
        return CartDTO.listFromCarttoCartDTO(findedCartList);
    }

    public CartDTO setCart(String cartId, int amount) {
        Optional<Cart> byId = cartRepository.findById(cartId);
        if (byId.isPresent()){
            byId.get().setAmount(amount);
            return CartDTO.fromCarttoCartDTO(cartRepository.save(byId.get()));
        }
        else throw new RuntimeException("cant find cart");
    }
    public void allDelCart(String storeId, String table){
        List<Cart> targetList = cartRepository.findByTableNoAndStoreId(storeId, table);
        cartRepository.deleteAll(targetList);
    }
}
