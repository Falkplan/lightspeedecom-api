package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;

/**
 *
 * @author stevensnoeijen
 */
@JsonRootName("groupsCustomer")
public class CreateGroupCustomer {

    @Getter
    private int group;

    @Getter
    private int customer;

    public CreateGroupCustomer() {
    }

    public CreateGroupCustomer(int group, int customer) {
        this.group = group;
        this.customer = customer;
    }
}
