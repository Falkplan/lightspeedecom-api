package com.lightspeedhq.ecom;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.google.common.collect.ImmutableMap;
import com.lightspeedhq.ecom.domain.*;
import com.lightspeedhq.ecom.test.InSequence;
import com.lightspeedhq.ecom.test.InSequenceRunner;
import com.lightspeedhq.ecom.test.mockbin.Bin;
import com.lightspeedhq.ecom.test.mockbin.Log;
import com.lightspeedhq.ecom.test.mockbin.MockbinClient;
import com.lightspeedhq.ecom.test.mockbin.MockbinFactory;
import feign.RetryableException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.TimeZone;
import org.aeonbits.owner.ConfigFactory;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 *
 * @author stevensnoeijen
 */
@RunWith(InSequenceRunner.class)
public class LightspeedEComClientTest {

    private final LightspeedEComClient lightspeedEComClient;

    /**
     * used to temp save webhookId
     */
    private static int createdWebhookId;

    /**
     * client for logging and reading webhook updates
     */
    private final MockbinClient mockbinClient;

    /**
     * Url created for listening to webhook updates.
     */
    private static String binId;

    public static final LightspeedEComTestConfig config = ConfigFactory.create(LightspeedEComTestConfig.class);

    public LightspeedEComClientTest() {
        lightspeedEComClient = createClient(true);
        mockbinClient = MockbinFactory.create();
    }

    public static LightspeedEComClient createClient(boolean force) {
        LightspeedEComClient lightspeedEComClient = LightspeedEComClient.builder()
                .cluster(config.getCluster())
                .language(config.getLanguage())
                .authorisation(config.getApiKey(), config.getApiSecret())
                .force(force)
                .build();
        return lightspeedEComClient;
    }

    @Test
    @InSequence(0)
    public void authorization() {
        //do request
        lightspeedEComClient.getProducts(ImmutableMap.of("page", 1, "limit", 50));
    }

    @Test(expected = LightspeedEComErrorException.class)
    @InSequence(1)
    public void error() {
        lightspeedEComClient.getCustomer(0);
    }

    @Test
    @InSequence(2)
    public void getProduct() {
        Product product = lightspeedEComClient.getProduct(36249620);
        assertNotNull(product);
    }

    @Test
    @InSequence(3)
    public void getProducts() {
        Product.List products = lightspeedEComClient.getProducts(ImmutableMap.of("page", 1, "limit", 50));
        assertNotNull(products);
        assertEquals(50, products.size());
    }

    @Test
    @InSequence(4)
    public void getProductCount() {
        Count count = lightspeedEComClient.getProductCount();
        assertNotNull(count);
    }

    @Test
    @InSequence(5)
    public void getProductAttributes() {
        ProductAttribute.List productAttribute = lightspeedEComClient.getProductAttributes(36249620);
        assertNotNull(productAttribute);
    }

    @Test
    @InSequence(6)
    public void getTypeAttributes() {
        TypesAttribute.List typesAttributes = lightspeedEComClient.getTypeAttributes(ImmutableMap.of("attribute", 268385));
        assertNotNull(typesAttributes);
    }

    @Test
    @InSequence(7)
    public void getCustomer() {
        Customer customer = lightspeedEComClient.getCustomer(22543580);
        assertNotNull(customer);
    }

    @Test
    @InSequence(8)
    public void getAttribute() {
        Attribute attribute = lightspeedEComClient.getAttribute(268385);
        assertNotNull(attribute);
    }

    @Test
    @InSequence(9)
    public void getType() {
        Type type = lightspeedEComClient.getType(95132);
        assertNotNull(type);
    }

    @Test
    @InSequence(10)
    public void getAccountRatelimit() {
        AccountRatelimit accountRatelimit = lightspeedEComClient.getAccountRatelimit();
        assertNotNull(accountRatelimit);
    }

    @Test
    @InSequence(11)
    public void getDeliverydate() {
        Deliverydate deliverydate = lightspeedEComClient.getDeliverydate(56579);
        assertNotNull(deliverydate);
    }

