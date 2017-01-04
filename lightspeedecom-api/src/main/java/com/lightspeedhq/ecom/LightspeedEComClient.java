package com.lightspeedhq.ecom;

import com.lightspeedhq.ecom.domain.*;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import java.util.Collections;
import java.util.Map;

/**
 * Lightspeed eCom offers a powerful set of API’s for developers to create awesome apps or single shop connections.
 * The API provides developers the interface to connect with third party software such as accounting-, feedback-,
 * e-mailmarketing- and inventory management-software, or extend with new features that interact with our core platform,
 * such as loyalty programs, social-sharing programs or reporting tools. <br><br>
 *
 * <u>All methods can throw (runtimeexception) {@link LightspeedEComErrorException} when an error occured.</u><br>
 * <u>Every request has a {@link #DEFAULT_PAGE} and {@link #DEFAULT_LIMIT} (and max {@link #MAX_LIMIT}) that can be set with the filter.</u><br>
 * All other filters that are id's should be set by the objects name without -id.
 *
 * @see <a href="http://developers.seoshop.com/api/">http://developers.seoshop.com/api/</a>
 * @author stevensnoeijen
 */
public interface LightspeedEComClient {

    public static final String CLUSTER_US1 = "api.shoplightspeed.com";
    public static final String CLUSTER_EU1 = "api.webshopapp.com";

    /**
     * Default datetime format for entities.<br>
     * In ISO-8601 format.
     */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * Filter get results page
     */
    public static final int DEFAULT_PAGE = 1;

    /**
     * Filter get results limit
     */
    public static final int DEFAULT_LIMIT = 50;

    /**
     * Filter get max results
     */
    public static final int MAX_LIMIT = 250;

    /**
     * The shop ID
     */
    public static String HEADER_SHOP_ID = "X-Shop-Id";
    /**
     * The cluster that has sent the webhook (us1 or eu1).
     */
    public static String HEADER_CLUSTER_ID = "X-Cluster-Id";
    /**
     * Will contain "{@link ItemGroup}/{@link ItemAction}"
     */
    public static String HEADER_EVENT = "X-Event";
    /**
     * Format of the payload (JSON|XML).
     *
     * @see Webhook.Format
     */
    public static String HEADER_FORMAT = "X-Format";
    /**
     * ISO 639-1 code of a language (two letters).
     */
    public static String HEADER_LANGUAGE = "X-Language";
    public static String HEADER_REQUEST_ID = "X-Request-Id";

    public static final String HEADER_RATELIMIT_LIMIT = "X-RateLimit-Limit";
    public static final String HEADER_RATELIMIT_REMAINING = "X-RateLimit-Remaining";
    public static final String HEADER_RATELIMIT_RESET = "X-RateLimit-Reset";

    /**
     * Retrieve the rate limits for the active API key used to perform the request.
     *
     * @return {@link com.lightspeedhq.ecom.domain.AccountRatelimit}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/accountratelimit">http://developers.seoshop.com/api/resources/accountratelimit</a>
     */
    @RequestLine("GET /account/ratelimit.json")
    public AccountRatelimit getAccountRatelimit();

    /**
     * Retrieve a single {@link com.lightspeedhq.ecom.domain.Attribute}.
     *
     * @param attributeId unique numeric identifier for the {@link com.lightspeedhq.ecom.domain.Attribute}.
     *
     * @return {@link com.lightspeedhq.ecom.domain.Attribute}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/attribute">http://developers.seoshop.com/api/resources/attribute</a>
     */
    @RequestLine("GET /attributes/{attribute-id}.json")
    public Attribute getAttribute(@Param("attribute-id") int attributeId);

    /**
     * Retrieve a single {@link com.lightspeedhq.ecom.domain.Brand} based on the unique identifier.
     *
     * @param brandId The unique numeric identifier for the {@link com.lightspeedhq.ecom.domain.Brand}.
     *
     * @return {@link com.lightspeedhq.ecom.domain.Brand}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/brand">http://developers.seoshop.com/api/resources/brand</a>
     */
    @RequestLine("GET /brands/{brand-id}.json")
    public Brand getBrand(@Param("brand-id") int brandId);

    /**
     * Retrieve all the brands from this shop.
     *
     * @param filters
     *
     * @return list of {@link com.lightspeedhq.ecom.domain.Filster}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/brand">http://developers.seoshop.com/api/resources/brand</a>
     */
    @RequestLine("GET /brands.json")
    public Brand.List getBrands(@QueryMap Map<String, Object> filters);

