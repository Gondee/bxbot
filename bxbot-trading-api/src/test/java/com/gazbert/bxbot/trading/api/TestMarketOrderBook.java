/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Gareth Jon Lynch
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.gazbert.bxbot.trading.api;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests a Market Order Book behaves as expected.
 *
 * @author gazbert
 */
public class TestMarketOrderBook {

    private static final String MARKET_ID = "BTC_USD";

    private List<MarketOrder> sellOrders;
    private List<MarketOrder> buyOrders;

    private static final BigDecimal ORDER_1_PRICE = new BigDecimal("111.11");
    private static final BigDecimal ORDER_1_QUANTITY = new BigDecimal("0.01614453");
    private static final BigDecimal ORDER_1_TOTAL = ORDER_1_PRICE.multiply(ORDER_1_QUANTITY);

    private static final BigDecimal ORDER_2_PRICE = new BigDecimal("222.22");
    private static final BigDecimal ORDER_2_QUANTITY = new BigDecimal("0.02423424");
    private static final BigDecimal ORDER_2_TOTAL = ORDER_2_PRICE.multiply(ORDER_2_QUANTITY);

    private static final BigDecimal ORDER_3_PRICE = new BigDecimal("333.33");
    private static final BigDecimal ORDER_3_QUANTITY = new BigDecimal("0.03435344");
    private static final BigDecimal ORDER_3_TOTAL = ORDER_3_PRICE.multiply(ORDER_3_QUANTITY);

    private MarketOrder sellOrder1;
    private MarketOrder sellOrder2;
    private MarketOrder sellOrder3;

    private MarketOrder buyOrder1;
    private MarketOrder buyOrder2;
    private MarketOrder buyOrder3;


    @Before
    public void setupOrdersBeforeEachTest() {

        sellOrder1 = new MarketOrder(OrderType.SELL, ORDER_1_PRICE, ORDER_1_QUANTITY, ORDER_1_TOTAL);
        sellOrder2 = new MarketOrder(OrderType.SELL, ORDER_2_PRICE, ORDER_2_QUANTITY, ORDER_2_TOTAL);
        sellOrder3 = new MarketOrder(OrderType.SELL, ORDER_3_PRICE, ORDER_3_QUANTITY, ORDER_3_TOTAL);

        sellOrders = new ArrayList<>();
        sellOrders.add(sellOrder1);
        sellOrders.add(sellOrder2);
        sellOrders.add(sellOrder3);

        buyOrder1 = new MarketOrder(OrderType.BUY, ORDER_1_PRICE, ORDER_1_QUANTITY, ORDER_1_TOTAL);
        buyOrder2 = new MarketOrder(OrderType.BUY, ORDER_2_PRICE, ORDER_2_QUANTITY, ORDER_2_TOTAL);
        buyOrder3 = new MarketOrder(OrderType.BUY, ORDER_3_PRICE, ORDER_3_QUANTITY, ORDER_3_TOTAL);

        buyOrders = new ArrayList<>();
        buyOrders.add(buyOrder1);
        buyOrders.add(buyOrder2);
        buyOrders.add(buyOrder3);
    }

    @Test
    public void testMarketOrderBookIsInitialisedAsExpected() {

        final MarketOrderBook marketOrderBook = new MarketOrderBook(MARKET_ID, sellOrders, buyOrders);
        assertEquals(MARKET_ID, marketOrderBook.getMarketId());

        assertEquals(sellOrders, marketOrderBook.getSellOrders());
        assertEquals(3, sellOrders.size());
        assertTrue(sellOrders.contains(sellOrder1));
        assertTrue(sellOrders.contains(sellOrder2));
        assertTrue(sellOrders.contains(sellOrder3));

        assertEquals(buyOrders, marketOrderBook.getBuyOrders());
        assertEquals(3, buyOrders.size());
        assertTrue(buyOrders.contains(buyOrder1));
        assertTrue(buyOrders.contains(buyOrder2));
        assertTrue(buyOrders.contains(buyOrder3));
    }

    @Test
    public void testSettersWorkAsExpected() {

        final MarketOrderBook marketOrderBook = new MarketOrderBook(null, null, null);
        assertEquals(null, marketOrderBook.getMarketId());
        assertEquals(null, marketOrderBook.getSellOrders());
        assertEquals(null, marketOrderBook.getSellOrders());

        marketOrderBook.setMarketId(MARKET_ID);
        assertEquals(MARKET_ID, marketOrderBook.getMarketId());

        marketOrderBook.setSellOrders(sellOrders);
        assertEquals(sellOrders, marketOrderBook.getSellOrders());

        marketOrderBook.setBuyOrders(buyOrders);
        assertEquals(buyOrders, marketOrderBook.getBuyOrders());
    }
}