    @Test
    @InSequence(12)
    public void getBrand() {
        Brand brand = lightspeedEComClient.getBrand(1037039);
        assertNotNull(brand);
    }

    @Test
    @InSequence(13)
    public void getSupplier() {
        Supplier supplier = lightspeedEComClient.getSupplier(323213);
        assertNotNull(supplier);
    }

    @Test
    @InSequence(14)
    public void getWebhooks() {
        Webhook.List webhooks = lightspeedEComClient.getWebhooks();
        assertNotNull(webhooks);
    }

    @Test
    @InSequence(15)
    public void getWebhooksCount() {
        Count body = lightspeedEComClient.getWebhooksCount();
        assertNotNull(body);
    }

    @Test
    @InSequence(16)
    public void getLanguages() {
        Language.List languages = lightspeedEComClient.getLanguages();
        assertNotNull(languages);
    }

    @Test
    @InSequence(17)
    public void createWebhook() throws InterruptedException {
        //create bin for logging hook updates
        Bin bin = new Bin();
        bin.setStatus(200);
        bin.setStatusText("OK");
        bin.setHttpVersion("HTTP/1.1");
        bin.getContent().setMimeType("application/json");
        binId = mockbinClient.create(bin);
        String binUrl = MockbinFactory.getBinUrl(binId);
        System.out.println("created url for webhook test: " + binUrl);
        Thread.sleep(2000);

        //create webhook
        Webhook webhook = new Webhook();
        webhook.setActive(true);
        webhook.setItemGroup(Webhook.ItemGroup.CUSTOMERS);
        webhook.setItemAction(Webhook.ItemAction.UPDATED);
        webhook.setLanguage(lightspeedEComClient.getLanguages().get(0));
        webhook.setFormat(Webhook.Format.JSON);
        webhook.setAddress(binUrl);

        webhook = lightspeedEComClient.createWebhook(webhook);
        assertNotNull(webhook);
        createdWebhookId = webhook.getId();
    }

    @Test
    @InSequence(18)
    public void getWebhook() {
        Webhook webhook = lightspeedEComClient.getWebhook(createdWebhookId);
        assertNotNull(webhook);
    }

    @Test
    @InSequence(19)
    public void updateCustomer() {
        Customer customer = lightspeedEComClient.getCustomer(22543580);
        customer.setMemo("tested at " + LocalDateTime.now());
        customer = lightspeedEComClient.updateCustomer(22543580, customer);
        assertNotNull(customer);
    }

    @Test(timeout = 60000)//10min
    @InSequence(20)
    public void webhookCalled() throws InterruptedException, IOException {
        //webhook can take a few minutes to post update
        while (true) {
            System.out.println("LightspeedEcomClientTest.webhookCalled() retry in 10sec");
            Thread.sleep(10000);//wait 10 sec
            //get log
            Log log = mockbinClient.log(binId).getLog();
            //check is log extry is done, else retry in 10 sec
            if (!log.getEntries().isEmpty()) {
                assertEquals(1, log.getEntries().size());
                Log.Entry entry = log.getEntries().get(0);
                Log.Entry.Request request = entry.getRequest();
                String eventString = request.findHeader(LightspeedEComClient.HEADER_EVENT);
                Webhook.EventDescriptor eventDescriptor = Webhook.EventDescriptor.fromString(eventString);
                //check webhook event type
                assertEquals(Webhook.ItemGroup.CUSTOMERS, eventDescriptor.getGroup());
                assertEquals(Webhook.ItemAction.UPDATED, eventDescriptor.getAction());

                String text = request.getPostData().getText();

                ObjectMapper om = new ObjectMapper();
                om.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
                om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
                om.registerModule(new JSR310Module());
                om.setTimeZone(TimeZone.getTimeZone("UTC"));

                //get actual content of the update
                Customer customer = om.readValue(text, Customer.class);
                assertEquals(20782625, customer.getId());
                break;
            }
        }
    }