    /**
     * Retrieve all the brands from this shop.
     *
     * @return list of {@link com.lightspeedhq.ecom.domain.Filster}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/brand">http://developers.seoshop.com/api/resources/brand</a>
     */
    @RequestLine("GET /brands.json")
    public Brand.List getBrands();

    /**
     * Retrieve all the categories of a shop.
     *
     * @param filters as 'parent'
     *
     * @return list of {@link com.lightspeedhq.ecom.domain.Category}s
     *
     * @see <a href="http://developers.seoshop.com/api/resources/category">http://developers.seoshop.com/api/resources/category</a>
     */
    @RequestLine("GET /categories.json")
    public Category.List getCategories(@QueryMap Map<String, Object> filters);

    /**
     * {@link #getCategories(java.util.Map) } without filters.
     *
     * @return list of {@link com.lightspeedhq.ecom.domain.Category}s
     */
    public default Category.List getCategories() {
        return getCategories(Collections.emptyMap());
    }

    /**
     * Retrieve the total number of categories.
     *
     * @return count of categories
     *
     * @see <a href="http://developers.seoshop.com/api/resources/category">http://developers.seoshop.com/api/resources/category</a>
     */
    @RequestLine("GET /categories/count.json")
    public Count getCategoryCount();

    /**
     * Retrieve a single category.
     *
     * @param categoryId The unique numeric identifier for the category.
     *
     * @return {@link com.lightspeedhq.ecom.domain.Category}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/category">http://developers.seoshop.com/api/resources/category</a>
     */
    @RequestLine("GET /categories/{category-id}.json")
    public Category getCategory(@Param("category-id") int categoryId);

    /**
     * Retrieve all the products with their associated category.
     *
     * @param filters as 'page', 'limit', 'category', 'product' or 'parent'
     *
     * @return {@link com.lightspeedhq.ecom.domain.CategoriesProduct}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/categoriesproduct">http://developers.seoshop.com/api/resources/categoriesproduct</a>
     */
    @RequestLine("GET /categories/products.json")
    public CategoriesProduct.List getCategoriesProducts(@QueryMap Map<String, Object> filters);

    /**
     * {@link #getCategoriesProducts(java.util.Map) } without filter.
     *
     * @return {@link com.lightspeedhq.ecom.domain.CategoriesProduct}
     */
    public default CategoriesProduct.List getCategoriesProducts() {
        return getCategoriesProducts(Collections.emptyMap());
    }

    /**
     * Retrieve the number by category available in the shop.
     *
     * @param filters as category-id
     *
     * @return count of filters
     *
     * @see <a href="http://developers.lightspeedhq.com/ecom/endpoints/categoryproduct/">http://developers.lightspeedhq.com/ecom/endpoints/categoryproduct/</a>
     */
    @RequestLine("GET /categories/products/count.json")
    public Count getCategoriesProductsCount(@QueryMap Map<String, Object> filters);

    /**
     * Retrieve the number by category available in the shop.
     *
     * @return count of filters
     *
     * @see <a href="http://developers.lightspeedhq.com/ecom/endpoints/categoryproduct/">http://developers.lightspeedhq.com/ecom/endpoints/categoryproduct/</a>
     */
    @RequestLine("GET /categories/products/count.json")
    public Count getCategoriesProductsCount();

    /**
     * Create a new customer based on the given parameters.<br>
     * Use {@link com.lightspeedhq.ecom.domain.Customer#create(java.lang.String, java.lang.String, java.lang.String) } to create the customer with the required fields.
     *
     * @param customer {@link com.lightspeedhq.ecom.domain.Customer} to create.
     *
     * @return the created {@link com.lightspeedhq.ecom.domain.Customer}
     *
     * @see <a href="http://developers.lightspeedhq.com/ecom/endpoints/customer/">http://developers.lightspeedhq.com/ecom/endpoints/customer/</a>
     */
    @RequestLine("POST /customers.json")
    public Customer createCustomer(Customer customer);

    /**
     * Retrieve a single {@link com.lightspeedhq.ecom.domain.Customer} based on the unique identifier.
     *
     * @param customerId unique id of {@link com.lightspeedhq.ecom.domain.Customer}
     *
     * @return {@link com.lightspeedhq.ecom.domain.Customer}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/customer">http://developers.seoshop.com/api/resources/customer</a>
     */
    @RequestLine("GET /customers/{customer-id}.json")
    public Customer getCustomer(@Param("customer-id") int customerId);

