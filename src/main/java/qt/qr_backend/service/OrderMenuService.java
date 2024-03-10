package qt.qr_backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qt.qr_backend.DTO.OrderMenuDTO;
import qt.qr_backend.domain.OrderMenu;
import qt.qr_backend.repository.OrderMenuRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderMenuService {

    private final OrderMenuRepository orderMenuRepository;

    public OrderMenuDTO saveOrderMenu(OrderMenuDTO orderMenuDTO){
        OrderMenu orderMenu = orderMenuRepository.save(orderMenuDTO.toOrderMenu());
        return OrderMenuDTO.fromOrderMenutoOrderMenuDTO(orderMenu);
    }

    public void deleteOrderMenu(String id){
        orderMenuRepository.deleteById(id);
    }
    public OrderMenuDTO findOrderMenuById(String id){
        Optional<OrderMenu> findedOrderMenu = orderMenuRepository.findById(id);
        OrderMenu orderMenu = new OrderMenu();
        if(findedOrderMenu.isPresent()){
            orderMenu.setId(findedOrderMenu.get().getId());
            orderMenu.setOrder(findedOrderMenu.get().getOrder());
            orderMenu.setMenu(findedOrderMenu.get().getMenu());
            orderMenu.setOrderPrice(findedOrderMenu.get().getOrderPrice());
            orderMenu.setCount(findedOrderMenu.get().getCount());
        }
        return OrderMenuDTO.fromOrderMenutoOrderMenuDTO(orderMenu);
    }
    public List<OrderMenuDTO> findOrderMenuListByOrderId(String id){
        List<OrderMenu> findedOrderMenuByOrderId = orderMenuRepository.findByOrder_Id(id);
        return OrderMenuDTO.listFromOrderMenutoOrderMenuDTO(findedOrderMenuByOrderId);
    }
    public List<OrderMenuDTO> findOrderMenuListByMenuId(String id){
        List<OrderMenu> findedOrderMenuByMenuId = orderMenuRepository.findByMenu_Id(id);
        return OrderMenuDTO.listFromOrderMenutoOrderMenuDTO(findedOrderMenuByMenuId);
    }
    public List<OrderMenuDTO> findAllOrderMenu(){
        List<OrderMenu> findAllOrderMenu = orderMenuRepository.findAll();
        return OrderMenuDTO.listFromOrderMenutoOrderMenuDTO(findAllOrderMenu);
    }

}
