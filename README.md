# lightspeedecom-api
Java api client for the lightspeed ecommerce platform, based on http://developers.lightspeedhq.com/ecom/

## install
Add jitpack.io repository: https://jitpack.io/

Add dependency to maven:
~~~~
dependency>
    <groupId>com.github.Falkplan</groupId>
    <artifactId>lightspeedecom-api</artifactId>
    <version>v0.1.0</version>
</dependency>
~~~~
or gradle:
~~~~
dependencies {
  compile 'com.github.Falkplan:lightspeedecom-api:v0.1.0'
}
~~~~

## Usage
~~~~
LightspeedEComClient lightspeedEComClient = LightspeedEComClient.builder()
                .cluster("api.webshopapp.com")
                .language("nl")
                .authorisation("yourapikey", "yourapisecret")
                .build();
~~~~

The client supports the following methods:
- [Account Rate limits](http://developers.lightspeedhq.com/ecom/endpoints/accountratelimit/)
  - getAccountRatelimit
- [Attribute](http://developers.lightspeedhq.com/ecom/endpoints/attribute/)
  - getAttribute
- [Brand](http://developers.lightspeedhq.com/ecom/endpoints/brand/)
  - getBrand
  - getBrands
- [Categories](http://developers.lightspeedhq.com/ecom/endpoints/category/)
  - getCategories
  - getCategoryCount
  - getCategory
- [CategoriesProduct](http://developers.lightspeedhq.com/ecom/endpoints/categoryproduct/)
  - getCategoriesProducts
  - getCategoriesProductsCount
- [Customer](http://developers.lightspeedhq.com/ecom/endpoints/customer/)
  - createCustomer
  - getCustomer
  - getCustomers
  - updateCustomer
  - removeCustomer
- [Deliverydate](http://developers.lightspeedhq.com/ecom/endpoints/deliverydate/)
  - getDeliverydate
- [Filters](http://developers.lightspeedhq.com/ecom/endpoints/filter/)
  - getFilters
  - getFilter
- [FilterValue](http://developers.lightspeedhq.com/ecom/endpoints/filtervalue/)
  - getFilterValues
  - getFilterValue
- [Language](http://developers.lightspeedhq.com/ecom/endpoints/language/)
  - getLanguages
- [Order](http://developers.lightspeedhq.com/ecom/endpoints/order/)
  - getOrders
- [Product](http://developers.lightspeedhq.com/ecom/endpoints/product/)
  - getProducts
  - getProduct
  - getProductCount
- [ProductAttribute](http://developers.lightspeedhq.com/ecom/endpoints/productattribute/)
  - getProductAttributes
- [ProductFilterValue](http://developers.lightspeedhq.com/ecom/endpoints/productfiltervalue/)
  - getProductFilterValues
- [Quote](http://developers.lightspeedhq.com/ecom/endpoints/quote/)
  - getQuote
  - getQuotes
- [Supplier](http://developers.lightspeedhq.com/ecom/endpoints/supplier/)
  - getSupplier
- [TypeAttribute](http://developers.lightspeedhq.com/ecom/endpoints/typesattribute/)
  - getTypeAttributes
- [Type](http://developers.lightspeedhq.com/ecom/endpoints/type/)
  - getType
- [Variant](http://developers.lightspeedhq.com/ecom/endpoints/variant/)
  - getVariants
  - getVariantCount
- [Webhook](http://developers.lightspeedhq.com/ecom/endpoints/webhook/)
  - getWebhooks
  - getWebhooksCount
  - getWebhook
  - createWebhook
  - updateWebhook
  - deleteWebhook
- [SingleSignOn](http://developers.lightspeedhq.com/ecom/endpoints/singlesignon/)
  - createCustomerToken

### Error handling
LightspeedEComErrorException (RunTimeException) can be thrown at any call when the api returns an error status, the error description will be contained in the `.error` object inside the exception.

Know that your api key has a [rate limit](http://developers.lightspeedhq.com/ecom/introduction/rate-limiting/) that will throw an error when it exceeds the limit.

There is an option to use the "force" method in the builder to retry a request when the limit was exceeded, this will block the tread though!

There is also an option to set a request limit ("limit" in builder) to only use a certain amount of requests, to preserve the rate limit for other usages.

## Testing
To run the tests rename `default.test.properties` to `test.properties` and set all values.

To acquire authentication see: http://developers.lightspeedhq.com/ecom/introduction/authentication/ and see http://developers.lightspeedhq.com/ecom/introduction/clusters/ for cluster.

**Warning:** Tests are not setup for users of this library, but they could be edited for universal usage.

***

**Notice:** Falkplan is not to be associated with Lightspeed, Falkplan is merely a customer of Lightspeed.