    /**
     * Find customer by email.
     *
     * @param filters as 'email'
     *
     * @return list of {@link com.lightspeedhq.ecom.domain.Customer}s
     */
    @RequestLine("GET /customers.json")
    public Customer.List getCustomers(@QueryMap Map<String, Object> filters);

    /**
     * Update an existing customer based on the given parameters.
     *
     * @param customerId unique id of {@link com.lightspeedhq.ecom.domain.Customer}
     * @param customer with {@link com.lightspeedhq.ecom.domain.Customer} to update
     *
     * @return the updated {@link com.lightspeedhq.ecom.domain.Customer}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/customer">http://developers.seoshop.com/api/resources/customer</a>
     */
    @RequestLine("PUT /customers/{customer-id}.json")
    public Customer updateCustomer(@Param("customer-id") int customerId, Customer customer);

    /**
     * Delete an existing customer based on the unique identifier.
     *
     * @param customerId The unique numeric identifier for the customer.
     *
     * @see <a href="http://developers.lightspeedhq.com/ecom/endpoints/customer/">http://developers.lightspeedhq.com/ecom/endpoints/customer/</a>
     */
    @RequestLine("DELETE /customers/{customer-id}.json")
    public void removeCustomer(@Param("customer-id") int customerId);

    /**
     * Retrieve a single {@link com.lightspeedhq.ecom.domain.Deliverydate} based on the unique identifier.
     *
     * @param deliverydateId The unique numeric identifier for the {@link com.lightspeedhq.ecom.domain.Deliverydate}.
     *
     * @return {@link com.lightspeedhq.ecom.domain.Deliverydate}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/deliverydate">http://developers.seoshop.com/api/resources/deliverydate</a>
     */
    @RequestLine("GET /deliverydates/{deliverydate-id}.json")
    public Deliverydate getDeliverydate(@Param("deliverydate-id") int deliverydateId);

    /**
     * Retrieve a list of all filters from this shop.
     *
     * @return {@link com.lightspeedhq.ecom.domain.Filter}s.
     *
     * @see <a href="http://developers.seoshop.com/api/resources/filter">http://developers.seoshop.com/api/resources/filter</a>
     */
    @RequestLine("GET /filters.json")
    public Filter.List getFilters();

    /**
     * Retrieve a single filter based on the unique identifier.
     *
     * @param filterId The unique numeric identifier for the filter.
     *
     * @return {@link com.lightspeedhq.ecom.domain.Filter}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/filter">http://developers.seoshop.com/api/resources/filter</a>
     */
    @RequestLine("GET /filters/{filter-id}.json")
    public Filter getFilter(@Param("filter-id") int filterId);

    /**
     * Retrieve a list of all values from a filter.
     *
     * @return {@link com.lightspeedhq.ecom.domain.FilterValue}s.
     *
     * @see <a href="http://developers.seoshop.com/api/resources/filtervalue"http://developers.seoshop.com/api/resources/filtervalue</a>
     */
    @RequestLine("GET /filters/{filter-id}/values.json")
    public FilterValue.List getFilterValues(@Param("filter-id") int filterId);

    /**
     * Retrieve a single filter value based on the unique identifier.
     *
     * @param filterId The unique numeric identifier for the filter.
     * @param filterValueId The unique numeric identifier for the filtervalue.
     *
     * @return {@link com.lightspeedhq.ecom.domain.FilterValue}.
     *
     * @see <a href="http://developers.seoshop.com/api/resources/filtervalue">http://developers.seoshop.com/api/resources/filtervalue</a>
     */
    @RequestLine("GET /filters/{filter-id}/values/{filterValue-id}.json")
    public FilterValue getFilterValue(@Param("filter-id") int filterId, @Param("filterValue-id") int filterValueId);

    /**
     * Retrieve a list of all languages from this shop.
     *
     * @return languages
     *
     * @see {@link com.lightspeedhq.ecom.domain.Language#code}
     * @see <a href="http://developers.seoshop.com/api/resources/language">http://developers.seoshop.com/api/resources/language</a>
     */
    @RequestLine("GET /languages.json")
    public Language.List getLanguages();

    /**
     * Retrieve a list of orders by customer.
     *
     * @param filters as 'customer', 'page' or 'limit'
     *
     * @return list of {@link com.lightspeedhq.ecom.domain.Order}s
     *
     * @see <a href="http://developers.seoshop.com/api/resources/order">http://developers.seoshop.com/api/resources/order</a>
     */
    @RequestLine("GET /orders.json")
    public Order.List getOrders(@QueryMap Map<String, Object> filters);

