package qt.qr_backend.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import qt.qr_backend.DTO.OrderMenuDTO;
import qt.qr_backend.DTO.OrderMenuOptionDTO;
import qt.qr_backend.DTO.OrderMenuRequest;
import qt.qr_backend.domain.exOrder.Order;
import qt.qr_backend.domain.exOrder.OrderMenu;
import qt.qr_backend.domain.exOrder.OrderMenuOption;
import qt.qr_backend.repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderMenuService {

    private final OrderMenuRepository orderMenuRepository;
    private final OrderRepository orderRepository;
    private final OrderMenuOptionRepository orderMenuOptionRepository;
    private final MenuRepository menuRepository;
    private final MenuOptionRepository menuOptionRepository;
    private final StoreRepository storeRepository;

//    public OrderMenuDTO saveOrderMenu(OrderMenuDTO orderMenuDTO){
//        OrderMenu orderMenu = orderMenuRepository.save(orderMenuDTO.toOrderMenu());
//        return OrderMenuDTO.fromOrderMenutoOrderMenuDTO(orderMenu);
//    }
//    public OrderMenuDTO updateOrderMenu(OrderMenuDTO orderMenuDTO){
//        Optional<OrderMenu> targetOrderMenu = orderMenuRepository.findById(orderMenuDTO.getId());
//        if(targetOrderMenu.isPresent()){
//            if (orderMenuDTO.getOrderMenuPrice()!=targetOrderMenu.get().getOrderMenuPrice()){
//                targetOrderMenu.get().setOrderMenuPrice(orderMenuDTO.getOrderMenuPrice());
//            }
//        }
//        OrderMenu savedOrderMenu = orderMenuRepository.save(targetOrderMenu.get());
//        return OrderMenuDTO.fromOrderMenutoOrderMenuDTO(savedOrderMenu);
//    }

//    public List<OrderMenuDTO> saveAllOrderMenu(List<OrderMenuRequest> orderMenuRequestList, String tableId, String orderId, String storeId){
//        int orderPrice = 0;
//        for (OrderMenuRequest orderMenuRequest : orderMenuRequestList) {
//            orderPrice += orderMenuRequest.getOrderMenuPrice();
//        }
//        Order savedOrder = orderRepository.save(new Order(storeId, LocalDateTime.now(), "WAIT", tableId,orderPrice));
//        List<OrderMenuDTO> orderMenuDTOList = orderMenuRequestList.stream().map(l -> new OrderMenuDTO(savedOrder.getId(), l.getMenuId(), l.getOrderMenuPrice())).toList();
//        List<OrderMenuDTO> orderMenuDTOS = OrderMenuDTO.listFromOrderMenutoOrderMenuDTO(orderMenuRepository.saveAll(orderMenuDTOList.stream().map(l -> new OrderMenu()).toList()));
//        List<OrderMenuOptionDTO> orderMenuOptionDTOList = new ArrayList<>();
//
//        for (int i=0;i<orderMenuDTOS.size();i++){
//            List<OrderMenuOptionDTO> list = orderMenuRequestList.get(i).getOrderMenuOptionDTOList();
//            for (OrderMenuOptionDTO orderMenuOptionDTO : list) {
//                orderMenuOptionDTOList.add(new OrderMenuOptionDTO(orderMenuDTOS.get(i).getId(), orderMenuOptionDTO.getMenuOptionId()));
//            }
//        }
//        orderMenuOptionRepository.saveAll(orderMenuOptionDTOList.stream().map(l -> new OrderMenuOption(null, orderMenuRepository.getReferenceById(l.getOrderMenuId()), menuOptionRepository.getReferenceById(l.getMenuOptionId()))).toList());
//        return orderMenuDTOS;
//    }

    public void deleteOrderMenu(String id){
        orderMenuRepository.deleteById(id);
    }
//    public OrderMenuDTO findOrderMenuById(String id){
//        Optional<OrderMenu> findedOrderMenu = orderMenuRepository.findById(id);
//        OrderMenu orderMenu = new OrderMenu();
//        if(findedOrderMenu.isPresent()){
//            orderMenu.setId(findedOrderMenu.get().getId());
//            orderMenu.setOrder(findedOrderMenu.get().getOrder());
//            orderMenu.setMenu(findedOrderMenu.get().getMenu());
//            orderMenu.setOrderMenuPrice(findedOrderMenu.get().getOrderMenuPrice());
//        }
//        return OrderMenuDTO.fromOrderMenutoOrderMenuDTO(orderMenu);
//    }
//    public List<OrderMenuDTO> findOrderMenuListByOrderId(String id){
//        List<OrderMenu> findedOrderMenuByOrderId = orderMenuRepository.findByOrder_Id(id);
//        return OrderMenuDTO.listFromOrderMenutoOrderMenuDTO(findedOrderMenuByOrderId);
//    }
//    public List<OrderMenuDTO> findOrderMenuListByMenuId(String id){
//        List<OrderMenu> findedOrderMenuByMenuId = orderMenuRepository.findByMenu_Id(id);
//        return OrderMenuDTO.listFromOrderMenutoOrderMenuDTO(findedOrderMenuByMenuId);
//    }
    public List<OrderMenuDTO> findAllOrderMenu(){
        List<OrderMenu> findAllOrderMenu = orderMenuRepository.findAll();
        return OrderMenuDTO.listFromOrderMenutoOrderMenuDTO(findAllOrderMenu);
    }

}
