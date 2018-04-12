# Change Log
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/)
and this project adheres to [Semantic Versioning](http://semver.org/)

## [v1.1.0] - 2018-04-12
### Added
-  lightspeed GroupCustomer functions in LightspeedEComClient and tested all these new methods

### Changed
- removed mockbin and insequence (bad practice)

## [v1.0.3] - 2017-04-20
### Changed
- changed JsonIgnoreProperties set per entity to ObjectMapper configure FAIL_ON_UNKNOWN_PROPERTIES

### Fixed
- category unknown property error

## [v1.0.2] - 2017-02-15
### Changed
- add skip test on build/run etc

### Fixed
- false category.image

## [v1.0.1] - 2017-01-04
### Changed
- update serialVersionUID due to change in namespace (moved these classes from another project)

## [v1.0.0] - 2017-01-04
### Added
- LightspeedEComClient.createCustomer(customer)
- LightspeedEComClient.createCustomerToken(customerId, customerToken)
- LightspeedEComClient.createWebhook(webhook)
- LightspeedEComClient.deleteWebhook(webhookId)
- LightspeedEComClient.getAccountRatelimit()
- LightspeedEComClient.getAttribute(attributeId)
- LightspeedEComClient.getBrand(brandId)
- LightspeedEComClient.getBrands(filters)
- LightspeedEComClient.getBrands()
- LightspeedEComClient.getCategories(filters)
- LightspeedEComClient.getCategoriesProducts(filters)
- LightspeedEComClient.getCategoriesProducts()
- LightspeedEComClient.getCategoriesProductsCount(filters)
- LightspeedEComClient.getCategoriesProductsCount()
- LightspeedEComClient.getCategory(categoryId)
- LightspeedEComClient.getCategoryCount()
- LightspeedEComClient.getCustomer(customerId)
- LightspeedEComClient.getCustomers(filters)
- LightspeedEComClient.getDeliverydate(deliverydateId)
- LightspeedEComClient.getFilter(filterId)
- LightspeedEComClient.getFilterValue(filterId, filterValueId)
- LightspeedEComClient.getFilterValues(filterId)
- LightspeedEComClient.getFilters()
- LightspeedEComClient.getLanguages()
- LightspeedEComClient.getOrders(filters)
- LightspeedEComClient.getProduct(productId)
- LightspeedEComClient.getProductAttributes(productId)
- LightspeedEComClient.getProductCount()
- LightspeedEComClient.getProductFilterValues(productId)
- LightspeedEComClient.getProducts(filters)
- LightspeedEComClient.getQuote(quoteId)
- LightspeedEComClient.getQuotes(filters)
- LightspeedEComClient.getSupplier(supplierId)
- LightspeedEComClient.getType(typeId)
- LightspeedEComClient.getTypeAttributes(filters)
- LightspeedEComClient.getVariantCount()
- LightspeedEComClient.getVariants(filters)
- LightspeedEComClient.getWebhook(webhookId)
- LightspeedEComClient.getWebhooks()
- LightspeedEComClient.getWebhooksCount()
- LightspeedEComClient.removeCustomer(customerId)
- LightspeedEComClient.updateCustomer(customerId, customer)
- LightspeedEComClient.updateWebhook(webhookId, webhook)