    /**
     * Retrieve a list of all {@link com.lightspeedhq.ecom.domain.Product}s from this shop.
     *
     * @param filters as 'page', 'limit'
     *
     * @return list of {@link com.lightspeedhq.ecom.domain.Product}s
     *
     * @see <a href="http://developers.seoshop.com/api/resources/product">http://developers.seoshop.com/api/resources/product</a>
     */
    @RequestLine("GET /products.json")
    public Product.List getProducts(@QueryMap Map<String, Object> filters);

    /**
     * Retrieve a single {@link com.lightspeedhq.ecom.domain.Product} based on the unique identifier.
     *
     * @param productId unique id of the {@link com.lightspeedhq.ecom.domain.Product}
     *
     * @return {@link com.lightspeedhq.ecom.domain.Product}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/product">http://developers.seoshop.com/api/resources/product</a>
     */
    @RequestLine("GET /products/{product-id}.json")
    public Product getProduct(@Param("product-id") int productId);

    /**
     * Retrieve the total number of products from this shop.
     *
     * @return count of products
     *
     * @see <a href="http://developers.seoshop.com/api/resources/product">http://developers.seoshop.com/api/resources/product</a>
     */
    @RequestLine("GET /products/count.json")
    public Count getProductCount();

    /**
     * Retrieve a list of all {@link com.lightspeedhq.ecom.domain.ProductAttribute} from this shop.
     *
     * @param productId unique id of the {@link com.lightspeedhq.ecom.domain.Product}.
     *
     * @return {@link com.lightspeedhq.ecom.domain.ProductAttribute}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/productattribute">http://developers.seoshop.com/api/resources/productattribute</a>
     */
    @RequestLine("GET /products/{product-id}/attributes.json")
    public ProductAttribute.List getProductAttributes(@Param("product-id") int productId);

    /**
     *
     * @param productId unique id of the {@link com.lightspeedhq.ecom.domain.Product}.
     *
     * @return {@link com.lightspeedhq.ecom.domain.ProductFilterValue}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/productfiltervalue">http://developers.seoshop.com/api/resources/productfiltervalue</a>
     */
    @RequestLine("GET /products/{product-id}/filtervalues.json")
    public ProductFilterValue.List getProductFilterValues(@Param("product-id") int productId);

    /**
     * Retrieve a single quote based on the unique identifier.
     *
     * @param quoteId The unique numeric identifier for the quote.
     *
     * @return {@link com.lightspeedhq.ecom.domain.Quote}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/productfiltervalue">http://developers.seoshop.com/api/resources/productfiltervalue</a>
     */
    @RequestLine("GET /quotes/{quote-id}.json")
    public Quote getQuote(@Param("quote-id") int quoteId);

    /**
     * Retrieve a list of all quotes from this shop.
     *
     * @param filters
     *
     * @return list of {@link com.lightspeedhq.ecom.domain.Quote}
     *
     * @see <a href="http://developers.lightspeedhq.com/ecom/endpoints/quote/">http://developers.lightspeedhq.com/ecom/endpoints/quote/</a>
     */
    @RequestLine("GET /quotes.json")
    public Quote.List getQuotes(@QueryMap Map<String, Object> filters);

    /**
     * Retrieve a list of all {@link com.lightspeedhq.ecom.domain.Supplier}s from the shop.
     *
     * @param supplierId The unique numeric identifier for the {@link com.lightspeedhq.ecom.domain.Supplier}.
     *
     * @return {@link com.lightspeedhq.ecom.domain.Supplier}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/supplier">http://developers.seoshop.com/api/resources/supplier</a>
     */
    @RequestLine("GET /suppliers/{supplier-id}.json")
    public Supplier getSupplier(@Param("supplier-id") int supplierId);

    /**
     * Retrieve a list of all {@link TypeAttribute}s that belong to a specific attribute.
     *
     * @param filters as 'attribute'
     *
     * @return {@link com.lightspeedhq.ecom.domain.TypesAttribute}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/typesattribute">http://developers.seoshop.com/api/resources/typesattribute</a>
     */
    @RequestLine("GET /types/attributes.json")
    public TypesAttribute.List getTypeAttributes(@QueryMap Map<String, Object> filters);

    /**
     * Retrieve a single {@link com.lightspeedhq.ecom.domain.Type}s based on the unique identifier.
     *
     * @param typeId unique numeric identifier for the {@link com.lightspeedhq.ecom.domain.Type}
     *
     * @return {@link com.lightspeedhq.ecom.domain.Type}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/type">http://developers.seoshop.com/api/resources/type</a>
     */
    @RequestLine("GET /types/{type-id}.json")
    public Type getType(@Param("type-id") int typeId);