    @Test
    @InSequence(21)
    public void updateWebhook() {
        Webhook webhook = lightspeedEComClient.getWebhook(createdWebhookId);
        webhook.setActive(false);
        webhook = lightspeedEComClient.updateWebhook(createdWebhookId, webhook);
        assertNotNull(webhook);
    }

    @Test
    @InSequence(22)
    public void deleteWebhook() {
        lightspeedEComClient.deleteWebhook(createdWebhookId);
    }

    @Test
    @InSequence(23)
    public void createCustomer() {
        Customer customer = Customer.create("steven@no-spam.ws", "Steven", "X");
        customer = lightspeedEComClient.createCustomer(customer);
        assertNotNull(customer);
    }

    @Test
    @InSequence(24)
    public void getCustomers_email() {
        Customer.List customers = lightspeedEComClient.getCustomers(ImmutableMap.of("email", "steven@no-spam.ws"));
        assertEquals(1, customers.size());
    }

    @Test
    @InSequence(25)
    public void removeCustomer() {
        Customer customer = lightspeedEComClient.getCustomers(ImmutableMap.of("email", "steven@no-spam.ws")).get(0);
        lightspeedEComClient.removeCustomer(customer.getId());
    }

    @Test
    @InSequence(26)
    public void getOrders_customer() {
        Order.List orders = lightspeedEComClient.getOrders(ImmutableMap.of("customer", 20782625));
        assertNotNull(orders);
    }

    @Test
    @InSequence(27)
    public void getCategories() {
        Category.List categories = lightspeedEComClient.getCategories();
        assertNotNull(categories);
    }

    @Test
    @InSequence(28)
    public void getCategoryCount() {
        Count body = lightspeedEComClient.getCategoryCount();
        assertNotNull(body);
    }

    @Test
    @InSequence(29)
    public void getCategory() {
        Category customer = lightspeedEComClient.getCategory(4147181);
        assertNotNull(customer);
    }

    @Test
    @InSequence(30)
    public void getCategories_parent() {
        Category.List categories = lightspeedEComClient.getCategories(ImmutableMap.of("parent", 4147181));
        assertNotNull(categories);
    }

    @Test
    @InSequence(31)
    public void getCategoriesProducts() {
        CategoriesProduct.List categoriesProducts = lightspeedEComClient.getCategoriesProducts();
        assertNotNull(categoriesProducts);
    }

    @Test
    @InSequence(32)
    public void getCategoriesProductsByCategory() {
        CategoriesProduct.List categoriesProducts = lightspeedEComClient.getCategoriesProducts(ImmutableMap.of("category", 4145276));
        assertNotNull(categoriesProducts);
    }

    @Test
    @InSequence(33)
    public void getCategoriesProducts_product() {
        CategoriesProduct.List categoriesProducts = lightspeedEComClient.getCategoriesProducts(ImmutableMap.of("product", 36482948));
        assertNotNull(categoriesProducts);
    }

    @Test
    @InSequence(34)
    public void getFilters() {
        Filter.List filters = lightspeedEComClient.getFilters();
        assertNotNull(filters);
    }

    @Test
    @InSequence(35)
    public void getFilter() {
        Filter filter = lightspeedEComClient.getFilter(33428);
        assertNotNull(filter);
    }

    @Test
    @InSequence(36)
    public void getFilterValues() {
        FilterValue.List filterValues = lightspeedEComClient.getFilterValues(33428);
        assertNotNull(filterValues);
    }

    @Test
    @InSequence(37)
    public void getFilterValue() {
        FilterValue filterValue = lightspeedEComClient.getFilterValue(33419, 196997);
        assertNotNull(filterValue);
    }

    @Test
    @InSequence(38)
    public void getProductFilterValues() {
        ProductFilterValue.List productFilterValues = lightspeedEComClient.getProductFilterValues(36483518);
        assertNotNull(productFilterValues);
    }

    @Test
    @InSequence(39)
    public void createCustomerToken() {
        CustomerToken customerToken = lightspeedEComClient.createCustomerToken(22543580, CustomerToken.create("https://www.google.com/", 60));
        assertNotNull(customerToken);
    }

