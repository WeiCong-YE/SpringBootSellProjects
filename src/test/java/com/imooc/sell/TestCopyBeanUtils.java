package com.imooc.sell;


import com.imooc.sell.dto.CartDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestCopyBeanUtils {

    @Test
    public void copyBean() {
        CartDto cartDto = new CartDto(null, 121);
        testBean test1 = new testBean();
        test1.setProductId("1212");
        com.imooc.sell.utils.BeanUtils.copyNonNullProperties(cartDto, test1);
        log.info(test1.toString());
    }


    class testBean {
        private String productId;
        private Integer productQuantity;
        private String testKey1;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public Integer getProductQuantity() {
            return productQuantity;
        }

        public void setProductQuantity(Integer productQuantity) {
            this.productQuantity = productQuantity;
        }

        public String getTestKey1() {
            return testKey1;
        }

        public void setTestKey1(String testKey1) {
            this.testKey1 = testKey1;
        }

        @Override
        public String toString() {
            return "testBean{" +
                    "productId='" + productId + '\'' +
                    ", productQuantity=" + productQuantity +
                    ", testKey1='" + testKey1 + '\'' +
                    '}';
        }
    }
}
