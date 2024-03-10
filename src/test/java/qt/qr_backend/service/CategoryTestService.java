package qt.qr_backend.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import qt.qr_backend.domain.Category;
import qt.qr_backend.domain.Ceo;
import qt.qr_backend.domain.Store;
import qt.qr_backend.domain.enums.Approval;
import qt.qr_backend.repository.CategoryRepository;

import java.util.List;

@DataJpaTest
public class CategoryTestService {

    @Autowired
    CategoryRepository categoryRepository;

    @BeforeEach
    void insertTestData(){
        Ceo ceo = new Ceo("testCeoName", "010-0000-0000", "testLoginId", true, "000-00-000000","1","test@gmail.com");
        Store store = new Store(ceo, "testStoreName","010-0000-0000" , "testMainAdr", "testDetailAdr", "11", 5, Approval.APPROVE);


        Category category = new Category();
        category.setName("111");
        category.setStore(store);
        categoryRepository.save(category);

        Category category2 = new Category();
        category.setName("222");
        category.setStore(store);
        categoryRepository.save(category);
        Category category3 = new Category();
        category.setName("333");
        category.setStore(store);
        categoryRepository.save(category);
        Category category4 = new Category();
        category.setName("444");
        category.setStore(store);
        categoryRepository.save(category);
    }

    @Test
    void findAllTest(){
        List<Category> categoryList = categoryRepository.findAll();
        for (Category category : categoryList) {
            System.out.println("category = " + category);
        }
    }


}
