// Copyright © 2012-2018 Vaughn Vernon. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.reactive.messaging.patterns.pipesandfilters;

import java.nio.charset.StandardCharsets;

import io.vlingo.actors.Actor;
import io.vlingo.actors.testkit.TestUntil;

public class OrderAcceptanceEndpoint extends Actor implements OrderProcessor {
  private final OrderProcessor nextFilter;
  private final TestUntil until;

  public OrderAcceptanceEndpoint(final OrderProcessor nextFilter, final TestUntil until) {
    this.nextFilter = nextFilter;
    this.until = until;
  }

  @Override
  public void processIncomingOrder(byte[] orderInfo) {
    final String textOrderInfo = new String(orderInfo, StandardCharsets.UTF_8);
    logger().log("OrderAcceptanceEndpoint: processing " + textOrderInfo);
    nextFilter.processIncomingOrder(orderInfo);
    until.happened();
  }
}
