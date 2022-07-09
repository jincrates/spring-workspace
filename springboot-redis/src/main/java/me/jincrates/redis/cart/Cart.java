package me.jincrates.redis.cart;

import me.jincrates.redis.example.JedisHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import redis.clients.jedis.Jedis;

import java.util.Set;

public class Cart {
    private final Jedis jedis;
    private final JSONObject cartInfo;
    private final String userNo;
    private static final String KEY_CART_LIST = ":cart:product";
    private static final String KEY_CART_PRODUCT = ":cart:productid:";
    private static final String JSON_PRODUCT_LIST = "products";
    private static final int EXPIRE = 60 * 60 * 24 * 3; //만료기간 3일

    /**
     * 장바구니를 처리하기 위한 Cart 클래스의 생성자
     * @param helper 레디스 헬퍼 객체
     * @param userNo 유저번호
     */
    // 1. 지정된 사용자에 대한 장바구니 객체를 생성한다.
    public Cart(JedisHelper helper, String userNo) {
        this.jedis = helper.getConnection();
        this.userNo = userNo;
        this.cartInfo = getCartInfo();  // 2. 사용자의 Cart 객체가 생성될 때 레디스에 저장된 상품번호 목록을 조회한다.
    }

    /**
     * 레디스에 저장된 장바구니 정보를 조회하여 JSON 객체로 변환한다.
     * @return 장바구니 정보가 저장된 JSONObject
     */
    private JSONObject getCartInfo() {
        String productInfo = this.jedis.get(this.userNo + KEY_CART_LIST);

        // 3. 저장된 상품번호 목록이 없으면 빈 장바구니 객체를 생성한다.
        if (productInfo == null || productInfo.equals("")) {
            return makeEmptyCart();
        }

        try {
            JSONParser parser = new JSONParser();
            return (JSONObject)  parser.parse(productInfo);
        }
        catch (ParseException e) {
            // 4. 상품번호 목록에 오류가 있을 때 빈 장바구니 객체를 생성한다.
            return makeEmptyCart();
        }
    }

    /**
     * 장바구니가 존재하지 않는 사용자를 위한 빈 장바구니 정보를 생성한다.
     * @return 빈 장바구니 정보
     */
    private JSONObject makeEmptyCart() {
        JSONObject cart = new JSONObject();
        cart.put(JSON_PRODUCT_LIST, new JSONArray());
        return cart;
    }

    /**
     * 장바구니에 저장된 상품을 삭제한다.
     * @return 삭제된 상품개수
     */
    public int flushCart() {
        JSONArray products = (JSONArray) this.cartInfo.get(JSON_PRODUCT_LIST);
        for (int i = 0; i < products.size(); i++) {
            // 5. 장바구니에 저장된 상품 목록을 모두 삭제한다.
            this.jedis.del(this.userNo + KEY_CART_PRODUCT + products.get(i));
        }

        this.jedis.set(this.userNo + KEY_CART_LIST, "");
        return products.size();
    }

    /**
     * 장바구니에 상품을 추가한다.
     * @param productNo 장바구니에 추가할 상품번호
     * @param productName 장바구니에 추가할 상품명
     * @param quantity 상품개수
     * @return 장바구니 등록 결과
     */
    public String addProduct(String productNo, String productName, int quantity) {
        JSONArray products = (JSONArray) this.cartInfo.get(JSON_PRODUCT_LIST);
        products.add(productNo);

        // 6. 추가되는 상품의 아이디를 상품 목록에 추가하고 레디스에 저장한다.
        this.jedis.set(this.userNo + KEY_CART_LIST, this.cartInfo.toJSONString());

        JSONObject product = new JSONObject();
        product.put("productNo", productNo);
        product.put("productName", productName);
        product.put("quantity", quantity);

        String productKey = this.userNo + KEY_CART_PRODUCT + productNo;

        // 7. 개별 상품의 정보를 레디스에 저장하고 3일의 만료기간을 설정한다.
        // 3일이 지나면 레디스에서 해당 키가 자동으로 사라진다.
        return this.jedis.setex(productKey, EXPIRE, product.toJSONString());
    }

    /**
     * 장바구니에 저장된 상품 정보를 삭제한다.
     * @param productNo 삭제할 상품번호 목록
     * @return 삭제된 상품개수
     */
    public int deleteProduct(String[] productNo) {
        JSONArray products = (JSONArray) this.cartInfo.get(JSON_PRODUCT_LIST);
        int result = 0;

        for (String item : productNo) {
            products.remove(item);
            // 8. 저장된 상품번호 목록 개수만큼 레디스에 삭제 명령을 전송한다.
            result += this.jedis.del(this.userNo + KEY_CART_PRODUCT + item);
        }

        this.jedis.set(this.userNo + KEY_CART_LIST, this.cartInfo.toJSONString());
        return result;
    }

    /**
     * 장바구니에 저장된 상품 목록 JSONArray 형식으로 조회한다.
     * @return 조회된 상품 목록
     */
    public JSONArray getProductList() {
        //이 메서드에서는 장바구니에 저장된 상품 목록만큼 레디스 조회 요청이 발생한다.
        //단일 명령어를 여러 번 나누어 전송하는 것은 불필요한 네트워크 왕복 시간이 포함되기 때문에
        //프로그램의 빠른 응답을 기대할 수 없다.
        boolean isChanged = false;
        JSONArray products = (JSONArray) this.cartInfo.get(JSON_PRODUCT_LIST);
        JSONArray result = new JSONArray();
        String value = null;

        for (int i = 0, max = products.size(); i < max; i++) {
            // 9. 저장된 상품번호 목록 개수만큼 레디스에 조회 명령을 전송한다.
            value = this.jedis.get(this.userNo + KEY_CART_PRODUCT + products.get(i));

            if (value == null) {
                isChanged = false;
            } else {
                result.add(value);
            }
        }

        // 10. 상품이 저장된 키가 만료되면 레디스에서 자동으로 사라지므로 조회된 값이 존재하지 않는다.
        // 그러므로 상품 번호 목록을 갱신한다.
        if (isChanged) {
            this.jedis.set(this.userNo + KEY_CART_LIST, this.cartInfo.toJSONString());  //10
        }

        return result;
    }

    /**
     * keys 명령을 사용하여 장바구니에 저장된 상품을 삭제한다.
     * @return 삭제된 상품개수
     * @deprecated  keys 명령을 사용한 잘못된 구현이다.
     */
    // 11. 레디스와 서비스 코드에서 사용하지 말아야하는 keys 명령을 사용하여 장바구니 삭제를 구현한 예다.
    public int flushCartDeprecated() {
        Set<String> keys = this.jedis.keys(this.userNo + KEY_CART_PRODUCT + "*");
        for (String key : keys) {
            this.jedis.del(key);
        }

        this.jedis.set(this.userNo + KEY_CART_LIST, "");
        return keys.size();
    }
}
