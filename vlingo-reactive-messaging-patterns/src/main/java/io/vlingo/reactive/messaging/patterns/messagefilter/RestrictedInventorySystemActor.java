// Copyright © 2012-2018 Vaughn Vernon. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.reactive.messaging.patterns.messagefilter;

import io.vlingo.actors.Actor;
import io.vlingo.actors.testkit.TestUntil;

public class RestrictedInventorySystemActor extends Actor implements InventorySystem {

    private final TestUntil until;

    public RestrictedInventorySystemActor(final TestUntil until) {
        this.until = until;
    }

    @Override
    public void processOrder(final Order order) {
        if(order.isType("TypeABC")) {
            logger().log("Handling " + order);
            until.happened();
        } else {
            logger().log("Filtering out " + order);
            until.happened();
        }
    }

}