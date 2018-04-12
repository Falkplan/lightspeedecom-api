package com.lightspeedhq.ecom;

import com.google.common.collect.ImmutableMap;
import com.lightspeedhq.ecom.domain.Category;
import com.lightspeedhq.ecom.domain.Count;
import com.lightspeedhq.ecom.domain.CreateGroupCustomer;
import com.lightspeedhq.ecom.domain.GroupCustomer;
import org.aeonbits.owner.ConfigFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author stevensnoeijen
 */
public class LightspeedEComClientTest {

    private LightspeedEComClient lightspeedEComClient;

    public static final LightspeedEComTestConfig config = ConfigFactory.create(LightspeedEComTestConfig.class);

    @Before
    public void before() {
        lightspeedEComClient = LightspeedEComClient.builder()
                .cluster(config.getCluster())
                .language(config.getLanguage())
                .authorisation(config.getApiKey(), config.getApiSecret())
                .force(true)
                .build();
    }

    @Test
    public void getCategories() {
        Category.List categories = lightspeedEComClient.getCategories();
        assertNotNull(categories);
    }

    @Test
    public void getGroupsCustomers_all_filledList() {
        GroupCustomer.List list = lightspeedEComClient.getGroupsCustomers();
        assertFalse(list.isEmpty());
    }

    //TODO: replace the static customer and group-id's with generated id's in the lightspeed api
    @Test
    public void getGroupsCustomers_withNotExistingCustomer_emptyList() {
        GroupCustomer.List list = lightspeedEComClient.getGroupsCustomers(ImmutableMap.of("customer", 1));
        assertTrue(list.isEmpty());
    }

    @Test
    public void getGroupsCustomers_withExistingCustomer_filledList() {
        GroupCustomer.List list = lightspeedEComClient.getGroupsCustomers(ImmutableMap.of("customer", 24613943));
        assertFalse(list.isEmpty());
        assertEquals(24613943, list.get(0).getCustomer().getResource().getId());//customer-id should be equal to requested customer-id
    }

    @Test
    public void getGroupsCustomers_withNotExistingGroup_emptyList() {
        GroupCustomer.List list = lightspeedEComClient.getGroupsCustomers(ImmutableMap.of("group", 1));
        assertTrue(list.isEmpty());
    }

    @Test
    public void getGroupsCustomers_withExistingGroup_filledList() {
        GroupCustomer.List list = lightspeedEComClient.getGroupsCustomers(ImmutableMap.of("group", 23633));
        assertFalse(list.isEmpty());
        assertEquals(23633, list.get(0).getGroup().getResource().getId());
    }

    @Test
    public void getGroupsCustomersCount_existing_4() {
        Count count = lightspeedEComClient.getGroupsCustomersCount();
        assertEquals(4, count.get());
    }

    @Test
    public void getGroupsCustomer_existing_exists() {
        GroupCustomer groupCustomer = lightspeedEComClient.getGroupCustomer(3914861);
        assertNotNull(groupCustomer);
    }

    @Test(expected = LightspeedEComErrorException.class)
    public void getGroupsCustomer_notExisting_exception() {
        lightspeedEComClient.getGroupCustomer(1);
    }

    @Test
    public void addGroupsCustomer_existing_added() {
        GroupCustomer groupsCustomer = lightspeedEComClient.addGroupCustomer(new CreateGroupCustomer(23633, 25313198));
        assertNotNull(groupsCustomer);
    }

    @Test(expected = LightspeedEComErrorException.class)
    public void addGroupsCustomer_notExisting_exception() {
        lightspeedEComClient.addGroupCustomer(new CreateGroupCustomer(123, 123));
    }

    @Test
    public void removeGroupsCustomer_existing_ok() {
        GroupCustomer groupsCustomer = lightspeedEComClient.addGroupCustomer(new CreateGroupCustomer(23633, 25313198));

        lightspeedEComClient.removeGroupCustomer(groupsCustomer.getId());
    }

    @Test(expected = LightspeedEComErrorException.class)
    public void removeGroupsCustomer_notExisting_exception() {
        lightspeedEComClient.removeGroupCustomer(123);
    }
}