    @Test
    @InSequence(40)
    public void getCategoriesProductsCountByCategory() {
        Count body = lightspeedEComClient.getCategoriesProductsCount(ImmutableMap.of("category-id", 4145276));
        assertNotNull(body);
    }

    @Test
    @InSequence(41)
    public void getCustomers() {
        Customer.List customers = lightspeedEComClient.getCustomers(Collections.emptyMap());
        assertNotNull(customers);
    }

    @Test
    @InSequence(42)
    public void getVariants() {
        Variant.List variants = lightspeedEComClient.getVariants(Collections.emptyMap());
        assertNotNull(variants);
    }

    @Test
    @InSequence(43)
    public void getVariants_product() {
        Variant.List variants = lightspeedEComClient.getVariants(ImmutableMap.of("product", 36483425));
        assertNotNull(variants);
    }

    @Test
    @InSequence(44)
    public void getBrands() {
        Brand.List brands = lightspeedEComClient.getBrands(Collections.emptyMap());
        assertNotNull(brands);
    }

    @Test
    @InSequence(45)
    public void getQuote() {
        Quote quote = lightspeedEComClient.getQuote(116506229);
        assertNotNull(quote);
    }

    @Test
    @InSequence(46)
    public void getQuotes() {
        Quote.List quotes = lightspeedEComClient.getQuotes(Collections.emptyMap());
        assertNotNull(quotes);
    }

    /**
     * Update country with the {@link Customer#setAddressBillingCountry(java.lang.String)}
     * and {@link Customer#setAddressShippingCountry(java.lang.String)}.
     */
    @Test
    @InSequence(47)
    public void updateCustomer_countryString() {
        Customer customer = lightspeedEComClient.getCustomer(22543580);
        customer.setAddressBillingCountry("BE");
        customer = lightspeedEComClient.updateCustomer(customer.getId(), customer);
        assertEquals(21, customer.getAddressBillingCountry().getId());//belgium id
        //change back
        customer.setAddressBillingCountry("NL");
        customer = lightspeedEComClient.updateCustomer(customer.getId(), customer);
    }

    @Test(expected = LimitException.class)
    @InSequence(98)
    public void limit() {
        LightspeedEComClient client = LightspeedEComClient.builder()
                .cluster(config.getCluster())
                .language(config.getLanguage())
                .authorisation(config.getApiKey(), config.getApiSecret())
                .limit(100, 2)
                .build();
        //call 2 requests
        client.getWebhooksCount();
        client.getWebhooksCount();
        client.getWebhooksCount();//third time must throw exception
    }

    @Test
    @InSequence(99)
    public void forceLimit() throws InterruptedException {
        LightspeedEComClient client = LightspeedEComClient.builder()
                .cluster(config.getCluster())
                .language(config.getLanguage())
                .authorisation(config.getApiKey(), config.getApiSecret())
                .force(true)
                .limit(0, 1)
                .build();
        Thread thread = new Thread(() -> {
            client.getWebhooksCount();
            client.getWebhooksCount();//must wait
        });
        thread.start();
        Thread.sleep(1000);//let other thread run
        assertEquals(Thread.State.TIMED_WAITING, thread.getState());
    }

    @Test(expected = RetryableException.class)
    @InSequence(100)
    public void toManyRequests_error() {
        //force to exception
        for (int i = 300; i >= 0; i--) {
            lightspeedEComClient.getProductCount();
        }
    }

    @Test
    @InSequence(101)
    public void toManyRequests_force() {
        LightspeedEComClient lightspeedEComClient = createClient(true);
        lightspeedEComClient.getProductCount();//should not exception (but will take some time)
    }

    @Test(expected = RetryableException.class)
    @InSequence(102)
    public void limitError() {
        LightspeedEComClient client = LightspeedEComClient.builder()
                .cluster(config.getCluster())
                .language(config.getLanguage())
                .authorisation(config.getApiKey(), config.getApiSecret())
                .limit(100, 100)
                .build();
        client.getProductCount();
        //TODO: check if limiter is updated
    }

}
