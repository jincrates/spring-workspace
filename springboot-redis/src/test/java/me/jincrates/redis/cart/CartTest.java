package me.jincrates.redis.cart;

import me.jincrates.redis.example.JedisHelper;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
    private static final String TESTER = "19940420";
    static JedisHelper helper;
    private Cart cart;

    @BeforeEach
    public void setUpBeforeClass() throws Exception {
        helper = JedisHelper.getInstance();
    }

    @AfterEach
    public void testDownAfterClass() throws  Exception {
        helper.destroyPool();
    }

    @BeforeEach
    public void setUp() throws Exception {
        // 사용자 번호가 19940420인 사용자의 장바구니 객체를 생성하고 정상인지 테스트한다.
        this.cart = new Cart(helper, TESTER);
        assertNotNull(this.cart);
    }

    @Test
    public void testAddProject() {
        // 장바구니 상품번호 151번인 아메리카도 1개를 추가하고 결과가 OK인지 테스트 한다.
        assertEquals("OK", this.cart.addProduct("151", "아메리카노", 1));
        assertEquals("OK", this.cart.addProduct("156", "카페라떼", 5));
    }

    @Test
    public void testGetProductList() {
        // 장바구니에 등록된 상품 목록을 조회하고 결과가 존재하는지 테스트한다.
        JSONArray products = this.cart.getProductList();
        System.out.println(products);
        assertNotNull(products);
        assertEquals(2, products.size());
    }

    @Test
    public void testDeleteProduct() {
        String[] products = {"151"};
        int result = this.cart.deleteProduct(products);
        assertEquals(1, result);
    }

    @Test
    public void testFlushCart() {
        // 장바구니 비우기를 테스트한다.
        assertTrue(this.cart.flushCart() > -1);
        assertTrue(this.cart.flushCartDeprecated() > -1);
    }
}