package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.io.Serializable;
import java.util.ArrayList;
import lombok.Getter;

/**
 *
 * @see <a href="http://developers.lightspeedhq.com/ecom/endpoints/groupscustomer">http://developers.lightspeedhq.com/ecom/endpoints/groupscustomer</a>
 * @author stevensnoeijen
 */
@JsonRootName("groupsCustomer")
public class GroupCustomer implements Serializable {

    @JsonRootName("groupsCustomers")
    public static class List extends ArrayList<GroupCustomer> {

    }

    /**
     * The unique numeric identifier for the groupsCustomer.
     */
    @Getter
    private int id;

    /**
     * Link to group's resource.
     */
    @Getter
    private ResourceObject group;

    /**
     * Link to customer's resource.
     */
    @Getter
    private ResourceObject customer;

    public GroupCustomer() {
    }


}
