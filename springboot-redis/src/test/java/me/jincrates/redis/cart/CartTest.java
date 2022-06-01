package me.jincrates.redis.cart;

import me.jincrates.redis.example.JedisHelper;
import org.json.simple.JSONArray;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
    private static final String TESTER = "12521";
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
        this.cart = new Cart(helper, TESTER);
        assertNotNull(this.cart);
    }

    @Test
    public void testAddProject() {
        assertEquals("OK", this.cart.addProduct("151", "아메리카노", 1));
        assertEquals("OK", this.cart.addProduct("156", "카페라떼", 5));
    }

    @Test
    public void testGetProductList() {
        JSONArray products = this.cart.getProductList();
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
        assertTrue(this.cart.flushCart() > -1);
        assertTrue(this.cart.flushCartDeprecated() > -1);
    }
}