    /**
     * Retrieve a list of variants from this shop.
     *
     * @param filters as 'product'
     *
     * @return list of {@link com.lightspeedhq.ecom.domain.Variant}s
     *
     * @see <a href="http://developers.lightspeedhq.com/ecom/endpoints/variant/">http://developers.lightspeedhq.com/ecom/endpoints/variant/</a>
     */
    @RequestLine("GET /variants.json")
    public Variant.List getVariants(@QueryMap Map<String, Object> filters);

    @RequestLine("GET /variants/count.json")
    public Count getVariantCount();

    /**
     * Retrieve a list of all {@link com.lightspeedhq.ecom.domain.Webhook}s from the shop.
     *
     * @return list of {@link com.lightspeedhq.ecom.domain.Webhook}s
     *
     * @see <a href="http://developers.seoshop.com/api/resources/webhook">http://developers.seoshop.com/api/resources/webhook</a>
     */
    @RequestLine("GET /webhooks.json")
    public Webhook.List getWebhooks();

    /**
     * Retrieve the total number of {@link com.lightspeedhq.ecom.domain.Webhook}s from this shop.
     *
     * @return count of webhooks
     *
     * @see <a href="http://developers.seoshop.com/api/resources/webhook">http://developers.seoshop.com/api/resources/webhook</a>
     */
    @RequestLine("GET /webhooks/count.json")
    public Count getWebhooksCount();

    /**
     * Retrieve a single {@link com.lightspeedhq.ecom.domain.Webhook} based on the unique identifier.
     *
     * @param webhookId The unique numeric identifier for the {@link com.lightspeedhq.ecom.domain.Webhook}.
     *
     * @return {@link com.lightspeedhq.ecom.domain.Webhook}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/webhook">http://developers.seoshop.com/api/resources/webhook</a>
     */
    @RequestLine("GET /webhooks/{webhook-id}.json")
    public Webhook getWebhook(@Param("webhook-id") int webhookId);

    /**
     * Create a new {@link com.lightspeedhq.ecom.domain.Webhook} based on the given parameters.
     *
     * @param webhook to create
     *
     * @return created {@link com.lightspeedhq.ecom.domain.Webhook}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/webhook">http://developers.seoshop.com/api/resources/webhook</a>
     */
    @RequestLine("POST /webhooks.json")
    public Webhook createWebhook(Webhook webhook);

    /**
     * Update an existing {@link com.lightspeedhq.ecom.domain.Webhook} based on the given parameters.
     *
     * @param webhookId id of the {@link com.lightspeedhq.ecom.domain.Webhook}
     * @param webhook to update
     *
     * @return updated {@link com.lightspeedhq.ecom.domain.Webhook}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/webhook">http://developers.seoshop.com/api/resources/webhook</a>
     */
    @RequestLine("PUT /webhooks/{webhook-id}.json")
    public Webhook updateWebhook(@Param("webhook-id") int webhookId, Webhook webhook);

    /**
     * Delete an existing {@link com.lightspeedhq.ecom.domain.Webhook} based on the unique identifier.
     *
     * @param webhookId id of the {@link com.lightspeedhq.ecom.domain.Webhook}
     *
     * @see <a href="http://developers.seoshop.com/api/resources/webhook">http://developers.seoshop.com/api/resources/webhook</a>
     */
    @RequestLine("DELETE /webhooks/{webhook-id}.json")
    public void deleteWebhook(@Param("webhook-id") int webhookId);

    /**
     * A CustomerToken is created based on the CustomerID.
     * By default the returnUrl redirects to the homepage and the expiresIn time will be set to 60.
     * Use {@link com.lightspeedhq.ecom.domain.CustomerToken#create(java.lang.String, int) } to request the token.
     *
     * @param customerId id of the {@link com.lightspeedhq.ecom.domain.Customer}
     * @param customerToken to create
     *
     * @return created {@link com.lightspeedhq.ecom.domain.CustomerToken} with url
     *
     * @see <a href="http://developers.lightspeedhq.com/ecom/endpoints/singlesignon/">http://developers.lightspeedhq.com/ecom/endpoints/singlesignon/</a>
     */
    @RequestLine("POST /customers/{customer-id}/tokens.json")
    public CustomerToken createCustomerToken(@Param("customer-id") int customerId, CustomerToken customerToken);

    /**
     * Builder to create an instance.
     *
     * @return builder
     */
    public static LightspeedEComClientBuilder builder() {
        return new LightspeedEComClientBuilder();
    }
}
